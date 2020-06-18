<!DOCTYPE html>

<html>


<head>

    <title>login</title>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>

<h2> Log in <h2>
<h3> Use latin latters and '_' <h3>
<br>
<form action='${pageContext.request.contextPath}/login_form' method="post">
<label for ="fieldUser">Username:</label><br><input type="text" id="fieldUser" name="login" pattern="\w+"
placeholder="max - 20 symbols" maxlength="20" required>
<br><br>
<label for ="fieldPassword">Password:</label><br><input type="password" id="fieldPassword" name="password" pattern="\w+"
placeholder="max - 20 symbols" maxlength="20" required>
<br><input type="reset" value="Reset">
<input type="submit" value="Log in">
</form>

</body>


</html>