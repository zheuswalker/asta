<?php
    include 'predefiner.php';
    $result_array = array();
    $fullname = trim($_POST['fullname']);
    $email = trim($_POST['email']);
    $userpassword = trim($_POST['userpassword']);
    $profession = trim($_POST['profession']);
    $usertype = trim($_POST['usertype']);
    $query ="call sp_add_user('$usertype','$fullname','$email','$userpassword','$profession');";
    if ($result = $conn->query($query)) 
      while($row = $result->fetch_array(MYSQLI_ASSOC))
      $result_array [] = $row;
      $output = json_encode(array('register_user' => $result_array ));
      echo $output;
      $conn->close();

?>