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

    <title>Add bank account</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<table class="auto_center center">
<thead>

<tr>

<th scope="col"><fmt:message key="label.organisation_name" /></th>
<th scope="col"><fmt:message key="label.block_condition" /></th>
<th scope="col"><fmt:message key="label.info" /></th>
<th scope="col"><fmt:message key="label.add" /> <fmt:message key="label.bank_account" /></th>

</tr>

</thead>

<tbody>

<c:forEach var="organisation" items='${organisations}'>

<tr>
   <td>${organisation.name}</td>


   <c:choose>

       <c:when test="${organisation.blocked=='true'}">

   <td style="color: red" ><fmt:message key="label.block" /></td>

       </c:when>

       <c:otherwise>
      <td style="color: green" ><fmt:message key="label.active" /></td>

       </c:otherwise>

   </c:choose>



  <td>${organisation.info}</td>

<td>
    <form action='${pageContext.request.contextPath}/add_bank_account_form' method="post">
        <input type="text" id="orgID" name="orgID" value='${organisation.id}' hidden required>

     <p>   <label for="minBalance"><fmt:message key="label.min_balance" /></label> <input type="text" id="minBalance"
                                                                             name="minBalance"
                                                                             pattern="-?\d+(\.\d*)?">
</p><p>
        <label for="blocked"><fmt:message key="label.block_action" />: </label> <input type="checkbox" id="blocked" name="blocked" value="true">
</p>
<p>
        <label for="orgInfo"><fmt:message key="label.info" />: </label> <input type="text" id="orgInfo" name="orgInfo"
                                                         pattern="[\wА-Яа-я\s:!.,)(-?\d]+">
</p>
        <input type="submit" value=<fmt:message key="label.add" />>
    </form>

    </td>

</tr>

</c:forEach>

</tbody>
</table>

</body>

</html>