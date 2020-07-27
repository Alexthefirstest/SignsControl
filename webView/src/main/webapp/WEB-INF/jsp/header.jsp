<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<body>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?1">

<header >

<br>
<br>
   <h1 style=" float: left;"> SignsControl </h1>




<table style=" float: right; ">
<tbody>
<tr>

<td>
<img src="${pageContext.request.contextPath}/images/headerImage.jpg" style="width: 150px; height: 75px;">
</td>

<td>



  <a href="${pageContext.request.contextPath}/" >main page</a>

</td>

<td>

  <c:choose>

        <c:when test="${empty sessionScope.role}">

        <a href="${pageContext.request.contextPath}/login" >log in</a>

            </c:when>


            <c:otherwise>

       ${sessionScope.username} <br><a href="${pageContext.request.contextPath}/logout" >log out</a>
<br>
    <a href="${pageContext.request.contextPath}/user_profile/${sessionScope.userID}" >user profile</a>

            </c:otherwise>

            </c:choose>


</td>







<td>

  <a href="${pageContext.request.contextPath}/pdd_signs" >pdd signs</a>
<br>
      <a href="${pageContext.request.contextPath}/standard_sizes" >standard sizes</a>
</td>

 <td>
    <a href="${pageContext.request.contextPath}/bank_accounts" >bank</a>
</td>

<td>
    <a href="${pageContext.request.contextPath}/users"  >users</a>
<br>
    <a href="${pageContext.request.contextPath}/organisations" >organisations</a>
</td><td>
    <a href="${pageContext.request.contextPath}/orders"  >orders</a>
<br>
    <a href="${pageContext.request.contextPath}/workers_crews" >workers crews</a>
 </td>



</tr>
</tbody>
</table>


</header>

</body>

</html>