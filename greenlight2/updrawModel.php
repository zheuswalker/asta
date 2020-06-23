<?php

// Android SQL Model
class android_model
{
	
	function runQuery($query,$request = null)
	{
		include ("dbconn.php");

		$result = $updrawMysqli->query($query);
		if($request == "id")
			return $updrawMysqli->insert_id;
		else
			return $result;
	}

	function selectData($result,$request)
	{
		$Json = array();
		if ($result->num_rows > 0)
		{
			$i = 0;
			while($data = $result->fetch_assoc()) 
			{
				if($request == "illustration")
				{
					$Json['illustration']["IllustrationID"] = $data["title"];
					$Json["illustration"]["Illustration"] = $data["cert"];
					$Json['illustration']["IllustrationDesc"] = $data["id"];
				}

			$i++;
			}
			$Json["result"] = 1;
		}
		else
		{
			$Json['result'] = 0;
		}
		return $Json;
	}

	

}

?>