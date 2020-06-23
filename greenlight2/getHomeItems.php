<?php
include 'predefiner.php';
	$id = trim($_POST['id']);

	$query = "SELECT Req.RS_ID as repository_id, Req.RS_TITLE as seminar_title, CONCAT(SUBSTRING(Req.RS_DESCRIPTION,1,65),'...') as seminar_desc
		FROM r_request_seminars as Req
		INNER JOIN r_professions as Prof
		ON Req.PR_ID = Prof.PR_ID

		INNER JOIN users
		ON Prof.PR_ID = users.PR_ID

		WHERE Prof.PR_ID = (SELECT users.PR_ID where users.id = '$id') AND Req.RS_STATUS = 'Approved'";

	if ($result = $conn->query($query)) 
		while($row = $result->fetch_array(MYSQLI_ASSOC))
			$homecontent [] = $row;

$output = json_encode(array('homeContent' => $homecontent));
echo $output;
$conn->close();
?>