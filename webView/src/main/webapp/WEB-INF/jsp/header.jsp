<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<body>

<header>

   <h1> SignsControl </h1>

 <h5>
    <a href="${pageContext.request.contextPath}/user_profile" style="text-align: left">user profile</a>
    <a href="${pageContext.request.contextPath}/" style="text-align: right">main page</a>

     <c:choose>

        <c:when test="${empty sessionScope.role}">

        <a href="${pageContext.request.contextPath}/login" style="text-align: center">log in</a>

            </c:when>


            <c:otherwise>

        User: ${sessionScope.username} <a href="${pageContext.request.contextPath}/logout" style="text-align: center">log out</a>

            </c:otherwise>

            </c:choose>

 </h5>

   <h5>
  <a href="${pageContext.request.contextPath}/pdd_signs" style="text-align: left">pdd signs</a>
      <a href="${pageContext.request.contextPath}/standard_sizes" style="text-align: right">standard sizes</a>
   </h5>

    <h5>
    <a href="${pageContext.request.contextPath}/bank_accounts">bank</a>
    </h5>

    <h5>
    <a href="${pageContext.request.contextPath}/users"  style="text-align: left">users</a>
    <a href="${pageContext.request.contextPath}/organisations" style="text-align: right">organisations</a>
</h5>

<h5>
    <a href="${pageContext.request.contextPath}/orders"  style="text-align: left">orders</a>
    <a href="${pageContext.request.contextPath}/workers_crews" style="text-align: right">workers crews</a>
</h5>

</header>

</body>

</html>