<?php
include("koneksi.php");

error_reporting(E_ERROR | E_PARSE);

$sql = "SELECT * FROM users";

$result = $conn->query($sql);
$data = array();

if ($result->num_rows > 0) {
    while ($obj = $result->fetch_object()) {
        $data[] = $obj;
    }
    echo json_encode(array('result' => 'OK', 'data' => $data));
} else {
    echo json_encode(array('result' => 'ERROR', 'message' => 'No data found'));
    die();
}

$conn->close();
