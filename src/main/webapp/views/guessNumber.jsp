<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Devine un nombre</title>
	</head>
	<body onload="document.guessForm.guess.focus()">
		<hr>
		<h2>${applicationScope.numberOfPlayers} joueurs connectés</h2>
		<hr>

		<h3>Hello ${sessionScope.playerName}, Devine mon nombre</h3>
		<c:if test="${not empty param.guess}">
			Essai n° <b>${sessionScope.attempts}</b><br/>
			${param.guess} : <b>${requestScope.message}</b>			
		</c:if>
		<h2>je pense à un nombre compris entre 0 et 100</h2>
		<form name="guessForm" method="POST" accept-charset="UTF-8" >
			<label>Ta proposition : <input type="number" min="0" max="100" required name="guess"></label> 
			<input type="SUBMIT" name="action" value="Deviner"><br/>
		</form>
		<form method="POST">
			<input type="SUBMIT" name="action" value="Déconnexion">
		</form>

		<hr>
		<c:if test="${not empty applicationScope.bestPlayer}">
			<h2>Score à battre : ${applicationScope.bestScore} essais par ${applicationScope.bestPlayer}</h2>
		</c:if>		
	</body>
</html>
