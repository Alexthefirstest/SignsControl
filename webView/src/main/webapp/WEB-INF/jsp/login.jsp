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

<br>

<h7> Use latin letters and '_'</h7>

<br>

<form action='${pageContext.request.contextPath}/login_form' method="post">
    <label for="fieldUser">Login:</label><br><input type="text" id="fieldUser" name="login"
                                                       pattern="\w+"
                                                       placeholder="max - 20 symbols" maxlength="20"
                                                       required autocomplete="off">
    <br><br>
    <label for="fieldPassword">Password:</label><br><input type="password" id="fieldPassword"
                                                           name="password" pattern="\w+"
                                                           placeholder="max - 20 symbols" maxlength="20"
                                                           required autocomplete="new-password">
    <br><input type="reset" value="Reset">
    <input type="submit" value="Log in">
</form>

<c:if test='${second_form==true}'>

   <h6 style="color: red; text-align: center" > wrong login or password </h6>

    <c:set var="second_form" scope="session" value="false"/>

</c:if>


</body>


</html>