<?php
include "config.php";
$sql = "select * from listmenu";
$data = mysqli_query($conn, $sql);

$result = array();
while($row = mysqli_fetch_assoc($data)){
    $result[] = ($row);
}

if(!empty($result)){

    $arr = [
        'succes' => true,
        'message' => "thanh cong",
        'result' => $result

    ];

}
else{
        
    $arr = [
        'succes' => false,
        'message' => "that bai",
        'result' => $result

    ];
}
print_r(json_encode($arr));


?>