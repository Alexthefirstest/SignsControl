<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>


<head>

    <title>login</title>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


</head>

<body>
<jsp:include page="header.jsp"/>


<div class="fullwidthblock center">
<h3 > Input login and password</h3>
<br>
<h3 > Use latin letters, numbers and '_'</h3>
<br><br>
<c:if test='${second_form==true}'>

   <h6 style="color: red; text-align: center" > wrong login or password </h6>

    <c:set var="second_form" scope="session" value="false"/>

</c:if>
</div>
<hr>

<form action='${pageContext.request.contextPath}/login_form' method="post" class="registration" class="center">
    <label for="fieldUser">Login:</label><br><input type="text" id="fieldUser" name="login"
                                                       pattern="\w+"
                                                       placeholder="max - 20 symbols" maxlength="20"
                                                       required autocomplete="off">
<br><br>
    <label for="fieldPassword">Password:</label><br><input type="password" id="fieldPassword"
                                                           name="password" pattern="\w+"
                                                           placeholder="max - 20 symbols" maxlength="20"
                                                           required autocomplete="new-password">

    <input type="submit" value="Log in" class="registerbtn">
</form>


</body>


</html>