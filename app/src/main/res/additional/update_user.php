<?php
include("koneksi.php");
extract($_POST);

$stmt = $conn->prepare("UPDATE users SET fname=?, lname=?, password=? WHERE id=?");

$stmt->bind_param("sssi", $fname, $lname, $password, $id);

if ($stmt->execute()) {
    $arr = array("result" => "OK", "message" => "Update Password Success");
} else {
    $arr = ["result" => "error", "message" => "Gagal simpan data", "error" => $stmt->error];
}

echo json_encode($arr);
$stmt->close();
$conn->close();
