<?php
include 'predefiner.php';
    $result_array = array();
    $id = trim($_POST['id']);
    $query ="call sp_getJobDetails('$id');";
    if ($result = $conn->query($query)) 
      while($row = $result->fetch_array(MYSQLI_ASSOC))
      $result_array [] = $row;
      $output = json_encode(array('homeDetailsContent' => $result_array ));
      echo $output;
      $conn->close();
?>

