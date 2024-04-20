<?php
include('koneksi.php');

extract($_POST);

$stmt = $conn->prepare("INSERT INTO users (username, fname, lname, email, password) VALUES (?, ?, ?, ?, ?)");

$stmt->bind_param("sssss", $username, $fname, $lname, $email, $password);

if ($stmt->execute()) {
    $arr = array("result" => "OK", "sql" => $sql, "message" => "Sign up success");
} else {
    $arr = ["result" => "error", "message" => "Sign up failed"];
}

echo json_encode($arr);
$stmt->close();
$conn->close();
