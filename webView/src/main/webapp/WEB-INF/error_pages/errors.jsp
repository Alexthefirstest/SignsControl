<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:choose>

        <c:when test="${empty sessionScope.locale}">

        <fmt:setLocale value="ru"/>

            </c:when>

            <c:otherwise> <fmt:setLocale value="${sessionScope.locale}"/> </c:otherwise>

           </c:choose>

<fmt:setBundle basename="messages"/>



<!DOCTYPE html>

<html>


<head>

    <title>Server error</title>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


</head>

<body>
<jsp:include page="../jsp/header.jsp"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/500.css?10">


<div class="wrapper">
<div class="box">
<br><br><br>
<h1 id="err500">500</h1>
<p class="ex"><fmt:message key="label.server_exception" /></p>

</div>
</div>


<!-- Error during ${pageContext.errorData.requestURI} request-->
<!-- <br/><br/><br/>-->
<!-- Status code: ${pageContext.errorData.statusCode}-->
<!-- <br/>-->
<!-- exception: ${pageContext.exception}-->
<!-- <br/>-->
<!-- message: ${pageContext.exception.message}-->


</body>
</html>
