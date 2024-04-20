<?php
include("koneksi.php");

extract($_GET);

if (empty($id)) {
    $sql = "SELECT * FROM news ORDER BY released_date DESC";
} else {
    $sql = "SELECT * FROM news WHERE id = '$id'";
}

$result = $conn->query($sql);
$data = array();

if ($result->num_rows > 0) {
    while ($obj = $result->fetch_object()) {
        $data[] = $obj;
    }
    echo json_encode($data);
}

$conn->close();
