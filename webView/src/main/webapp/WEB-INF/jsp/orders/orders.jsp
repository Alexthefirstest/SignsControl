<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Orders</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<a href="${pageContext.request.contextPath}/types_of_work">тип работ</a>

<form action='${pageContext.request.contextPath}/orders' method="get">
    <input type="text" name="orders_option" value='all' hidden>
    <input type="submit" value="показать все">
</form>

<form action='${pageContext.request.contextPath}/orders' method="get">
    <input type="text" name="orders_option" value='executed' hidden>
    <input type="submit" value="показать выполненные">
</form>

<form action='${pageContext.request.contextPath}/orders' method="get">
    <input type="text" name="orders_option" value='unexecuted' hidden>
    <input type="submit" value="показать невыполненные">
</form>

<p>

  <label for="show_by_id_form"> показать по организации-исполнителю: </label>
<form action='${pageContext.request.contextPath}/orders' method="get" id='show_by_id_form'>
    <input type="text" name="orders_option" value='show_by_org_id' hidden>


    <label for="organisation_id"> организация:</label><select name="organisationID" id='organisation_id'
                                                              required>

    <c:forEach var="organisation" items='${allOrganisations}'>

        <option value='${organisation.id}'>${organisation.name}</option>

    </c:forEach>

</select>

    <input type="submit" value="показать заказы">
</form>

</p>

<table>
<thead>

<tr>

<th scope="col">id</th>
<th scope="col">знак</th>
<th scope="col">размер</th>
<th scope="col">заказчик</th>
<th scope="col">транзакция</th>
<th scope="col">тип работ</th>
<th scope="col">дата заказа</th>
<th scope="col">дата выполнения</th>
<th scope="col">бригада</th>
<th scope="col">информация заказа</th>

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
  <td>id транзакции:${order.transactionID}<br>сумма: ${order.transactionMoney}</td>
            </c:when>
            <c:otherwise>
              <td ><h4 style="color: red">не оплачено</h4>

                  <c:if test="${sessionScope.organisationRole==order.customerOrganisation.role.id}">

                      <form action='${pageContext.request.contextPath}/pay_order' method="post" id='pay_order_form'
                            accept-charset="UTF-8">



                          <input type="text" name="id" value='${order.id}' hidden required>

                        <br>  <label for="organisations_order">Получатель перевода:</label><select name="organisationTo" id='organisations_order'
                                                                                     required>

                          <c:forEach var="organisation" items='${organisations}'>

                              <option value='${organisation.id}'>${organisation.name}</option>

                          </c:forEach>


                      </select>

                          <br><label for="acceptPrice">Сумма: </label> <input type="text" id="acceptPrice" name="acceptPrice"
                                                                          pattern="\d+(\.\d*)?">

   <input type="submit" value="оплатить">

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
           <td style="color: red">не выполнено</td>
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