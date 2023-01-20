<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<header>
		<t:logo></t:logo>
		<t:navbar></t:navbar>
	</header>

	<jsp:useBean id="user" class="user.UserBean">
		<jsp:setProperty name="user" property="name" value="Scab" />
	</jsp:useBean>

	<jsp:getProperty name="user" property="name" />
</body>
<t:footer></t:footer>
</html>