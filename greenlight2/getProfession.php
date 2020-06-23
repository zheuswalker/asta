<?php
include 'predefiner.php';

$query = "select DISTINCT PR_NAME as profession from r_professions";


	if ($result = $conn->query($query)) 
while($row = $result->fetch_array(MYSQLI_ASSOC))
$professionName [] = $row;

$output = json_encode(array('professionName' => $professionName));
echo $output;
$conn->close();
?>

