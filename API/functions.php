<?php

include('config.php');
include('crypto.php');

function create_user($username,$password,$email,$rechten)
{
	#echo("fcuntion call");
	$username	=escape($username);
	$password	=escape($password);
	$email		=escape($email);
	$rechten 	=escape($rechten);
	$hashedpw	=create_hash($password);
	
	#echo("pre sql");
	
	$query = "INSERT INTO inloggegevens(Inlognaam, Wachtwoord, Email, Rechten) 
				VALUES ('$username','$hashedpw','$email', '$rechten')";
	#echo("pre sql oke");		
	
	if (!mysqli_query($GLOBALS['con'], $query)) 
	{
    	echo("unable to create user");
	}
	else
	{
		echo("user created");
	} 
	#echo ("final final");
}

function validate_user($username,$password)
{
	$password=escape($password);
	$username=escape($username);
	
	$query = "SELECT wachtwoord FROM inloggegevens WHERE inlognaam = '$username'";
	$result = $GLOBALS['con']->query($query);
	
	while($r = mysqli_fetch_array($result))
	 		{ 
   				$check = ($r['wachtwoord']); 
			} 
    $result = validate_password($password,$check);
	
    if ($result)
    {
		$query = "SELECT rechten from inloggegevens where inlognaam = '$username'";
		$result = $GLOBALS['con']->query($query);
	
		while($r = mysqli_fetch_array($result))
	 		{ 
   				echo ("Good:".$r['rechten']); 
			} 
    }
    else
    {
        echo "invalid username/password";
    }	
}

function get_client($input)
{
	$input = escape($input);
	
	if ($input == "all")
	{	
		$query ="SELECT * FROM clienten";
		$result = $GLOBALS['con']->query($query);
	
		while($r = mysqli_fetch_array($result))
	 		{ 
   				echo ($r['KamerID'].":" .$r['Naam']. ":\r\n"); 
			} 
	}
	
	else
	{		
		$query ="SELECT * FROM clienten WHERE Naam = '$input'";
		$result = $GLOBALS['con']->query($query);
		
		while($r = mysqli_fetch_array($result))
	 		{ 
   				echo ($r['KamerID'].":" .$r['Naam']. ":\r\n"); 
			} 
	}
}

function get_medicatie($input)
{
	$input = escape($input);
	if($input == "all")
	{
		$query =	"SELECT clienten.Naam, dispensers.AantalMedicijnen 
					FROM clienten 
					INNER JOIN dispensers ON 														   						        			clienten.KamerID=dispensers.KamerID 
					ORDER BY dispensers.AantalMedicijnen ASC";
	$result = $GLOBALS['con']->query($query);
		
		while($r = mysqli_fetch_array($result))
	 		{ 
   				echo ("Naam: ".$r['Naam']." aantalmedicijnen: " .$r['AantalMedicijnen']. "\r\n"); 
			} 
	}
	
	else
	{
		$query =	"SELECT clienten.Naam, dispensers.AantalMedicijnen 
					FROM clienten 
					INNER JOIN dispensers ON 														   						        			clienten.KamerID=dispensers.KamerID 
					WHERE clienten.Naam = '$input'
					ORDER BY dispensers.AantalMedicijnen ASC";
	$result = $GLOBALS['con']->query($query);
		
		while($r = mysqli_fetch_array($result))
	 		{ 
   				echo ($r['AantalMedicijnen']. "\r\n"); 
			} 
		
		 
	}
}

function set_medicatie($naam,$medicijnen){
	$naam = escape($naam);
	$medicijnen = escape($medicijnen);
	
	$query = 		"UPDATE dispensers
					SET AantalMedicijnen='$medicijnen'
					WHERE KamerID=(SELECT KamerID FROM clienten WHERE naam='$naam')";
					
	$result = $GLOBALS['con']->query($query);
	
	if (!mysqli_query($GLOBALS['con'], $query)) 
	{
    	echo("error");
	}
	else
	{
		echo("good");
	} 

}

function set_dispenserstate($naam,$state)
{
	$naam = escape($naam);
	$state = escape($state);
	
	$query = 	"UPDATE dispensers
				SET Status='$state'
				WHERE KamerID=(SELECT KamerID FROM clienten WHERE naam='$naam')";
					
	$result = $GLOBALS['con']->query($query);
		
	if (!mysqli_query($GLOBALS['con'], $query)) 
	{
    	echo("error");
	}
	else
	{
		echo("good");
	} 
}
function get_dispensererrors()
{
	$query =		"SELECT clienten.Naam, dispensers.status 
					FROM clienten INNER JOIN dispensers ON clienten.KamerID=dispensers.KamerID 
					WHERE NOT dispensers.status = 'Goed' ORDER BY dispensers.status ASC";
					
	$result = $GLOBALS['con']->query($query);
		
		while($r = mysqli_fetch_array($result))
	 		{ 
   				echo ("Naam: ".$r['Naam']. "\rDispenser status: " .$r['status']. "\r\r\n"); 
			} 

	
	
}	
function get_dispenserstate($naam)
{
	$naam = escape($naam);
	
	$query =		"SELECT clienten.Naam, dispensers.status
					FROM clienten 
					INNER JOIN dispensers ON 														   						        			clienten.KamerID=dispensers.KamerID 
					WHERE clienten.Naam = '$naam'
					ORDER BY dispensers.status ASC";
					
	$result = $GLOBALS['con']->query($query);
		
		while($r = mysqli_fetch_array($result))
	 		{ 
   				echo ($r['status']. "\r\n"); 
			} 
}	

function add_dispenser($naam)
{
	$naam = escape($naam);
	
	$query =	"insert into dispensers (DispenserID, Status, KamerID) 
				Values (NULL, 'Nieuw', (Select KamerID from clienten Where Naam='$naam'))";

		
	if (!mysqli_query($GLOBALS['con'], $query)) 
	{
    	echo("error");
	}
	else
	{
		echo("good");
	} 
}

function delete_dispenser($naam)
{
	$naam = escape($naam);
	
	$query =	"DELETE FROM dispensers
				WHERE KamerID=(Select KamerID From clienten WHERE Naam='$naam')";
	
	$result = $GLOBALS['con']->query($query);

		
	if (!mysqli_query($GLOBALS['con'], $query)) 
	{
    	echo("error");
	}
	else
	{
		echo("good");
	} 
}

function delete_client($naam)
{
	$naam = escape($naam);
	
	$query =	"DELETE FROM clienten
				WHERE Naam='$naam'";
	
	$result = $GLOBALS['con']->query($query);

		
	if (!mysqli_query($GLOBALS['con'], $query)) 
	{
    	echo("error");
	}
	else
	{
		echo("good");
	} 
}

function add_client($kamerid,$naam,$geboortedatum,$geboorteplaats,$aantalmedicijnen)
{
	$kamerid		 = escape($kamerid);
	$naam			 = escape($naam);
	$geboortedatum	 = escape($geboortedatum);
	$geboorteplaats	 = escape($geboorteplaats);
	$aantalmedicijnen = escape($aantalmedicijnen);
	
	
	$query =	"INSERT INTO clienten(KamerID,Naam,Achternaam,GeboorteDatum,GeboortePlaats,AantalMedicijnen) 
				VALUES ('$kamerid','$naam','','$geboortedatum','$geboorteplaats','$aantalmedicijnen')";

		
	if (!mysqli_query($GLOBALS['con'], $query)) 
	{
    	echo("error");
	}
	else
	{
		echo("good");
	} 
}


function connect($host, $username, $password, $database)
{
	$GLOBALS['con'] = mysqli_connect($host, $username, $password, $database);
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
}


function escape($string)
{
	$string = mysqli_real_escape_string($GLOBALS['con'], $string);
	
	return $string;
}


?>
