<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Organisations</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<form action='${pageContext.request.contextPath}/execute_transaction' method="post">

    <label for="organisation_idtr"> получатель:</label><select name="organisationID" id='organisation_idtr'
                                                               required>

    <c:forEach var="organisation" items='${organisations}'>

        <option value='${organisation.id}'>${organisation.name}</option>

    </c:forEach>

    <label for="priceCh">money: </label> <input type="text" id="priceCh" name="money" pattern="\d+(.\d*)?" required>

 <br><input type="reset" value="Reset">
    <input type="submit" value="послать деняк">
</select>
</form>

<a href="${pageContext.request.contextPath}/organisations">organisations</a>

<form action='${pageContext.request.contextPath}/organisations' method="get" id='show_org_with_role'
      accept-charset="UTF-8">

    <select name="role" required>

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    <br><input type="reset" value="Reset">
    <input type="submit" value="посмотреть по роли">

</form>

<p>

<form action='${pageContext.request.contextPath}/add_organisation_form_handler' method="post" id='add_org'
      accept-charset="UTF-8">

    <select name="role" required>

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    </select>

    <label for="nameAdd">name: </label> <input type="text" id="nameAdd" name="name"
                                            required>

    <label for="infoAdd">info: </label> <input type="text" id="infoAdd" name="info">

<br><input type="reset" value="Reset">
<input type="submit" value="добавить организация">
</form>





<br>

<c:forEach var="organisation" items='${organisations}'>


    <c:out value='id : ${organisation.id}'/>
    <c:out value='  ||      '/>
    <c:out value='name: ${organisation.name}'/>
    <c:out value='    ||    '/>
    <c:out value='role : ${organisation.role.role}'/>
    <c:out value='    ||    '/>
    <c:out value='block : ${organisation.blocked}'/>
    <c:out value='    ||    '/>
    <c:out value='info : ${organisation.info}'/>
    <c:out value='    ||    '/>

    <form action='${pageContext.request.contextPath}/change_organisation_form_handler' method="post" id='change_organisation'
          accept-charset="UTF-8">

        <input type="text" id="orgID" name="id" value='${organisation.id}' hidden required>


    <input type="checkbox" id="setRole" name="setRole">
    <label for="setRole">setRole</label>


        <select name="role" required>

            <c:forEach var="role" items='${roles}'>

                <option value='${role.id}'>${role.role}</option>

            </c:forEach>

        </select>


    <input type="checkbox" id="setName" name="setName">
    <label for="setName">setName</label>


        <label for="name">name: </label> <input type="text" id="name" name="name"
                                                value='${organisation.name}' required>


    <input type="checkbox" id="setInfo" name="setInfo">
    <label for="setInfo">setInfo</label>

        <label for="info">info: </label> <input type="text" id="info" name="info"
                                                value='${organisation.info}'>


        <c:if test='${organisation.blocked==true}'>
            <label for="unblock">разблокировать</label>
            <input type="radio" id="unblock" value='false' name="block">
        </c:if>

        <c:if test='${organisation.blocked==false}'>
            <label for="block">заблокировать</label>
            <input type="radio" id="block" value='true' name="block">
        </c:if>


        <br><input type="reset" value="Reset">
        <input type="submit" value="submit">

    </form>

</c:forEach>


</body>

</html>