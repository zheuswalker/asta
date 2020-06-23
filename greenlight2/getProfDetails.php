<?php
include 'predefiner.php'; 
	$result_array = array();
    $id = trim($_POST['id']);
    $query ="call sp_getUserDetails('$id');";
    if ($result = $conn->query($query)) 
      while($row = $result->fetch_array(MYSQLI_ASSOC))
      $result_array [] = $row;
      $output = json_encode(array('UserDetails' => $result_array ));
      echo $output;
      $conn->close();
?>