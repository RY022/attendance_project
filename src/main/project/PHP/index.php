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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link th:href="@{/css/style.css}" rel="stylesheet">
<body>
<div id="wrapper">

<div id="input_form">
  <form action="index.php" method="POST">
    <input type="text" name="userid">
    <input type="text" name="password">
    <input type="submit" name="registration" value="新規登録">
  </form>
</div>

<div id="drag-area">
<?php
$sql = 'SELECT * FROM sortable';
$stmt = $dbh->query($sql)->fetchAll(PDO::FETCH_ASSOC);
foreach ($stmt as $result){
  echo '  <div class="drag" data-num="'.$result['id'].'" style="left:'.$result['left_x'].'px; top:'.$result['top_y'].'px;">'.PHP_EOL;
  echo '    <p><span class="name">'.$result['id'].' '.$result['name'].'</span></p>'.PHP_EOL;
  echo '  </div>'.PHP_EOL;
}
?>
</div>
</div>
</body>
</head>
</html>