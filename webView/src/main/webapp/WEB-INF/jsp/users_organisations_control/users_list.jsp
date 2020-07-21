<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Users</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<a href="${pageContext.request.contextPath}/users">show all</a>
<a href="${pageContext.request.contextPath}/add_user">add user</a>

<form action='${pageContext.request.contextPath}/users' method="get" id='show_organisations' accept-charset="UTF-8">

    <select name="id" required>

        <c:forEach var="organisation" items='${organisations}'>

            <option value='${organisation.id}'>${organisation.name}</option>

        </c:forEach>

    </select>

    <br>
    <input type="submit" value="submit">

</form>
</p>


<p>

    <c:forEach var="user" items='${users}'>


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

<form action='${pageContext.request.contextPath}/change_user_form_handler' method="post" id='change_user'
      accept-charset="UTF-8">


    <input type="text" id="userID" name="id" value='${user.id}' hidden required>



    <input type="checkbox" id="setRole" name="setRole">
    <label for="setRole">setRole</label>


    <select name="role" required>

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    </select>


    <input type="checkbox" id="setName" name="setName">
    <label for="setName">setName</label>


    <label for="name">name: </label> <input type="text" id="name" name="name"
                                            value='${user.name}' required>


    <input type="checkbox" id="setSurname" name="setSurname">
    <label for="setSurname">setSurname</label>


    <label for="surname">: </label> <input type="text" id="surname" name="surname"
                                           value='${user.surname}' required>


    <input type="checkbox" id="setOrganisation" name="setOrganisation">
    <label for="setOrganisation">setOrganisation</label>


    <select name="organisation" required>

        <c:forEach var="organisation" items='${organisations}'>

            <option value='${organisation.id}'>${organisation.name}</option>

        </c:forEach>

    </select>


    <input type="checkbox" id="setInfo" name="setInfo">
    <label for="setInfo">setInfo</label>

    <label for="info">info: </label> <input type="text" id="info" name="info"
                                            value='${user.info}'>

    <c:if test='${user.block==true}'>
        <label for="unblock">разблокировать</label>
        <input type="radio" id="unblock" value='false' name="block">
    </c:if>

    <c:if test='${user.block==false}'>
        <label for="block">заблокировать</label>
        <input type="radio" id="block" value='true' name="block">
    </c:if>


    <br><input type="reset" value="Reset">
    <input type="submit" value="submit">

</form>


<br>


</c:forEach>


</body>

</html>