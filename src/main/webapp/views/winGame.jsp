<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Number Guessing game</title>
	</head>
	<body>
		<hr>
		<h2>${applicationScope.numberOfPlayers} joueurs connectés</h2>
		<hr>

		<h3>Bravo, tu as gagné !</h3>
		<form method="POST">
			<input type="SUBMIT" name="action" value="Déconnexion"/>
			<input type="SUBMIT" name="action" value="Rejouer"/>
		</form>
	</body>
</html>
