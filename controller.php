<?php

switch($_POST['page']){//各ページ転移処理をここに記述する

    case 'index'://indexから来た場合の処理

        //値受け取り各種変数
        $id = $_POST['id'];
        $name = $_POST['name'];
        $tel = $_POST['tel'];
        $mail = $_POST['mail'];
        $d15 = $_POST['d15'];
        $d16 = $_POST['d16'];

        //次のページへ：Cheak.php
        header("Location:cheak.php");
        exit;

    break;

    case 'cheak'://cheakから来た場合の処理

        //値受け取り各種変数
        $id = $_POST['id'];
        $name = $_POST['name'];
        $tel = $_POST['tel'];
        $mail = $_POST['mail'];
        $d15 = $_POST['d15'];
        $d16 = $_POST['d16'];

        //安全対策
        $id= htmlspecialchars($id,ENT_QUOTES,'UTF-8');
        $name= htmlspecialchars($name,ENT_QUOTES,'UTF-8');
        $tel= htmlspecialchars($tel,ENT_QUOTES,'UTF-8');
        $mail= htmlspecialchars($mail,ENT_QUOTES,'UTF-8');

        //DB処理：接続
        $dsn = 'mysql:dbname=test;host=localhost;charset=utf8mb4';
        $user = 'root';
        $password = '';
        $dbh = new PDO($dsn,$user,$password);
        $dbh ->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION); 

        //DB処理：DBにデータ挿入をする
        $sql = 'INSERT INTO stud2(id,name,tel,mail,d15,d16) VALUES (?,?,?,?,?,?)';
        $stmt = $dbh->prepare($sql);
        $date[] = $id;
        $date[] = $name;
        $date[] = $tel;
        $date[] = $mail;
        $date[] = $d15;
        $date[] = $d16;
        $stmt -> execute($date);

        $dbh = null;//切断

        //完了画面に移る
        header("Location:ok.php");
        exit;


    break;

}



?>