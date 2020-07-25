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

<br>
<br>
<a href="${pageContext.request.contextPath}/users">show all</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/add_user">add user</a>
<br>
<br>

 <label for="show_organisations">Показат по организации</label>
<form action='${pageContext.request.contextPath}/users' method="get" id='show_organisations' accept-charset="UTF-8">

 <label for="show_organisations_select">организация</label>
    <select name="id" required id="show_organisations_select">

        <c:forEach var="organisation" items='${organisations}'>

            <option value='${organisation.id}'>${organisation.name}</option>

        </c:forEach>

    </select>

    <br>
    <input type="submit" value="применить">

</form>
</p>

<br>
<br>

<table>
<thead>


<tr>


<th scope="col">id</th>
<th scope="col">login</th>
<th scope="col">name</th>
<th scope="col">surname</th>
<th scope="col">role</th>
<th scope="col">organisation</th>
<th scope="col">block condition</th>
<th scope="col">info</th>

</tr>


</thead>


<tbody>

    <c:forEach var="user" items='${users}'>


<tr>

<td>${user.id}</td>
<td><a href="${pageContext.request.contextPath}/user_profile/${user.id}" >${user.login}</a></td>
<td>${user.name}</td>
<td>${user.surname}</td>
<td>${user.role.role}</td>
<td>${user.organisation.name}</td>
<td>

<c:choose>
    <c:when test="${user.block=='true'}">

 <h4 style="color: red">заблокирован</h4>

    </c:when>

    <c:otherwise>

  <h4 style="color: green">не заблокирован</h4>

    </c:otherwise>

</c:choose>

</td>
<td>${user.info}</td>

</tr>

</c:forEach>


</body>

</html>