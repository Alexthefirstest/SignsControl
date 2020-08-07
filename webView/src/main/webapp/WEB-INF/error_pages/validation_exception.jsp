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

    <title>simple problem</title>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


</head>

<body>
<jsp:include page="../jsp/header.jsp"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/validation_exception.css?5">

	<div id="notfound">
		<div class="notfound">
			<div class="notfound-404">
				<h1><fmt:message key="label.error" /></h1>
			</div>
			<h2><fmt:message key="label.problem_during_execution" />:</h2>
			<br>
			<p>${message}</p>

		</div>
	</div>

</body> <%--  This templates was made by Colorlib (https://colorlib.com)--%>

</html>
