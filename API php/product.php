<?php
include "config.php";
$sql = "SELECT * FROM product ORDER BY id DESC;";
$data = mysqli_query($conn, $sql);

$result = array();
while($row = mysqli_fetch_assoc($data)){
    $result[] = ($row);
}

if(!empty($result)){

    $arr = [
        'success' => true,
        'message' => "thanh cong",
        'result' => $result

    ];

}
else{
        
    $arr = [
        'success' => false,
        'message' => "that bai",
        'result' => $result

    ];
}
print_r(json_encode($arr));


?>