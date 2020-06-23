<?php
include 'predefiner.php';
	$id = trim($_POST['id']);

	$query = "SELECT TC_TITLE as cert_title, CONCAT('Status: ',TC_IS_EVALUATED) as cert_status, CONCAT('CPD Units: ',CPD_UNITS, ' Units') as cert_units
		FROM t_certificates
		WHERE PRC_ID = '$id' AND ACTIVE_FLAG = '1'";

	if ($result = $conn->query($query)) 
		while($row = $result->fetch_array(MYSQLI_ASSOC))
			$notifContent [] = $row;

$output = json_encode(array('notifContent' => $notifContent));
echo $output;
$conn->close();
?>