<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Devine un nombre</title>
	</head>
	<body>
		<h1>Bienvenue dans notre jeu !</h1>
		<hr>
		<h2>${applicationScope.numberOfPlayers} joueurs connectés</h2>
		<hr>

	<form method="POST">
		<label>Ton prénom : <input name="playerName"></label>
		<input name="action" value="Connexion" type="SUBMIT">
	</form>
</body>
</html>
