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

<table class="fullwidthblock">
<thead>

<tr>

<th scope="col">Название организации</th>
<th scope="col">Состояние</th>
<th scope="col">Информация</th>
<th scope="col">Добавить банковский аккаунт</th>

</tr>

</thead>

<tbody>

<c:forEach var="organisation" items='${organisations}'>

<tr>
   <td>${organisation.name}</td>


   <c:choose>

       <c:when test="${organisation.blocked=='true'}">

   <td style="color: red" >заблокирована</td>

       </c:when>

       <c:otherwise>
      <td style="color: green" >не заблокирована</td>

       </c:otherwise>

   </c:choose>



  <td>${organisation.info}</td>

<td>
    <form action='${pageContext.request.contextPath}/add_bank_account_form' method="post">
        <input type="text" id="orgID" name="orgID" value='${organisation.id}' hidden required>

     <p>   <label for="minBalance">минимальный допустимый баланс</label> <input type="text" id="minBalance"
                                                                             name="minBalance"
                                                                             pattern="-?\d+(\.\d*)?">
</p><p>
        <label for="blocked">заблокировать: </label> <input type="checkbox" id="blocked" name="blocked" value="true">
</p>
<p>
        <label for="orgInfo">информация: </label> <input type="text" id="orgInfo" name="orgInfo"
                                                         pattern="[\wА-Яа-я\s:!.,)(-?\d]+">
</p>
        <input type="submit" value="добавить">
    </form>

    </td>

</tr>

</c:forEach>

</tbody>
</table>

</body>

</html>