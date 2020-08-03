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

    <title>Orders</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body class="auto_center center">
<jsp:include page="../header.jsp"/>

<a href="${pageContext.request.contextPath}/types_of_work"><fmt:message key="label.type_of_work" /></a>
<hr><br>
<form action='${pageContext.request.contextPath}/orders' method="get">
    <input type="text" name="orders_option" value='all' hidden>
    <input type="submit" value='<fmt:message key="label.show" /> <fmt:message key="label.all" />'>
</form>

<form action='${pageContext.request.contextPath}/orders' method="get">
    <input type="text" name="orders_option" value='executed' hidden>
    <input type="submit" value='<fmt:message key="label.show" /> <fmt:message key="label.executed" />'>
</form>

<form action='${pageContext.request.contextPath}/orders' method="get">
    <input type="text" name="orders_option" value='unexecuted' hidden>
    <input type="submit" value='<fmt:message key="label.show" /> <fmt:message key="label.un_executed" />'>
</form>
<hr>
<p>

  <label for="show_by_id_form"> <fmt:message key="label.show.by_organisation_performer" />: </label>
<form action='${pageContext.request.contextPath}/orders' method="get" id='show_by_id_form'>
    <input type="text" name="orders_option" value='show_by_org_id' hidden>


    <label for="organisation_id"> <fmt:message key="label.organisation_performer" />:</label><select name="organisationID" id='organisation_id'
                                                              required>

    <c:forEach var="organisation" items='${allOrganisations}'>

        <option value='${organisation.id}'>${organisation.name}</option>

    </c:forEach>

</select>

    <input type="submit" value='<fmt:message key="label.show" /> <fmt:message key="label.orders" />'>
</form>

</p>
<hr>
<table class="auto_center center">
<thead>

<tr>

<th scope="col">id</th>
<th scope="col"><fmt:message key="label.sign" /></th>
<th scope="col"><fmt:message key="label.standard_size" /></th>
<th scope="col"><fmt:message key="label.organisation_customer" /></th>
<th scope="col"><fmt:message key="label.transaction" /></th>
<th scope="col"><fmt:message key="label.type_of_work" /></th>
<th scope="col"><fmt:message key="label.date_of_order" /></th>
<th scope="col"><fmt:message key="label.date_of_execution" /></th>
<th scope="col"><fmt:message key="label.workers_crew" /></th>
<th scope="col"><fmt:message key="label.info" /></th>

</tr>

</thead>


<tbody>

<c:forEach var="order" items='${orders}'>

<tr>

<td>${order.id}</td>
<td>

<c:choose><c:when test='${order.sign.kind>-1}'>
    ${order.sign.section}.${order.sign.sign}.${order.sign.kind}
            </c:when>
            <c:otherwise>
               ${order.sign.section}.${order.sign.sign}
            </c:otherwise>
        </c:choose></td>


<td>${order.standardSize}</td>
<td>${order.customerOrganisation.name} -<br> ${order.customerOrganisation.info}</td>

<c:choose><c:when test='${order.transactionID>0}'>
  <td><fmt:message key="label.transaction_id" />:${order.transactionID}<br><fmt:message key="label.amount" />: ${order.transactionMoney}</td>
            </c:when>
            <c:otherwise>
              <td ><h4 style="color: red"><fmt:message key="label.not_paid" /></h4>

                  <c:if test="${sessionScope.organisationRole==order.customerOrganisation.role.id}">

                      <form action='${pageContext.request.contextPath}/pay_order' method="post" id='pay_order_form'
                            accept-charset="UTF-8">



                          <input type="text" name="id" value='${order.id}' hidden required>

                        <br>  <label for="organisations_order"><fmt:message key="label.payee" />:</label><select name="organisationTo" id='organisations_order'
                                                                                     required>

                          <c:forEach var="organisation" items='${organisations}'>

                              <option value='${organisation.id}'>${organisation.name}</option>

                          </c:forEach>


                      </select>

                          <br><label for="acceptPrice"><fmt:message key="label.amount" />: </label> <input type="text" id="acceptPrice" name="acceptPrice"
                                                                          pattern="\d+(\.\d*)?">

   <input type="submit" value=<fmt:message key="label.pay" />>

                      </form>

                  </c:if>


              </td>
            </c:otherwise>
        </c:choose>


<td>${order.typeOfWork.typeOfWork}</td>
<td>${order.dateOfOrder}</td>

<c:choose><c:when test='${order.workersCrew>0}'>
 <td>${order.dateOfExecution}</td>
 <td>${order.workersCrew}</td>
            </c:when>
            <c:otherwise>
           <td style="color: red"><fmt:message key="label.un_executed" /></td>
           <td>-</td>
            </c:otherwise>
        </c:choose>

<td>${order.info}</td>


</tr>

</c:forEach>

</tbody>
</table>

</body>

</html>