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


<html >

<body>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?3">

<header >

<br>
<br>
   <h1 class ="inline"> SignsControl </h1>




<table class ="inline">
<tbody>
<tr>

<td>

</td>

<td>



  <a href="${pageContext.request.contextPath}/" ><fmt:message key="label.main_page" /></a>

</td>

<td>

  <c:choose>

        <c:when test="${empty sessionScope.role}">

        <a href="${pageContext.request.contextPath}/login" ><fmt:message key="label.log_in" /></a>

            </c:when>


            <c:otherwise>

       ${sessionScope.username} <br><a href="${pageContext.request.contextPath}/logout" ><fmt:message key="label.log_out" /></a>
<br>
    <a href="${pageContext.request.contextPath}/user_profile/${sessionScope.userID}" ><fmt:message key="label.user_profile" /></a>

            </c:otherwise>

            </c:choose>


</td>







<td>

  <a href="${pageContext.request.contextPath}/pdd_signs" ><fmt:message key="label.pdd_signs" /></a>
<br>
      <a href="${pageContext.request.contextPath}/standard_sizes" ><fmt:message key="label.standard_sizes" /></a>
</td>

 <td>
    <a href="${pageContext.request.contextPath}/bank_accounts" ><fmt:message key="label.bank_accounts" /></a>
</td>

<td>
    <a href="${pageContext.request.contextPath}/users"  ><fmt:message key="label.users_list" /></a>
<br>
    <a href="${pageContext.request.contextPath}/organisations" ><fmt:message key="label.organisations_list" /></a>
</td><td>
    <a href="${pageContext.request.contextPath}/orders"  ><fmt:message key="label.orders" /></a>
<br>
    <a href="${pageContext.request.contextPath}/workers_crews" ><fmt:message key="label.workers_crews" /></a>
 </td>

<td>

    <form action='${pageContext.request.contextPath}/set_locale/ru' method="post" accept-charset="UTF-8">
        <input type="submit" value="рус.">
    </form>

   <form action='${pageContext.request.contextPath}/set_locale/en' method="post" accept-charset="UTF-8">
               <input type="submit" value="en">
           </form>
    <form action='${pageContext.request.contextPath}/set_locale/by' method="post" accept-charset="UTF-8">
               <input type="submit" value="бел.">
           </form>

 </td>

</tr>
</tbody>
</table>

<br><hr><br>
</header>

</body>

</html>