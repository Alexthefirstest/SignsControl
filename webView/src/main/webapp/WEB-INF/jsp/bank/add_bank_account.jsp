<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Add bank account</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>
<c:forEach var="organisation" items='${organisations}'>


    <c:out value='Org name: ${organisation.name}'/>
    <c:out value='        '/>
    <c:out value='${organisation.blocked}'/>
    <c:out value='        '/>
    <c:out value='${organisation.info}'/>


    <form action='${pageContext.request.contextPath}/add_bank_account_form' method="post">
        <input type="text" id="orgID" name="orgID" value='${organisation.id}' hidden required>


        <label for="minBalance">минимальный допустимый баланс</label> <input type="text" id="minBalance"
                                                                             name="minBalance"
                                                                             pattern="-?\d+(.\d*)?">


        <label for="blocked">заблокировать: </label> <input type="checkbox" id="blocked" name="blocked" value="true">


        <label for="orgInfo">информация: </label> <input type="text" id="orgInfo" name="orgInfo"
                                                         pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

        <input type="submit" value="добавить">
    </form>

</c:forEach>
</body>

</html>