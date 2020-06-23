<?php

class android_queries
{

	function getQuery($request,$param = null){

		switch ($request)
		{

			case 'addIllustration':$query = "insert into r_gl_certificate VALUES(
											  	null,"
											    .$param['title'].",
											  	'".$param['cert'].",
											  	DEFAULT,
											  	DEFAULT,
											  	'".$param['id'].",
											  	null
											  )"; break;

			default : $query = ''; break;
		}

	return $query;
	}

}


?>