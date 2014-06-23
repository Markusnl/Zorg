<?php
//SELECT *, SUM(amount) FROM transactions GROUP BY user
//header ("Content-Type:text/xml");

include('functions.php');
connect($GLOBALS['host'], $GLOBALS['username'], $GLOBALS['password'], $GLOBALS['database']);

if (isset($_POST['action']))
{
	$action = $_POST['action'];
	
	switch ($action) 
	{
  		case get_clienten:
    		if (isset($_POST['clientnaam']))
			{	
				$input = $_POST['clientnaam'];
				
				get_client($input);
			}
			break;
			
  		case validate_user:
			if (isset ($_POST['username'],$_POST['password']))
			{
				$username = $_POST['username'];
				$password = $_POST['password'];
	
				validate_user($username,$password);
			}
			break;
			
  		case create_user:
			if (isset ($_POST['username'],$_POST['password'],$_POST['email'],$_POST['rechten']))
			{
				$username = $_POST['username'];
				$password = $_POST['password'];
				$email 	= 	$_POST['email'];
				$rechten = $_POST['rechten'];

				create_user($username,$password,$email,$rechten);		
			}else {echo ("foute parameters");}
			break;
   				
   		case get_medicatie:
			if(isset($_POST['clientnaam']))
			{
				$input = $_POST['clientnaam'];
	
				get_medicatie($input);
			}
			break;
		
		case set_medicatie:
			if(isset($_POST['clientnaam'],$_POST['medicijnen']))
			{
				$input = $_POST['clientnaam'];
				$input2 = $_POST['medicijnen'];
	
				set_medicatie($input,$input2);
			}
			break;
		
		case set_dispenserstate:
			if(isset($_POST['clientnaam'],$_POST['state']))
			{
				$input = $_POST['clientnaam'];
				$input2 = $_POST['state'];
	
				set_dispenserstate($input,$input2);
			}
			break;	
			
		case get_dispenserstate:
			if(isset($_POST['clientnaam']))
			{
				$input = $_POST['clientnaam'];
	
				get_dispenserstate($input);
			}
			break;	
			
		case add_dispenser:
			if(isset($_POST['clientnaam']))
			{
				$input = $_POST['clientnaam'];
	
				add_dispenser($input);
			}
			break;
		
		case add_client:
			if(isset($_POST['kamerid'],$_POST['clientnaam'],$_POST['geboortedatum'],$_POST['geboorteplaats'],
			$_POST['aantalmedicijnen']))
			{
				$input1 =	$_POST['kamerid'];
				$input2 =	$_POST['clientnaam'];
				$input3 =	$_POST['geboortedatum'];
				$input4 =	$_POST['geboorteplaats'];
				$input5 =	$_POST['aantalmedicijnen'];
				
				add_client($input1,$input2,$input3,$input4,$input5);
			}
			break;
			
		case delete_client:
			if(isset($_POST['clientnaam']))
			{
				$input = $_POST['clientnaam'];
	
				delete_client($input);
			}
			break;
		
		case delete_dispenser:
			if(isset($_POST['clientnaam']))
			{
				$input = $_POST['clientnaam'];
	
				delete_dispenser($input);
			}
			break;
		case get_dispensererrors:
			get_dispensererrors();
			break;	
				
		
			
  		default:
    		echo("No function called");
			break;
	}
}

?>