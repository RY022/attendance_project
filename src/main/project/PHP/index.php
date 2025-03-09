<?php
if(!empty($_POST['inputName'])){
    try{
        $sql  = 'INSERT INTO sortable(name) VALUES(:ONAMAE)';
        $stmt = $dbh->prepare($sql);
        
        $stmt->bindValue(':ONAMAE', $_POST['userid'], PDO::PARAM_STR);
        $stmt->bindValue(':ONAMAE', $_POST['password'], PDO::PARAM_STR);
        $stmt->bindValue(':ONAMAE', $_POST['registration'], PDO::PARAM_STR);
        $stmt->execute();
        
        header('location: http://localhost:8001/');
        exit();
    } catch (PDOException $e) {
        echo 'データベースにアクセスできません！'.$e->getMessage();
    }
}
?>