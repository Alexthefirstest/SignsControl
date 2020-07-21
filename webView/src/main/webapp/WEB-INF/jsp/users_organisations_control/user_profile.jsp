<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>User profile</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>


<p>

    <c:out value='id : ${user.id}'/>
    <c:out value='  ||      '/>
    <c:out value='login: ${user.login}'/>
    <c:out value='    ||    '/>
    <c:out value='role : ${user.role.role}'/>
    <c:out value='    ||    '/>
    <c:out value='organisation: ${user.organisation.name}'/>
    <c:out value='    ||    '/>

    <c:out value='block: ${user.block}'/>
    <c:out value='   ||   '/>
    <c:out value='name: ${user.name}'/>
    <c:out value='   ||   '/>
    <c:out value='surname: ${user.surname}'/>
    <c:out value='   ||   '/>
    <c:out value='info: ${user.info}'/>


<form action='${pageContext.request.contextPath}/change_login_password/login' method="post">
    <label for="fieldUser">Login:</label><br><input type="text" id="fieldUser" name="login"
                                                    pattern="\w+"
                                                    placeholder="max - 20 symbols" maxlength="20"
                                                    required>
    <br><br>
    <label for="fieldPassword">Password:</label><br><input type="password" id="fieldPassword"
                                                           name="password" pattern="\w+"
                                                           placeholder="max - 20 symbols" maxlength="20"
                                                           required>

    <label for="newLogin">Login:</label><br><input type="text" id="newLogin" name="newLogin"
                                                   pattern="\w+"
                                                   placeholder="max - 20 symbols" maxlength="20"
                                                   required>
    <br><input type="reset" value="Reset">
    <input type="submit" value=" set">
</form>

<form action='${pageContext.request.contextPath}/change_login_password/password' method="post">
    <label for="fieldUserPass">Login:</label><br><input type="text" id="fieldUserPass" name="login"
                                                        pattern="\w+"
                                                        placeholder="max - 20 symbols" maxlength="20"
                                                        required>
    <br><br>
    <label for="fieldPasswordLog">Password:</label><br><input type="password" id="fieldPasswordLog"
                                                              name="password" pattern="\w+"
                                                              placeholder="max - 20 symbols" maxlength="20"
                                                              required>
    <br><br>
    <label for="newPassword">Password:</label><br><input type="password" id="newPassword"
                                                         name="newPassword" pattern="\w+"
                                                         placeholder="max - 20 symbols" maxlength="20"
                                                         required>

    <br><input type="reset" value="Reset">
    <input type="submit" value="set">
</form>


</body>

</html>