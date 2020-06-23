<?php
include 'predefiner.php';
$jobid = trim($_POST['jobid']);
$applicant = trim($_POST['applicant']);
$cert = trim($_POST['cert']);
$sidenote = trim($_POST['sidenote']);

	$path = "upload/".$jobid.$applicant.".pdf";
	file_put_contents($path,base64_decode($cert));
	$sql = "call sp_newjob_application('$jobid','$applicant','$sidenote','$path')";
	$result = mysqli_query($conn, $sql);
	$info = "";

	if ($result)
	{
	$info= "true";
	} 
	else 
	{
	$info= "false";
	}

	echo $info;
	$conn->close();
?>