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

    <title>Organisation profile</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body class="auto_center center">
<jsp:include page="../header.jsp"/>

<br><hr><br>
<a href="${pageContext.request.contextPath}/show_transactions_history/1"><fmt:message key="label.show" /> <fmt:message key="label.transactions" /></a>
<br><hr><br>

<br>
  <label for="transactionForm"><h3><fmt:message key="label.execute_transaction" />:<h3></label>
<form action='${pageContext.request.contextPath}/execute_transaction' method="post" id="transactionForm">

    <label for="organisation_idtr"> <fmt:message key="label.payee" />:</label><select name="organisationID" id='organisation_idtr'
                                                               required>

    <c:forEach var="organisation" items='${organisations}'>

        <option value='${organisation.id}'>${organisation.name}</option>

    </c:forEach>
</select>

    <label for="priceCh">    <fmt:message key="label.amount" />: </label> <input type="text" id="priceCh" name="money" pattern="\d+(.\d*)?" required>

 <br><input type="reset" value="сбросить">
    <input type="submit" value="отправить">

</form>
<br><hr><br><br>

<br><hr>
<br>
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
${bank_account.minAllowedBalance}


  </td>


<c:choose>
    <c:when test="${bank_account.blocked=='true'}">

 <td> <h4 style="color: red"><fmt:message key="label.block" /></h4>


</td>

    </c:when>


    <c:otherwise>

 <td> <h4 style="color: green"><fmt:message key="label.active" /></h4>


</td>
    </c:otherwise>

</c:choose>

  <td>
${bank_account.info}

</td>

</tr>


</tbody>
</table>

</body>

</html>