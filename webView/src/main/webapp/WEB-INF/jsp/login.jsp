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

    <title>login</title>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


</head>

<body>
<jsp:include page="header.jsp"/>


<div class="fullwidthblock center">
<h3 ><fmt:message key="label.login.input" /></h3>
<br>
<h3 ><fmt:message key="label.login.symbols" /></h3>
<br><br>
<c:if test='${second_form==true}'>

   <h6 style="color: red; text-align: center" ><fmt:message key="label.login.wrong_data" /></h6>

    <c:set var="second_form" scope="session" value="false"/>

</c:if>

<c:if test='${second_form_block==true}'>

   <h6 style="color: red; text-align: center" ><fmt:message key="label.you_have_been_blocked" /></h6>

    <c:set var="second_form_block" scope="session" value="false"/>

</c:if>
</div>
<hr>

<form action='${pageContext.request.contextPath}/login_form' method="post" class="registration" class="center">
    <label for="fieldUser"><fmt:message key="label.login" />:</label><br><input type="text" id="fieldUser" name="login"
                                                       pattern="\w+"
                                                       placeholder="max - 20 symbols" maxlength="20"
                                                       required autocomplete="off">
<br><br>
    <label for="fieldPassword"><fmt:message key="label.password" />:</label><br><input type="password" id="fieldPassword"
                                                           name="password" pattern="\w+"
                                                           placeholder="max - 20 symbols" maxlength="20"
                                                           required autocomplete="new-password">

    <input type="submit" class="registerbtn" value=<fmt:message key="label.log_in" />>
</form>


</body>


</html>