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
<br>
 <label for="add_org"><h3>Добавить организацию:</h3></label>
<form action='${pageContext.request.contextPath}/add_organisation_form_handler' method="post" id='add_org'
      accept-charset="UTF-8">

 <label for="nameAdd">название: </label> <input type="text" id="nameAdd" name="name"
                                            required pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

 <label for="roleAdd">роль</label>
    <select name="role" required> id="roleAdd"

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    </select>



    <label for="infoAdd">информация: </label> <input type="text" id="infoAdd" name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

<br><input type="reset" value="Reset">
<input type="submit" value="добавить организация">
</form>
<br><hr>
<br>
  <label for="transactionForm"><h3>Выполнить транзакцию:<h3></label>
<form action='${pageContext.request.contextPath}/execute_transaction' method="post" id="transactionForm">

    <label for="organisation_idtr"> получатель:</label><select name="organisationID" id='organisation_idtr'
                                                               required>

    <c:forEach var="organisation" items='${organisations}'>

        <option value='${organisation.id}'>${organisation.name}</option>

    </c:forEach>
</select>

    <label for="priceCh">    Сумма: </label> <input type="text" id="priceCh" name="money" pattern="\d+(.\d*)?" required>

 <br><input type="reset" value="сбросить">
    <input type="submit" value="отправить">

</form>
<br><hr><br><br>
<a href="${pageContext.request.contextPath}/organisations">Показать все организации</a>
<br>
<br><br>
 <label for="show_org_with_role"><h3>Показать организации по роли: </h3></label>
<form action='${pageContext.request.contextPath}/organisations' method="get" id='show_org_with_role'
      accept-charset="UTF-8">

     <label for="roleToShow">    роль: </label><select name="role" required id="roleToShow">

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    <br><input type="reset" value="Reset">
    <input type="submit" value="показать">

</form>

<p>







<br>
<table>
<thead>


<tr>


<th scope="col">id</th>
<th scope="col">название</th>
<th scope="col">роль</th>
<th scope="col">состояние</th>
<th scope="col">информация</th>
<th scope="col">изменить организацию</th>

</tr>


</thead>


<tbody>

<c:forEach var="organisation" items='${organisations}'>

<tr>

<td>${organisation.id}</td>
<td>${organisation.name}</td>
<td>${organisation.role.role}</td>
<td>${organisation.blocked}


<c:choose>
    <c:when test="${organisation.blocked}=='true'}">

 <h4 style="color: red">заблокирован</h4>

    </c:when>

    <c:otherwise>

  <h4 style="color: green">не заблокирован</h4>

    </c:otherwise>

</c:choose>

</td>
<td>${organisation.info}</td>
<td>
    <form action='${pageContext.request.contextPath}/change_organisation_form_handler' method="post" id='change_organisation'
          accept-charset="UTF-8">

        <input type="text" id="orgID" name="id" value='${organisation.id}' hidden required>


    <label for="setRole">изменить роль</label>
    <input type="checkbox" id="setRole" name="setRole">

   <select name="role" required>

            <c:forEach var="role" items='${roles}'>

                <option value='${role.id}'>${role.role}</option>

            </c:forEach>

        </select>
     <br>
     <br>


    <label for="setName">изменить название</label>
    <input type="checkbox" id="setName" name="setName">

        <label for="name">name: </label> <input type="text" id="name" name="name"
                                                value='${organisation.name}' required>
 <br>
     <br>
    <label for="setInfo">изменить информацию</label>
    <input type="checkbox" id="setInfo" name="setInfo">

  <label for="info">info: </label> <input type="text" id="info" name="info"
                                                value='${organisation.info}'>



 <br>
     <br>
        <c:if test='${organisation.blocked==true}'>
            <label for="unblock">разблокировать</label>
            <input type="radio" id="unblock" value='false' name="block">
        </c:if>

        <c:if test='${organisation.blocked==false}'>
            <label for="block">заблокировать</label>
            <input type="radio" id="block" value='true' name="block">
        </c:if>


        <br><input type="reset" value="сбросить">
        <input type="submit" value="изменить">

    </form>
    </td>
</tr>
</c:forEach>


</tbody>
</table>

</body>

</html>