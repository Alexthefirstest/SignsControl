<%--
  Created by IntelliJ IDEA.
   User: Bulgak Alexander

--%>
<!DOCTYPE html>

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

    <title>add standard size</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>
<form action='${pageContext.request.contextPath}/add_standard_size_form' method="post" accept-charset="UTF-8" class="auto_center center">

    <label for="sizeID"> <fmt:message key="label.standard_size" />:</label>
    <input type="text" id="sizeID" name="size" pattern="\d+" required class="fullwidthblock">

    <label for="size_info"> <fmt:message key="label.description" />:</label>
    <input type="text" id="size_info" name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required class="fullwidthblock">

    <input type="submit" value=<fmt:message key="label.add" /> class="registerbtn">

</form>


</body>

</html>
