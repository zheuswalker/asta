<?php
include 'predefiner.php';
    $result_array = array();
    $email = trim($_POST['username']);
    $userpassword = trim($_POST['password']);
    $query ="call  sp_loginuser('$email','$userpassword');";
    if ($result = $conn->query($query)) 
      while($row = $result->fetch_array(MYSQLI_ASSOC))
      $result_array [] = $row;
      $output = json_encode(array('login_user' => $result_array ));
      echo $output;
      $conn->close();

?>