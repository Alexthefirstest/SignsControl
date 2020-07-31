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

    <title>Bank Accounts</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body class="auto_center center">
<jsp:include page="../header.jsp"/>

<br><hr><br>
<a href="${pageContext.request.contextPath}/show_transactions_history/1"><fmt:message key="label.show" /> <fmt:message key="label.transactions" /></a>
<br><hr><br>
<form action='${pageContext.request.contextPath}/add_bank_account' method="post">
    <input type="submit" value='<fmt:message key="label.add" /> <fmt:message key="label.bank_account" />' >
</form>

<br><hr>
<label for="add_money_form"><h3><fmt:message key="label.add_money_to_the_bank_account" /></h3></label>
<form action='${pageContext.request.contextPath}/add_money' method="post" id="add_money_form" >

        <label for="addMoneyOrg"><fmt:message key="label.organisation" /> - <fmt:message key="label.payee" />:</label>
        <select name="addMoneyOrg" id='addMoneyOrg'>

            <c:forEach var="bank_account" items='${bank_accounts}'>

                <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

            </c:forEach>

        </select>

        <label for="addMoneyValue"><fmt:message key="label.amount" /> :</label>
        <input type="text" id="addMoneyValue" name="addMoneyValue"
               pattern="-?\d+(\.\d*)?" placeholder=". to double" required>

 <input type="submit" value=<fmt:message key="label.transfer_money" />>


</form>

<br><hr>
<br>

<form action='${pageContext.request.contextPath}/bank_accounts' method="get" id='show_form' accept-charset="UTF-8">

    <p>
        <label for="blocked"><fmt:message key="label.show.blocked" /></label>
        <input type="radio" id="blocked" name="blocked" value=1>

        <label for="unblocked"><fmt:message key="label.show.unblocked" /></label>
        <input type="radio" id="unblocked" name="blocked" value=0>
    </p>
    <p>
        <label for="positiveBalance"><fmt:message key="label.show.with_positive_balance" /></label>
        <input type="radio" id="positiveBalance" name="balance" value="positive">


        <label for="negativeBalance"><fmt:message key="label.show.with_negative_balance" /></label>
        <input type="radio" id="negativeBalance" name="balance" value="negative">
    </p>
    <p>
        <label for="balanceMoreThenValue"><fmt:message key="label.show.with_balance_more_then" /> :</label>
        <input type="text" id="balanceMoreThenValue" name="balanceMoreThenValue"
               pattern="-?\d+" placeholder=<fmt:message key="label.integer_number" />
        >

        <label for="balanceLessThenValue"><fmt:message key="label.show.with_balance_less_then" />:</label>
        <input type="text" id="balanceLessThenValue" name="balanceLessThenValue"
               pattern="-?\d+" placeholder=<fmt:message key="label.integer_number" />
        >
    </p>

<hr>
   <h3><fmt:message key="label.sort.action" />:</h3>
    <p>
        <label for="blockedSort"><fmt:message key="label.sort.by_block" /></label>
        <select name="blockedSort" id="blockedSort">
            <option value=null><fmt:message key="label.no" /></option>
            <option value='max'><fmt:message key="label.at_first.blocked" /></option>
            <option value='min'><fmt:message key="label.at_first.unblocked" /></option>
        </select>


        <label for="minAllowedBalanceSort"><fmt:message key="label.sort.min_allowed_balance" /></label>
        <select name="minAllowedBalanceSort" id="minAllowedBalanceSort">
            <option value=null><fmt:message key="label.no" /></option>
            <option value='min'><fmt:message key="label.at_first.minimum" /></option>
            <option value='max'><fmt:message key="label.at_first.maximum" /></option>
        </select>

<br>
        <label for="balanceSort"><fmt:message key="label.sort.by_balance" /></label>
        <select name="balanceSort" id="balanceSort">
            <option value=null><fmt:message key="label.no" />нет</option>
            <option value='min'><fmt:message key="label.at_first.minimum" /></option>
            <option value='max'><fmt:message key="label.at_first.maximum" /></option>
        </select>


        <label for="orgNameSort"><fmt:message key="label.sort.by_organisation_name" /></label>
        <select name="orgNameSort" id="orgNameSort">
            <option value=null><fmt:message key="label.no" /></option>
            <option value='min'><fmt:message key="label.alphabetically" /></option>
            <option value='max'><fmt:message key="label.alphabetically.reverse" /></option>
        </select>
    </p>
    <hr>  <p>

        <br><input type="reset" value=<fmt:message key="label.reset" />>
        <input type="submit" value=<fmt:message key="label.sort.action" />>
    </p>
</form>


<hr>

<table>
<thead>

<tr>

<th scope="col"><fmt:message key="label.organisation_name" /></th>
<th scope="col"><fmt:message key="label.organisation.block_condition" /></th>
<th scope="col"><fmt:message key="label.organisation.info" /></th>
<th scope="col"><fmt:message key="label.balance" /></th>
<th scope="col"><fmt:message key="label.min_balance" /></th>
<th scope="col"><fmt:message key="label.bank_account.block_condition" /></th>
<th scope="col"><fmt:message key="label.bank_account.info" /></th>

</tr>

</thead>

<tbody>

    <c:forEach var="bank_account" items='${bank_accounts}'>

<tr>
  <td>${bank_account.organisation.name}</td>

   <c:choose>

       <c:when test="${bank_account.organisation.blocked=='true'}">

   <td style="color: red" ><fmt:message key="label.block" /></td>

       </c:when>

       <c:otherwise>
      <td style="color: green" ><fmt:message key="label.active" /></td>

       </c:otherwise>

   </c:choose>

  <td>${bank_account.organisation.info}</td>
  <td>${bank_account.balance}</td>
  <td>

  <form action='${pageContext.request.contextPath}/change_bank_account_form/set_min_balance' method="post">

      <input type="text" id="orgID" name="orgID" value='${bank_account.organisation.id}' hidden required>

      <label for="minBalance"><fmt:message key="label.min_balance" /> : </label><br> <input type="text" id="minBalance" name="minBalance"
                                                                             value='${bank_account.minAllowedBalance}'
                                                                             pattern="-?\d+(\.\d*)?" required
                                                                             oninvalid="alert('wrong')">

      <input type="submit" value=<fmt:message key="label.change" />>
  </form>

  </td>


<c:choose>
    <c:when test="${bank_account.blocked=='true'}">

 <td> <h4 style="color: red"><fmt:message key="label.block" /></h4>

        <form action='${pageContext.request.contextPath}/change_bank_account_form/set_block' method="post">

            <input type="text" id="orgIDsb" name="orgID" value='${bank_account.organisation.id}' hidden>
            <input type="text" id="blocksb" name="block" value='false' hidden>
            <input type="submit" value=<fmt:message key="label.unblock_action" />>
        </form>
</td>

    </c:when>


    <c:otherwise>

 <td> <h4 style="color: green"><fmt:message key="label.active" /></h4>

        <form action='${pageContext.request.contextPath}/change_bank_account_form/set_block' method="post">

            <input type="text" id="orgIDsb2" name="orgID" value='${bank_account.organisation.id}' hidden>
            <input type="text" id="blocksb2" name="block" value='true' hidden>
            <input type="submit" value=<fmt:message key="label.block_action" />>
        </form>
</td>
    </c:otherwise>

</c:choose>

  <td>

<form action='${pageContext.request.contextPath}/change_bank_account_form/set_info' method="post">

    <input type="text" id="orgIDsi" name="orgID" value='${bank_account.organisation.id}' hidden required>
    <label for="orgInfo"><fmt:message key="label.info" />: </label><br/>
    <textarea id="orgInfo"
             name="orgInfo" pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required
           >${bank_account.info}</textarea>

    <input type="submit" value=<fmt:message key="label.change" />>

    </td>

</form>

</tr>

</c:forEach>

</tbody>
</table>

</body>

</html>