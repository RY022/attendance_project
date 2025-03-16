<?php
error_reporting(-1);

define('attendance_users', 'mysql:host=localhost; dbname=cri_sortable; charset=utf8');
define('user_id', 'root');
define('password', 'root');

try {
    $dbh = new PDO(attendance_users, user_id, password);
    $dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $dbh->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
} catch (PDOException $e){
    echo $e->getMessage();
    exit;
}

if(!empty($_POST['userid'])){
    try{
        $sql  = 'INSERT INTO users(user_id) VALUES(:ONAMAE)';
        $stmt = $dbh->prepare($sql);
        
        $stmt->bindValue(':ONAMAE', $_POST['userid'], PDO::PARAM_STR);
        $stmt->execute();
        
        header('location: http://localhost:8001/');
        exit();
    } catch (PDOException $e) {
        echo 'データベースにアクセスできません！'.$e->getMessage();
    }
}

if(!empty($_POST['password'])){
    try{
        $sql  = 'INSERT INTO users(password) VALUES(:ONAMAE)';
        $stmt = $dbh->prepare($sql);
        
        $stmt->bindValue(':ONAMAE', $_POST['password'], PDO::PARAM_STR);
        $stmt->execute();
        
        header('location: http://localhost:8001/');
        exit();
    } catch (PDOException $e) {
        echo 'データベースにアクセスできません！'.$e->getMessage();
    }
}
?>