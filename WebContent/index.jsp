<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"	content="text/html; charset=windows-1256">
<title>Login Page</title>
</head>

<body>
<form action="LoginServlet" method="post">
    Enter your name: <input type="text" name="username" size="20"><br>
    Enter your password:<input type="password" name="password"/><br>
    <input type="submit" value="Call Servlet" />
</form>
</body>
</html>