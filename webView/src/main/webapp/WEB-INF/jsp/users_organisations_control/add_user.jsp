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

    <title>Add user</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<form action='${pageContext.request.contextPath}/add_user_form_handler' method="post" class="fullwidthblock center">

    <label for="fieldUserPass"><fmt:message key="label.login" />:</label><br><input class="fullwidthblock" type="text" id="fieldUserPass" name="login"
                                                        pattern="\w+"
                                                        placeholder=<fmt:message key="label.max_20_symbols" /> maxlength="20"
                                                        required>
    <br><br>
    <label for="fieldPasswordLog"><fmt:message key="label.password" />:</label><br><input class="fullwidthblock" type="password" id="fieldPasswordLog"
                                                              name="password" pattern="\w+"
                                                              placeholder=<fmt:message key="label.max_20_symbols" /> maxlength="20"
                                                    required>
 <br><br>
 <label for="role_user"><fmt:message key="label.role" />:</label>
    <select name="role" required id="role_user">

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    </select>

    <label for="name"><fmt:message key="label.user_name" />: </label> <input class="fullwidthblock" type="text" id="name" name="name"
                                            required>
<br>
    <label for="surname"><fmt:message key="label.surname" />: </label> <input  class="fullwidthblock" type="text" id="surname" name="surname"
                                           required>
<br>
 <label for="userOrg"><fmt:message key="label.organisation" />: </label>
    <select name="organisation" required id="userOrg">

        <c:forEach var="organisation" items='${organisations}'>

            <option value='${organisation.id}'>${organisation.name}</option>

        </c:forEach>

    </select>

<br>
    <label for="info">информация: </label> <textarea id="info" name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+"></textarea>

    <br><input type="reset" value=<fmt:message key="label.reset" /> class="registerbtn">
    <input type="submit" value=<fmt:message key="label.add" /> class="registerbtn">

</form>

</body>


</html>