<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
 <link rel="stylesheet" href="cs.css">
<title>応募フォーム</title> 
</head>

<body>
 <!-- 受け取り後にデータベースに参照：内部処理確認ページ-->
<?php

   print'<form method="POST" action="controller.php">' ;  

   //flag管理の変数:入力がまだだったらfalse
   $flag = true;

   //indexから受け取った値
   $sanka_id = $_POST['id'];
   $sanka_name = $_POST['name'];
   $sanka_tel = $_POST['tel'];
   $sanka_mail = $_POST['mail'];
   $sanka_d15 = $_POST['d15']??'';
   $sanka_d16 = $_POST['d16']??'';

   //安全対策
   $sanka_id= htmlspecialchars($sanka_id,ENT_QUOTES,'UTF-8');
   $sanka_name= htmlspecialchars($sanka_name,ENT_QUOTES,'UTF-8');
   $sanka_tel= htmlspecialchars($sanka_tel,ENT_QUOTES,'UTF-8');
   $sanka_mail= htmlspecialchars($sanka_mail,ENT_QUOTES,'UTF-8');

   //DBと接続する：参加日程参照用＋重複登録回避用
   $dsn = 'mysql:dbname=test;host=localhost;charset=utf8';
   $user = 'root';
   $password = '';
   $dbh = new PDO($dsn,$user,$password);
   $dbh ->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION); 

   //１５日と１６日の参加者のカウントをする
   $sql_d15 = 'SELECT COUNT(*) FROM stud2 WHERE d15=1';
   $sql_d16 = 'SELECT COUNT(*) FROM stud2 WHERE d16=1';
   $stmt_d15 = $dbh->prepare($sql_d15);
   $stmt_d16 = $dbh->prepare($sql_d16);

   $stmt_d15 -> execute();
   $stmt_d16 -> execute();

   $cd15 = $stmt_d15->fetchColumn();//１５日カウント結果が[$cd15]
   $cd16 = $stmt_d16->fetchColumn();//１６日カウント結果が[$cd16]


   //重複登録回避の処理
   //同姓同名が来る可能性があるので名前は重複処理なし
   $sql_id = 'SELECT * FROM `stud2` WHERE id=?';
   $sql_tel = 'SELECT * FROM `stud2` WHERE tel=?';
   $sql_mail = 'SELECT * FROM `stud2` WHERE mail=?';
   $stmt_id = $dbh->prepare($sql_id);
   $stmt_tel = $dbh->prepare($sql_tel);
   $stmt_mail = $dbh->prepare($sql_mail);

   $stmt_id -> execute([$sanka_id]);
   $stmt_tel -> execute([$sanka_tel]);
   $stmt_mail -> execute([$sanka_mail]);

   $cid = $stmt_id->rowCount();//重複チェック：id
   $ctel = $stmt_tel->rowCount();//重複チェック：tel
   $cmail = $stmt_mail->rowCount();//重複チェック：mail


   $dbh = null;//切断

   // //参加者カウント：テスト用
   // print'15日の人数表示'.$cd15;
   // print'<br/>';
   // print'16日の人数表示'.$cd16;
   // print'<br/>';

   // //重複チェック用：テスト用
   // print'cid：'.$cid;
   // print'<br/>';
   // print'ctel：'.$ctel;
   // print'<br/>';
   // print'cmail：'.$cmail;
   // print'<br/>';



   //空記入の場合の処理
   print'<br/>';
   print'<p>以下の入力で間違いがないかご確認ください。</p>';

   if($sanka_id==""){//id空入力は来客id「00000000」を代入する
      $sanka_id = 00000000;
   }
   if($sanka_id=="0000000") {
      print'<p class="hosoku">【学校外の参加者様へ】</p>';
      print'<p class="hosoku">当日のチケットをご購入をお願いします。</p>';
      print'<p class="hosoku">チケットは○○室にて職員○○がお待ちしております。1日につき1000円になります。</p>';
      print'<p class="hosoku">詳しい情報は折り返しのメールに書かれてありますのでご確認お願いします。</p>';
   }else if($cid  > 0 && $sanka_id!="0000000"){
      print'<p class="error">エラー：同じ学生番号が登録されています。</p>';
      $flag = false;
   } else {
      print'<br/>';
      print '学生番号：'.$sanka_id;
   }

   if($sanka_name==""){
      print '<p class="error">【名前未記入】名前を入力してください。</p>';
      $flag = false;
   } else{
      print'<br/>';
      print '名前：'.$sanka_name;
   }

   if($sanka_tel==""){
      print'<p class="error">【電話番号未記入】電話番号を入力してください。</p>';
      $flag = false;
   }else if($ctel > 0 ){
      print'<p class="error">エラー：同じ電話番号が登録されています。</p>';
      $flag = false;
   }else{
      print'<br/>';
      print'電話番号：'.$sanka_tel;
   }

   if($sanka_mail==""){
      print'<p class="error">【メールアドレス未記入】メールアドレスを入力してください。</p>';
      $flag = false;
   }else if($cmail > 0 ){
      print'<p class="error">エラー：同じメールアドレスが登録されています。</p>';
      $flag = false;
   }else{
      print'<br/>';
      print'メールアドレス：'.$sanka_mail;
   }


   //参加日程の処理
   if($sanka_d15==1 && $cd15<39){//39は残り席数
      print'<br/>';
      print'10月15日：参加<br/>';
   }else if($cd15 >= 39){
      print'<br/>';
      print'<p class="error">※10月15日は定員を達したようです。</p>';
   }else{
      print'<br/>';
      print'10月15日：不参加<br/>';
   }

   if($sanka_d16==1 && $cd15<50){//50は残り席数
      print'10月16日：参加<br/>';

   }else if($cd16 >= 50){
      print'<p class="error">※10月16日は定員を達したようです。</p>';
   }else{
      print'10月16日：不参加<br/>';
   }

   //決定ボタン表示分岐
   //未記入があれば戻って記入を繰り返す
   if($flag==true){
      print'<p>[確定]ボタンを押すと参加を申し込みが確定します。</p><br/>';

      //値を次に送る処理
      print'<input type="hidden" name="id" value="'.$sanka_id.'">';
      print'<input type="hidden" name="name" value="'.$sanka_name.'">';
      print'<input type="hidden" name="tel" value="'.$sanka_tel.'">';
      print'<input type="hidden" name="mail" value="'.$sanka_mail.'">';
      print'<input type="hidden" name="d15" value="'.$sanka_d15.'">';
      print'<input type="hidden" name="d16" value="'.$sanka_d16.'">';

      print'<input type="submit" value="確定">';

   }else{
      print'<p>もう一度入力をお願いします。</p><br/>';
      print '<input type="button" onclick="history.back()" value="戻る">';

   }

   print'<form method="POST" action="controller.php">';
   
?>

<!-- 隠し値：ページがどこか示すもの -->
<input type="hidden" name="page" value="cheak"><br/>


</body>
</html>
