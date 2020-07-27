<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Bank Accounts</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body class="auto_center center">
<jsp:include page="../header.jsp"/>

<br><hr><br>
<a href="${pageContext.request.contextPath}/show_transactions_history">Показать транзакции</a>
<br><hr><br>
<form action='${pageContext.request.contextPath}/add_bank_account' method="post">
    <input type="submit" value="добавить аккаунт">
</form>

<br><hr>
<label for="add_money_form"><h3>добавить денег на счет</h3></label>
<form action='${pageContext.request.contextPath}/add_money' method="post" id="add_money_form" >

        <label for="addMoneyOrg">Организация - получатель:</label>
        <select name="addMoneyOrg" id='addMoneyOrg'>

            <c:forEach var="bank_account" items='${bank_accounts}'>

                <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

            </c:forEach>

        </select>

        <label for="addMoneyValue">Сумма:</label>
        <input type="text" id="addMoneyValue" name="addMoneyValue"
               pattern="-?\d+(\.\d*)?" placeholder=". to double" required>

 <input type="submit" value="добавить денег">


</form>

<br><hr>
<br>

<form action='${pageContext.request.contextPath}/bank_accounts' method="get" id='show_form' accept-charset="UTF-8">

    <p>
        <label for="blocked">показать заблокированные</label>
        <input type="radio" id="blocked" name="blocked" value=1>

        <label for="unblocked">показать незаблокированные</label>
        <input type="radio" id="unblocked" name="blocked" value=0>
    </p>
    <p>
        <label for="positiveBalance">показать с положительным балансом</label>
        <input type="radio" id="positiveBalance" name="balance" value="positive">


        <label for="negativeBalance">показать с отрицательным балансом</label>
        <input type="radio" id="negativeBalance" name="balance" value="negative">
    </p>
    <p>
        <label for="balanceMoreThenValue">баланс выше значения:</label>
        <input type="text" id="balanceMoreThenValue" name="balanceMoreThenValue"
               pattern="-?\d+" placeholder="integer number"
        >

        <label for="balanceLessThenValue">баланс ниже значения:</label>
        <input type="text" id="balanceLessThenValue" name="balanceLessThenValue"
               pattern="-?\d+" placeholder="integer number"
        >
    </p>

<hr>
   <h3>Сортировать:</h3>
    <p>
        <label for="blockedSort">по блокировке</label>
        <select name="blockedSort" id="blockedSort">
            <option value=null>нет</option>
            <option value='max'>сначала заблокированные</option>
            <option value='min'>сначала незаблокированные</option>
        </select>


        <label for="minAllowedBalanceSort">по минимально допустимому балансу</label>
        <select name="minAllowedBalanceSort" id="minAllowedBalanceSort">
            <option value=null>нет</option>
            <option value='min'>сначала минимальный</option>
            <option value='max'>сначала максимальный</option>
        </select>


        <label for="balanceSort">по балансу</label>
        <select name="balanceSort" id="balanceSort">
            <option value=null>нет</option>
            <option value='min'>сначала минимальный</option>
            <option value='max'>сначала максимальный</option>
        </select>


        <label for="orgNameSort">по имени организации</label>
        <select name="orgNameSort" id="orgNameSort">
            <option value=null>нет</option>
            <option value='min'>по алфавиту</option>
            <option value='max'>обратно алфавиту</option>
        </select>
    </p>
    <p>
        <br><input type="reset" value="Reset">
        <input type="submit" value="сортировать">
    </p>
</form>


<hr>

<table>
<thead>

<tr>

<th scope="col">Название организации</th>
<th scope="col">Состояние блокировки организации</th>
<th scope="col">Информация об организцаии</th>
<th scope="col">Баланс</th>
<th scope="col">Миинмально допустимый баланс</th>
<th scope="col">Состояние блокировки банковского аккаунта</th>
<th scope="col">Информация о банковском аккаунте</th>

</tr>

</thead>

<tbody>

    <c:forEach var="bank_account" items='${bank_accounts}'>

<tr>
  <td>${bank_account.organisation.name}</td>

   <c:choose>

       <c:when test="${bank_account.organisation.blocked=='true'}">

   <td style="color: red" >заблокирована</td>

       </c:when>

       <c:otherwise>
      <td style="color: green" >не заблокирована</td>

       </c:otherwise>

   </c:choose>

  <td>${bank_account.organisation.info}</td>
  <td>${bank_account.balance}</td>
  <td>

  <form action='${pageContext.request.contextPath}/change_bank_account_form/set_min_balance' method="post">

      <input type="text" id="orgID" name="orgID" value='${bank_account.organisation.id}' hidden required>

      <label for="minBalance">минимальный допустимый баланс: </label><br> <input type="text" id="minBalance" name="minBalance"
                                                                             value='${bank_account.minAllowedBalance}'
                                                                             pattern="-?\d+(\.\d*)?" required
                                                                             oninvalid="alert('wrong')">

      <input type="submit" value="изменить">
  </form>

  </td>


<c:choose>
    <c:when test="${bank_account.blocked=='true'}">

 <td> <h4 style="color: red">заблокирован</h4>

        <form action='${pageContext.request.contextPath}/change_bank_account_form/set_block' method="post">

            <input type="text" id="orgIDsb" name="orgID" value='${bank_account.organisation.id}' hidden>
            <input type="text" id="blocksb" name="block" value='false' hidden>
            <input type="submit" value="разблокировать">
        </form>
</td>

    </c:when>


    <c:otherwise>

 <td> <h4 style="color: green">не заблокирован</h4>

        <form action='${pageContext.request.contextPath}/change_bank_account_form/set_block' method="post">

            <input type="text" id="orgIDsb2" name="orgID" value='${bank_account.organisation.id}' hidden>
            <input type="text" id="blocksb2" name="block" value='true' hidden>
            <input type="submit" value="заблокировать">
        </form>
</td>
    </c:otherwise>

</c:choose>

  <td>

<form action='${pageContext.request.contextPath}/change_bank_account_form/set_info' method="post">

    <input type="text" id="orgIDsi" name="orgID" value='${bank_account.organisation.id}' hidden required>
    <label for="orgInfo">Информация: </label><br/>
    <textarea id="orgInfo"
             name="orgInfo" pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required
           >${bank_account.info}</textarea>

    <input type="submit" value="изменить">

    </td>

</form>

</tr>

</c:forEach>

</tbody>
</table>

</body>

</html>