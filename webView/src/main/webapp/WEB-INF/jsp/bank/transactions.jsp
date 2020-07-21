<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Transactions</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>


<form action='${pageContext.request.contextPath}/show_transactions_history' method="get" id='show_form'
      accept-charset="UTF-8">

    <label for="findByOrgID">find by org</label>
    <input type="radio" id="findByOrgID" name="findBy" value="OrgId">

    <label for="accountsFrom"> account from:</label>
    <select name="accountFrom" id='accountsFrom'>

        <c:forEach var="bank_account" items='${bank_accounts}'>

            <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

        </c:forEach>

    </select>


    <label for="accountsTo"> account to:</label>
    <select name="accountTo" id='accountsTo'>

        <option value='-1'>-</option>
        <c:forEach var="bank_account" items='${bank_accounts}'>

            <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

        </c:forEach>

    </select>
    <p>
        <label for="findByDate">find by date</label>
        <input type="radio" id="findByDate" name="findBy" value="date">


        <label for="accountsFromDate"> account from:</label>
        <select name="accountFromDate" id='accountsFromDate'>

            <c:forEach var="bank_account" items='${bank_accounts}'>

                <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

            </c:forEach>

        </select>

        <label for="dateFrom"> date from:</label>
        <input type="date" name="dateFrom" id="dateFrom">

        <label for="dateTo"> date to:</label>
        <input type="date" name="dateTo" id="dateTo">

        <input type="reset" id="resetButton">
    </p>

    <br><input type="reset" value="Reset">
    <input type="submit" value="submit">

</form>
</p>


<p>

    <c:forEach var="transaction" items='${transactions}'>


        <c:out value='from : ${transaction.from.name}'/>
        <c:out value='  ||      '/>
        <c:out value='from info: ${transaction.from.info}'/>
        <c:out value='    ||    '/>
        <c:out value='to : ${transaction.to.name}'/>
        <c:out value='    ||    '/>
        <c:out value='to info: ${transaction.to.info}'/>
        <c:out value='    ||    '/>

        <c:out value='date: ${transaction.date}'/>
        <c:out value='   ||   '/>
        <c:out value='money: ${transaction.money}'/>


    <br>


    </c:forEach>


</body>

</html>