<?php
include 'predefiner.php';
    $result_array = array();
    $key = trim($_POST['key']);
    $query ="call sp_searchjob('$key');";
    if ($result = $conn->query($query)) 
      while($row = $result->fetch_array(MYSQLI_ASSOC))
      $result_array [] = $row;
      $output = json_encode(array('onlineContentswhere' => $result_array ));
      echo $output;
      $conn->close();
?>