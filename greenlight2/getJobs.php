<?php
include 'predefiner.php';
  
// $type = $_GET['item_type'];      
$type = "users";      



if (isset($_POST['key'])) {
    $key = $_POST["key"];
        $query = "SELECT Req.RS_ID as id, Req.RS_TITLE as jobname, CONCAT(SUBSTRING(Req.RS_DESCRIPTION,1,40),'...') as jobdescription
                            FROM r_request_seminars as Req
                            INNER JOIN r_professions as Prof
                            ON Req.PR_ID = Prof.PR_ID
                            WHERE Req.RS_TITLE LIKE '%$key%' OR Req.RS_DESCRIPTION LIKE '%$key%'
                            "
                            ;
            $result = mysqli_query($conn, $query);
            $response = array();
            while($row = mysqli_fetch_assoc($result) ){
                array_push($response, 
                array(
                    'id'=>$row['id'], 
                    'jobname'=>$row['jobname'], 
                    'jobdescription'=>$row['jobdescription']) 
            );
        }

      //  echo json_encode($response); 
        echo json_encode(array('nameofjson' => $response ));

}
else {
    if ($type == 'users') {
        $query = "SELECT Req.RS_ID as id, Req.RS_TITLE as jobname, CONCAT(SUBSTRING(Req.RS_DESCRIPTION,1,40),'...') as jobdescription
                            FROM r_request_seminars as Req
                            INNER JOIN r_professions as Prof
                            ON Req.PR_ID = Prof.PR_ID";
            $result = mysqli_query($conn, $query);
            $response = array();
            while($row = mysqli_fetch_assoc($result) ){
                array_push($response, 
                array(
                    'id'=>$row['id'], 
                    'jobname'=>$row['jobname'], 
                    'jobdescription'=>$row['jobdescription']) 
            );
        }

       // echo json_encode($response); 
        echo json_encode(array('nameofjsonsss' => $response ));
    }
}
$conn->close();
?>