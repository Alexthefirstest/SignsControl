<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Add user</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<form action='${pageContext.request.contextPath}/add_user_form_handler' method="post">

    <label for="fieldUserPass">Login:</label><br><input type="text" id="fieldUserPass" name="login"
                                                        pattern="\w+"
                                                        placeholder="max - 20 symbols" maxlength="20"
                                                        required>
    <br><br>
    <label for="fieldPasswordLog">Password:</label><br><input type="password" id="fieldPasswordLog"
                                                              name="password" pattern="\w+"
                                                              placeholder="max - 20 symbols" maxlength="20"
                                                              required>

    <select name="role" required>

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    </select>

    <label for="name">name: </label> <input type="text" id="name" name="name"
                                            required>

    <label for="surname">: </label> <input type="text" id="surname" name="surname"
                                           required>

    <select name="organisation" required>

        <c:forEach var="organisation" items='${organisations}'>

            <option value='${organisation.id}'>${organisation.name}</option>

        </c:forEach>

    </select>

    <label for="info">info: </label> <input type="text" id="info" name="info">

    <br><input type="reset" value="сбросить">
    <input type="submit" value="submit">

</form>

</body>


</html>