<?php
  	include 'predefiner.php';
    $result_array = array();
    $fullname = trim($_POST['fullname']);
    $email = trim($_POST['email']);
    $userpassword = trim($_POST['userpassword']);
    $profession = trim($_POST['profession']);
    $usertype = trim($_POST['usertype']);
    $query ="call sp_getJobs();";
    if ($result = $conn->query($query)) 
      while($row = $result->fetch_array(MYSQLI_ASSOC))
      $result_array [] = $row;
      $output = json_encode(array('get_jobs' => $result_array ));
      echo $output;
      $conn->close();
?>