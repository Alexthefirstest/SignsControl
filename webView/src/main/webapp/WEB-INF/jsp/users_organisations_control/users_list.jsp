<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:choose>

        <c:when test="${empty sessionScope.locale}">

        <fmt:setLocale value="ru"/>

            </c:when>

            <c:otherwise> <fmt:setLocale value="${sessionScope.locale}"/> </c:otherwise>

           </c:choose>

<fmt:setBundle basename="messages"/>
<html>

<head>

    <title>Users</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<br>
<br>

 <c:if test="${sessionScope.organisationRole==5}">
 <p>
<a href="${pageContext.request.contextPath}/users"><fmt:message key="label.show.all.he" /> </a>
<br>
<br>
<a href="${pageContext.request.contextPath}/add_user"><fmt:message key="label.add.user" /></a>
<br>
<br>
<hr>

 <label for="show_organisations"><fmt:message key="label.show.by_organisation" /></label>
<form action='${pageContext.request.contextPath}/users' method="get" id='show_organisations' accept-charset="UTF-8" class="auto_center center">

 <label for="show_organisations_select"><fmt:message key="label.organisation" /></label>
    <select name="id" required id="show_organisations_select">

        <c:forEach var="organisation" items='${organisations}'>

            <option value='${organisation.id}'>${organisation.name}</option>

        </c:forEach>

    </select>

    <br>
    <input type="submit" value=<fmt:message key="label.accept" />>

</form>
</p>
 </c:if>
<hr>
<br>
<br>

<table class="auto_center center">
<thead>


<tr>


<th scope="col">id</th>
<th scope="col"><fmt:message key="label.login" /></th>
<th scope="col"><fmt:message key="label.user_name" /></th>
<th scope="col"><fmt:message key="label.surname" /></th>
<th scope="col"><fmt:message key="label.role" /></th>
<th scope="col"><fmt:message key="label.organisation" /></th>
<th scope="col"><fmt:message key="label.block_condition" /></th>
<th scope="col"><fmt:message key="label.info" /></th>

</tr>


</thead>


<tbody>

    <c:forEach var="user" items='${users}'>


<tr>

<td>${user.id}</td>
<td>${user.login}
<br>

<c:if test="${ (user.organisation.role.id==5  && sessionScope.organisationRole==5 && sessionScope.userRole==2) || (user.organisation.role.id!=5 && ( (sessionScope.userRole==2 &&  sessionScope.organisationID==user.organisation.id) || (sessionScope.organisationRole==5 && user.role.id==2) ) ) }">

<a href="${pageContext.request.contextPath}/user_profile/${user.id}" ><fmt:message key="label.user_profile" /></a></td>

</c:if>

<td>${user.name}</td>
<td>${user.surname}</td>
<td>${user.role.role}</td>
<td>${user.organisation.name}</td>
<td>

<c:choose>
    <c:when test="${user.block=='true'}">

 <h4 style="color: red"><fmt:message key="label.block" /></h4>

    </c:when>

    <c:otherwise>

  <h4 style="color: green"><fmt:message key="label.active" /></h4>

    </c:otherwise>

</c:choose>

</td>
<td>${user.info}</td>

</tr>

</c:forEach>


</body>

</html>