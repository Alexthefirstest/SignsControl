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

    <title>StandardSizes</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<c:if test="${sessionScope.organisationRole==1}">
<p><a href="${pageContext.request.contextPath}/add_standard_size"><fmt:message key="label.add"/> <fmt:message key="label.standard_size"/> </a>
  </c:if>

<table class="auto_center center">

<thead>


<tr>


<th scope="col"><fmt:message key="label.standard_size"/></th>
<th scope="col"><fmt:message key="label.description"/></th>

  <c:if test="${sessionScope.organisationRole==1}">
<th scope="col"><fmt:message key="label.change"/> <fmt:message key="label.standard_size"/></th>
  </c:if>

</tr>


</thead>


<tbody>


    <c:forEach var="standard_size" items='${standard_sizes}'>
<tr>
<td>${standard_size.size}</td>
<td>${standard_size.info}</td>


  <c:if test="${sessionScope.organisationRole==1}">
<td>

<form action='${pageContext.request.contextPath}/change_standard_size' method="post" >
    <input type="text" id="size" name="size" value='${standard_size.size}' hidden>
    <input type="submit" value='<fmt:message key="label.change"/> <fmt:message key="label.standard_size"/>'>
</form>

<form action='${pageContext.request.contextPath}/remove_standard_size' method="post">
    <input type="text" name="size" value='${standard_size.size}' hidden>
    <input type="submit" value='<fmt:message key="label.delete"/> <fmt:message key="label.standard_size"/>'>
</form>

</td>
  </c:if>


</tr>
</c:forEach>

</tbody>
</table>

</body>

</html>