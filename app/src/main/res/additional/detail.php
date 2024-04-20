<?php
include("koneksi.php");

extract($_GET);

if (empty($id)) {
    $sql = "SELECT p.*, n.title newsTitle, n.author, n.image FROM page p INNER JOIN news n ON p.news_id = n.id";
} else {
    $sql = "SELECT p.*, n.title newsTitle, n.author, n.image FROM page p INNER JOIN news n ON p.news_id = n.id WHERE news_id = '$id'";
}

$result = $conn->query($sql);
$datas = array();

if ($result->num_rows > 0) {
    while ($obj = $result->fetch_object()) {
        $datas[] = $obj;
    }

    echo json_encode($datas);
} else {
    echo json_encode(array('result' => 'ERROR', 'message' => 'No data found'));
    die();
}

$conn->close();
