<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<body>

<header>
    SignsControl

    <a href="${pageContext.request.contextPath}/">main page</a>
    <a href="${pageContext.request.contextPath}/pdd_signs">pdd signs</a>
    <a href="${pageContext.request.contextPath}/standard_sizes">standard sizes</a>
    <a href="${pageContext.request.contextPath}/bank_accounts">bank</a>
    <a href="${pageContext.request.contextPath}/orders">orders</a>
    <a href="${pageContext.request.contextPath}/users">users</a>
    <a href="${pageContext.request.contextPath}/user_profile">user profile</a>
    <a href="${pageContext.request.contextPath}/organisations">organisations</a>
    <a href="${pageContext.request.contextPath}/workers_crews">workers crews</a>

    <c:choose>

    <c:when test="${empty sessionScope.role}">

    <p><a href="${pageContext.request.contextPath}/login">log in</a>

        </c:when>


        <c:otherwise>

    <p> User: ${sessionScope.username} <a href="${pageContext.request.contextPath}/logout">log out</a>

        </c:otherwise>

        </c:choose>
</header>

</body>

</html>