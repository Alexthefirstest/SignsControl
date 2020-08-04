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

    <title>Organisations</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body class="auto_center center">
<jsp:include page="../header.jsp"/>
<br>

  <c:if test="${sessionScope.organisationRole==5}">
 <label for="add_org"><h3><fmt:message key="label.add.organisation" />:</h3></label>
<form action='${pageContext.request.contextPath}/add_organisation_form_handler' method="post" id='add_org'
      accept-charset="UTF-8">

 <label for="nameAdd"><fmt:message key="label.name" />: </label> <input type="text" id="nameAdd" name="name"
                                            required pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

 <label for="roleAdd"><fmt:message key="label.role" /></label>
    <select name="role" required> id="roleAdd"

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    </select>



    <label for="infoAdd"><fmt:message key="label.info" />: </label> <input type="text" id="infoAdd" name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

<br><input type="reset" value=<fmt:message key="label.reset" />>
<input type="submit" value='<fmt:message key="label.add.organisation" />'>
</form>
  </c:if>
<br><hr>

<a href="${pageContext.request.contextPath}/organisations"><fmt:message key="label.show" /> <fmt:message key="label.all" /> <fmt:message key="label.organisation" /></a>
<br>
<br><br>
 <label for="show_org_with_role"><h3><fmt:message key="label.show.organisations_by_role" />: </h3></label>
<form action='${pageContext.request.contextPath}/organisations' method="get" id='show_org_with_role'
      accept-charset="UTF-8">

     <label for="roleToShow">    <fmt:message key="label.role" />: </label><select name="role" required id="roleToShow">

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    <br><input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.show" />>

</form>

<p>







<br>
<table>
<thead>


<tr>


<th scope="col">id</th>
<th scope="col"><fmt:message key="label.name" /></th>
<th scope="col"><fmt:message key="label.role" /></th>
<th scope="col"><fmt:message key="label.block_condition" /></th>
<th scope="col"><fmt:message key="label.info" /></th>

  <c:if test="${sessionScope.organisationRole==5}">
<th scope="col"><fmt:message key="label.change.organisation" /></th>
</c:if>

</tr>


</thead>


<tbody>

<c:forEach var="organisation" items='${organisations}'>

<tr>

<td>${organisation.id}</td>
<td>${organisation.name}</td>
<td>${organisation.role.role}</td>
<td>


<c:choose>
    <c:when test='${organisation.blocked==true}'>

 <h4 style="color: red"><fmt:message key="label.block" /></h4>

    </c:when>

    <c:otherwise>

  <h4 style="color: green"><fmt:message key="label.active" /></h4>

    </c:otherwise>

</c:choose>

</td>
<td>${organisation.info}</td>


  <c:if test="${sessionScope.organisationRole==5}">
<td>
    <form action='${pageContext.request.contextPath}/change_organisation_form_handler' method="post" id='change_organisation'
          accept-charset="UTF-8">

        <input type="text" id="orgID" name="id" value='${organisation.id}' hidden required>


    <label for="setRole"><fmt:message key="label.change" /> <fmt:message key="label.role" /></label>
    <input type="checkbox" id="setRole" name="setRole">

   <select name="role" required>

            <c:forEach var="role" items='${roles}'>

                <option value='${role.id}'>${role.role}</option>

            </c:forEach>

        </select>
     <br>
     <br>


    <label for="setName"><fmt:message key="label.change" /> <fmt:message key="label.name" /></label>
    <input type="checkbox" id="setName" name="setName">

        <label for="name"><fmt:message key="label.name" />: </label> <input type="text" id="name" name="name"
                                                value='${organisation.name}' required>
 <br>
     <br>
    <label for="setInfo"><fmt:message key="label.change.info" /></label>
    <input type="checkbox" id="setInfo" name="setInfo">

  <label for="info"><fmt:message key="label.info" />: </label> <input type="text" id="info" name="info"
                                                value='${organisation.info}'>



 <br>
     <br>
        <c:if test='${organisation.blocked==true}'>
            <label for="unblock"><fmt:message key="label.unblock_action" /></label>
            <input type="radio" id="unblock" value='false' name="block">
        </c:if>

        <c:if test='${organisation.blocked==false}'>
            <label for="block"><fmt:message key="label.block_action" /></label>
            <input type="radio" id="block" value='true' name="block">
        </c:if>


        <br><input type="reset" value=<fmt:message key="label.reset" />>
        <input type="submit" value=<fmt:message key="label.change" />>

    </form>
    </td>

  </c:if>
</tr>
</c:forEach>


</tbody>
</table>

</body>

</html>