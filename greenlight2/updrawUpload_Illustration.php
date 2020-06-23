<?php
include_once("updrawModel.php");
include_once("updrawQuery.php");

$target_dir = "uploads/";
$title = $_POST['title'];

//Create Folder
if(!file_exists($target_dir))
{
	mkdir($target_dir,0777,true);
}

$target_dir = $target_dir . rand() . "_" . time() . ".jpeg";

if(file_put_contents($target_dir, base64_decode($target_dir)))
{
	$android = new android_model();
	$androidQuery = new android_queries();

		$status = '1';

		$query = $androidQuery->getQuery('addIllustration',"cert"=>$target_dir,"title"=>$title,"id"=>$_POST['id']);
		$result = $android->runQuery($query);
	
	if($result!="")
	{
		echo "success";
	}
	else
	{
		echo "failed";
	}
}


?>