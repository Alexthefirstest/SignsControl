<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Add organisation</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<p>

<form action='${pageContext.request.contextPath}/add_organisation_form_handler' method="post" id='add_org'
      accept-charset="UTF-8">

    <select name="role" required>

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    </select>

    <label for="name">name: </label> <input type="text" id="name" name="name"
                                            required>

    <label for="info">info: </label> <input type="text" id="info" name="info">

    <br><input type="reset" value="Reset">
    <input type="submit" value="submit">

</form>


<br>


</body>

</html>