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

    <title>change standard size</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<form action='${pageContext.request.contextPath}/change_standard_size_form' method="post" accept-charset="UTF-8">

    <input type="text" name="old_size" value='${size}' pattern="\d+" hidden>

    <label for="sizeID"> <fmt:message key="label.new.he" /> <fmt:message key="label.standard_size" />:</label>
    <input type="text" id="sizeID" name="size" value='${size}' pattern="\d+" required>

    <label for="size_info"> <fmt:message key="label.description" />:</label>
    <input type="text" id="size_info" name="info" value='${info}' pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <input type="submit" value=<fmt:message key="label.change" />>

</form>


</body>

</html>
