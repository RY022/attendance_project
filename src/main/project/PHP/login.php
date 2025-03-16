<?php
session_start();

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "attendance_users";

$user = $_POST['user_id'];
$pass = $_POST['password'];

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("接続失敗: " . $conn->connect_error);
}

$sql = "SELECT * FROM users WHERE user_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $user);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    if (password_verify($pass, $row['password'])) {
        $_SESSION['username'] = $row['user_id'];
        header("Location: dashboard.php");
        exit();
    } else {
        echo "無効なユーザー名またはパスワードです。";
    }
} else {
    echo "無効なユーザー名またはパスワードです。";
}

$stmt->close();
$conn->close();
?>