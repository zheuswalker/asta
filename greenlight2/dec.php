<?php
include 'predefiner.php';
	   	  
		  $sql = "select md5(password) as passw from users where email = 'smav@smav.com' ";

$result = mysqli_query($conn, $sql);
$info = "";
 while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) 
    {
    		$info= $row['passw'];
    		//$info= "true";
    }
echo $info;
$conn->close();
?>