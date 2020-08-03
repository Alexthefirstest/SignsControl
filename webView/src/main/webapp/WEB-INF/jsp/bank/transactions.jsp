<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="date" uri="/WEB-INF/tag/getDateTags.tld" %>


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

    <title>Transactions</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body class="auto_center center">
<jsp:include page="../header.jsp"/>

<a href="${pageContext.request.contextPath}/show_transactions_history"><fmt:message key="label.update" /></a>
<br>
<hr>
<br>


<c:choose>

        <c:when test="${sessionScope.organisationRole==4}">

<form action='${pageContext.request.contextPath}/show_transactions_history' method="get" id='show_form'
      accept-charset="UTF-8">

    <label for="findByOrgID"><fmt:message key="label.find.by_organisation" />: </label>
    <input type="radio" id="findByOrgID" name="findBy" value="OrgId">

    <label for="accountsFrom"><fmt:message key="label.sender" />:</label>
    <select name="accountFrom" id='accountsFrom'>

    <option value='-1'>-</option>

        <c:forEach var="bank_account" items='${bank_accounts}'>

            <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

        </c:forEach>

    </select>


    <label for="accountsTo"><fmt:message key="label.payee" />: </label>
    <select name="accountTo" id='accountsTo'>

        <option value='-1'>-</option>
        <c:forEach var="bank_account" items='${bank_accounts}'>

            <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

        </c:forEach>

    </select>
    <p>
        <label for="findByDate"><fmt:message key="label.find.by_date" />: </label>
        <input type="radio" id="findByDate" name="findBy" value="date">


        <label for="accountsFromDate"><fmt:message key="label.sender" />:</label>
        <select name="accountFromDate" id='accountsFromDate'>

            <c:forEach var="bank_account" items='${bank_accounts}'>

                <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

            </c:forEach>

        </select>

        <label for="dateFrom"><fmt:message key="label.find.date_from" />:</label>
        <input type="date" name="dateFrom" max=<date:getCurrentDate/> id="dateFrom" >

        <label for="dateTo"><fmt:message key="label.find.date_to" />:</label>
        <input type="date" name="dateTo" max=<date:getCurrentDate/> id="dateTo" >


    </p>

    <br><input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.accept" />>

</form>

     </c:when>

            <c:otherwise>
<br>



<form action='${pageContext.request.contextPath}/show_transactions_history' method="get"
      accept-charset="UTF-8" >


    <input type="text" name="findBy" value="OrgId" hidden required>
    <input type="text" name="accountTo" value="-1" hidden required>

    <label for="accountsFrom1"><fmt:message key="label.sender" />:</label>
    <select name="accountFrom" id='accountsFrom1'>

        <c:forEach var="bank_account" items='${bank_accounts}'>
        <option value='-2'><fmt:message key="label.all" /></option>
            <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

        </c:forEach>

    </select>

  <input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.accept" />>
</form>

<br>

<form action='${pageContext.request.contextPath}/show_transactions_history' method="get" id='show_form'
      accept-charset="UTF-8">

          <input type="text" name="findBy" value="OrgId" hidden required>
          <input type="text" name="accountFrom" value="-1" hidden required>

    <label for="accountsTo"><fmt:message key="label.payee" />: </label>
    <select name="accountTo" id='accountsTo'>
 <option value='-2'><fmt:message key="label.all" /></option>
        <c:forEach var="bank_account" items='${bank_accounts}'>

            <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

        </c:forEach>

    </select>

   </form>


           </c:choose>



</p>

<br>
<hr>
<br>
<table class="auto_center center">
<thead>

<tr>

<th scope="col"><fmt:message key="label.sender" /> - <fmt:message key="label.organisation_name" /></th>
<th scope="col"><fmt:message key="label.sender" /> - <fmt:message key="label.info" /></th>
<th scope="col"><fmt:message key="label.payee" /> - <fmt:message key="label.organisation_name" /></th>
<th scope="col"><fmt:message key="label.payee" /> - <fmt:message key="label.info" /></th>
<th scope="col"><fmt:message key="label.transaction_date" /></th>
<th scope="col"><fmt:message key="label.amount" /></th>
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


<c:set var="parameters" scope="page" value="findBy=${param.findBy}&accountFrom=${param.accountFrom}&accountTo=${param.accountTo}&accountFromDate=${param.accountFromDate}&dateFrom=${param.dateFrom}&dateTo=${param.dateTo}"/>



<table id="chTable" class="auto_center center">
<tr>
<c:if test='${startPage>0}'>
<td><a href="${pageContext.request.contextPath}/show_transactions_history/${startPage}?${parameters}">${startPage}</a></td>
<td>...<td>
</c:if>

<c:forEach var="page" items='${startPages}'>

<td><a href="${pageContext.request.contextPath}/show_transactions_history/${page}?${parameters}">${page}</a></td>

</c:forEach>

<td><a href="${pageContext.request.contextPath}/show_transactions_history/${currentPage}?${parameters}"><h3><b>${currentPage}</b></h3></a></td>

<c:forEach var="page" items='${finishPages}'>

<td><a href="${pageContext.request.contextPath}/show_transactions_history/${page}?${parameters}">${page}</a></td>

</c:forEach>

<c:if test='${finishPage>0}'>
<td>...<td>
<td><a href="${pageContext.request.contextPath}/show_transactions_history/${finishPage}?${parameters}">${finishPage}</a></td>

</c:if>

</tr>
</table>

</body>

</html>