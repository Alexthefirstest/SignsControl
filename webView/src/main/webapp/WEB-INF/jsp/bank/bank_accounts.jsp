<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Bank Accounts</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<a href="${pageContext.request.contextPath}/show_transactions_history">transactions history</a>

<form action='${pageContext.request.contextPath}/add_bank_account' method="post">
    <input type="submit" value="добавить аккаунт">
</form>


<form action='${pageContext.request.contextPath}/add_money' method="post">

    <p>
        <input type="submit" value="добавить деняк">
        <label for="addMoneyOrg"> addMoneyOrg:</label>
        <select name="addMoneyOrg" id='addMoneyOrg'>

            <c:forEach var="bank_account" items='${bank_accounts}'>

                <option value='${bank_account.organisation.id}'>${bank_account.organisation.name}</option>

            </c:forEach>

        </select>

        <label for="addMoneyValue">денишки:</label>
        <input type="text" id="addMoneyValue" name="addMoneyValue"
               pattern="-?\d+(.\d*)?" placeholder=". to double" required>

    </p>


</form>


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
    <p>
        <label for="blockedSort">сортировать по блокировке</label>
        <select name="blockedSort" id="blockedSort">
            <option value=null>нет</option>
            <option value='max'>сначала заблокированные</option>
            <option value='min'>сначала незаблокированные</option>
        </select>


        <label for="minAllowedBalanceSort">сортировать по минимально допустимому балансу</label>
        <select name="minAllowedBalanceSort" id="minAllowedBalanceSort">
            <option value=null>нет</option>
            <option value='min'>сначала минимальный</option>
            <option value='max'>сначала максимальный</option>
        </select>


        <label for="balanceSort">сортировать по балансу</label>
        <select name="balanceSort" id="balanceSort">
            <option value=null>нет</option>
            <option value='min'>сначала минимальный</option>
            <option value='max'>сначала максимальный</option>
        </select>


        <label for="orgNameSort">имени организации</label>
        <select name="orgNameSort" id="orgNameSort">
            <option value=null>нет</option>
            <option value='min'>по алфавиту</option>
            <option value='max'>обратно алфавиту</option>
        </select>
    </p>
    <p>
        <br><input type="reset" value="Reset">
        <input type="submit" value="submit">
    </p>
</form>


<p>

    <c:forEach var="bank_account" items='${bank_accounts}'>


        <c:out value='Org name: ${bank_account.organisation.name}'/>
        <c:out value='        '/>
        <c:out value='${bank_account.organisation.blocked}'/>
        <c:out value='        '/>
        <c:out value='${bank_account.organisation.info}'/>
        <c:out value='   BANK     '/>
        <c:out value='Balance: ${bank_account.balance}'/>
        <c:out value='        '/>
        <c:out value='Min allowed balance: ${bank_account. minAllowedBalance}'/>
        <c:out value='        '/>
        <c:out value='${bank_account.blocked}'/>
        <c:out value='        '/>
        <c:out value='${bank_account.info}'/>
        <c:out value='        '/>


<form action='${pageContext.request.contextPath}/change_bank_account_form/set_min_balance' method="post">

    <input type="text" id="orgID" name="orgID" value='${bank_account.organisation.id}' hidden required>

    <label for="minBalance">минимальный допустимый баланс: </label> <input type="text" id="minBalance" name="minBalance"
                                                                           value='${bank_account.minAllowedBalance}'
                                                                           pattern="-?\d+(.\d*)?" required>
    <input type="submit" value="установить">
</form>


<c:choose>

    <c:when test="${bank_account.blocked=='true'}">

        <form action='${pageContext.request.contextPath}/change_bank_account_form/set_block' method="post">

            <input type="text" id="orgIDsb" name="orgID" value='${bank_account.organisation.id}' hidden>
            <input type="text" id="blocksb" name="block" value='false' hidden>
            <input type="submit" value="разблокировать">
        </form>

    </c:when>


    <c:otherwise>

        <form action='${pageContext.request.contextPath}/change_bank_account_form/set_block' method="post">

            <input type="text" id="orgIDsb2" name="orgID" value='${bank_account.organisation.id}' hidden>
            <input type="text" id="blocksb2" name="block" value='true' hidden>
            <input type="submit" value="заблокировать">
        </form>

    </c:otherwise>

</c:choose>


<form action='${pageContext.request.contextPath}/change_bank_account_form/set_info' method="post">

    <input type="text" id="orgIDsi" name="orgID" value='${bank_account.organisation.id}' hidden required>
    <label for="orgInfo">информация: </label> <input type="text" value='${bank_account.info}' id="orgInfo"
                                                     name="orgInfo" pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>
    <input type="submit" value="установить">
</form>

</p>
<br>


</c:forEach>


</body>

</html>