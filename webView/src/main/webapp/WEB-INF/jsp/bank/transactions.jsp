<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="date" uri="/WEB-INF/tag/getDateTags.tld" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Transactions</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body class="auto_center center">
<jsp:include page="../header.jsp"/>

<a href="${pageContext.request.contextPath}/show_transactions_history">Обновить</a>
<br>
<hr>
<br>
<form action='${pageContext.request.contextPath}/show_transactions_history' method="get" id='show_form'
      accept-charset="UTF-8">

    <label for="findByOrgID">Найти по организцаиям: </label>
    <input type="radio" id="findByOrgID" name="findBy" value="OrgId">

    <label for="accountsFrom"> отправитель:</label>
    <select name="accountFrom" id='accountsFrom'>

        <c:forEach var="bank_account" items='${bank_accounts}'>

            <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

        </c:forEach>

    </select>


    <label for="accountsTo">получатель: </label>
    <select name="accountTo" id='accountsTo'>

        <option value='-1'>-</option>
        <c:forEach var="bank_account" items='${bank_accounts}'>

            <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

        </c:forEach>

    </select>
    <p>
        <label for="findByDate">Найти по дате: </label>
        <input type="radio" id="findByDate" name="findBy" value="date">


        <label for="accountsFromDate">отправитель:</label>
        <select name="accountFromDate" id='accountsFromDate'>

            <c:forEach var="bank_account" items='${bank_accounts}'>

                <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

            </c:forEach>

        </select>

        <label for="dateFrom"> дата с:</label>
        <input type="date" name="dateFrom" max=<date:getCurrentDate/> id="dateFrom" required>

        <label for="dateTo"> дата по:</label>
        <input type="date" name="dateTo" max=<date:getCurrentDate/> id="dateTo" required>


    </p>

    <br><input type="reset" value="очистить">
    <input type="submit" value="приенить">

</form>
</p>

<br>
<hr>
<br>
<table class="auto_center center">
<thead>

<tr>

<th scope="col">Отправитель название</th>
<th scope="col">Отправитель инфомрация</th>
<th scope="col">Получатель название</th>
<th scope="col">Получитель информация</th>
<th scope="col">Дата транзакции</th>
<th scope="col">Сумма</th>
</tr>

</thead>

<tbody>



    <c:forEach var="transaction" items='${transactions}'>
<tr>

<td>${transaction.from.name}</td>
<td>${transaction.from.info}</td>
<td>${transaction.to.name}</td>
<td>${transaction.to.info}</td>
<td>${transaction.date}</td>
<td>${transaction.money}</td>

</tr>

    </c:forEach>

</tbody>
</table>

</body>

</html>