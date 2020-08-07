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

    <title>access forbidden</title>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


</head>

<body id="accbody">
<jsp:include page="../jsp/header.jsp"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/403.css?10">
<br><br><br><br><br><br>
<h1 id="exp403"><span>403</span></h1>
<br>
<h2 class="text center"><fmt:message key="label.access_forbidden" /></h2>


</body>


</html>