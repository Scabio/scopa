<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<!DOCTYPE html>
<html lang="IT">
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
	<header>
		<t:logo></t:logo>
		<t:navbar></t:navbar>
	</header>
	<div class="Main">
		<div class="Form">
			Log in:
			<form action="./home" method="post">
				<div class="Input">
					<input type="text" placeholder="Nome" name="name">
				</div>
				<div class="Input">
					<input type="password" placeholder="Password" name="password">
				</div>
				<div class="Input">
					<input type="submit" value="Accedi">
				</div>
			</form>
		</div>
		<div class="Form">
			Sign in:
			<form action="./welcome" method="post">
				<div class="Input">
					<input type="text" placeholder="Nome">
				</div>
				<div class="Input">
					<input type="password" placeholder="Password">
				</div>
				<div class="Input">
					<input type="submit" value="Registrati">
				</div>
			</form>
		</div>
	</div>
	<t:footer></t:footer>
</body>
</html>