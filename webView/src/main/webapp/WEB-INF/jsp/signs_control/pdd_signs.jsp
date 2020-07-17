<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>PddSigns</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>
<p><a href="${pageContext.request.contextPath}/add_pdd_sign">add sign</a>

    <c:forEach var="sign" items='${pdd_signs}'>

    <c:choose>


    <c:when test='${sign.kind>-1}'>

        <c:out value='${sign.section}'/>
        <c:out value='.${sign.sign}'/>
        <c:out value='.${sign.kind}'/>
    </c:when>
    <c:otherwise>
        <c:out value='${sign.section}'/>
        <c:out value='.${sign.sign}'/>
    </c:otherwise>
    </c:choose>

        <c:out value='        '/>
        <c:out value='${sign.name}'/>
        <c:out value='        '/>
        <c:out value='${sign.description}'/>
        <c:out value='        '/>

 <c:if test="${not empty sign.picture}">
       <img src="${pageContext.request.contextPath}/upload?id=${sign.id}" />
</c:if>

<form action='${pageContext.request.contextPath}/change_pdd_sign' method="post">
    <input type="text" id="signID" name="signID" value='${sign.id}' hidden>
    <input type="submit" value="change sign">
</form>
<form action='${pageContext.request.contextPath}/remove_pdd_sign' method="post">
    <input type="text" name="signID" value='${sign.id}' hidden>
    <input type="submit" value="remove sign">
</form>

<br>

</c:forEach>

</body>

</html>