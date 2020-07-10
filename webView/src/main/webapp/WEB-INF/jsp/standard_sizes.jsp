<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>StandardSizes</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="header.jsp"/>
<p><a href="${pageContext.request.contextPath}/add_standard_size">add st size</a>

    <c:forEach var="standard_size" items='${standard_sizes}'>


        <c:out value='${standard_size.size}'/>
        <c:out value='        '/>
        <c:out value='${standard_size.info}'/>

<form action='${pageContext.request.contextPath}/change_standard_size' method="get">
    <input type="text" id="size" name="size" value='${standard_size.size}' hidden>
    <input type="submit" value="change size">
</form>
<form action='${pageContext.request.contextPath}/remove_standard_size' method="post">
    <input type="text" name="size" value='${standard_size.size}' hidden>
    <input type="submit" value="remove st size">
</form>

<br>


</c:forEach>


</body>

</html>