<!doctype html>
<html lang="en">
<body>
	<div id="info">
		<form action="dashboardServlet" method="post">
			<input type="hidden" name="url" value="transaction"/>
			<input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"  />
			Moeny:<input type="text" name="money"/>
			<input type="submit">
		</form>
	</div>
</body>
</html>