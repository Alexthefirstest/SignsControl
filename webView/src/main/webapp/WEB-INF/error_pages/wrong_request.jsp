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

    <title>404</title>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


		 <!-- <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>-->
		<!--  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>-->

</head>

<body>
<jsp:include page="../jsp/header.jsp"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/404.css?4">

	<div id="notfound">
		<div class="notfound">
			<div class="notfound-404"></div>
			<h1>404</h1>
			<h2><fmt:message key="label.page_not_found" /></h2>
			<p><fmt:message key="label.page_never_exist" /></p>

		</div>
	</div>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
