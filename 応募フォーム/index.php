<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
 <link rel="stylesheet" href="cs.css">

<title>トップ</title> 
</head>

<body>

 <!-- トップページ：ユーザーが入力するページ -->
    <form method="POST" action="cheak.php">

      <p>【10/15 10/16 開催】</p>
      <p>人工知能•入門セミナー 応募フォーム</p>

        <!-- //隠し値：ページがどこか示すもの -->
        <input type="hidden" name="page" value="index"><br/>

        <!-- //各部品の記入 -->
        
        <p>■学生IDを入力してください</p>
        <input type="text" name="id" placeholder="例）12345678" ><br/>
        <p class="hosoku">※もし学生ではない来客の場合は未記入でお願いします</p>

        <p>■名前を入力してください<p>
        <input type="text" name="name"  placeholder="例）山田太郎"><br/>

        <p>■電話番号を入力してください</p>
        <input type="tel" name="tel"  placeholder="例）070xxxxyyyy" ><br/>

        <p>■メールアドレスを入力してください<p>
        <input type="email" name="mail" placeholder="例）xxxx@xxxxx" ><br/>
        
        <p>■参加する日程を選択してください ※複数選択可<p>
        10月15日：<input type="checkbox" name="d15" value=1 ><br/>
        10月16日：<input type="checkbox" name="d16" value=1><br/>


         <!-- //登録ボタン -->
        
        <br/>
        <input type="submit" value="確認する">


     </form>


</body>

</html>