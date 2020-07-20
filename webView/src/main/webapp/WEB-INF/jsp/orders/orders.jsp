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

    <a href="${pageContext.request.contextPath}/types_of_work">types_of_work</a>

 <form action='${pageContext.request.contextPath}/orders' method="get">
     <input type="text" name="orders_option" value='all' hidden>
     <input type="submit" value="show all">
 </form>

  <form action='${pageContext.request.contextPath}/orders' method="get">
     <input type="text" name="orders_option" value='executed' hidden>
     <input type="submit" value="show executed">
 </form>

  <form action='${pageContext.request.contextPath}/orders' method="get">
     <input type="text" name="orders_option" value='unexecuted' hidden>
     <input type="submit" value="show unexecuted">
 </form>

  <form action='${pageContext.request.contextPath}/orders' method="get">
     <input type="text" name="orders_option" value='show_by_org_id' hidden>


  <label for="organisation_id"> организация:</label><select name="organisationID" id='organisation_id'
                                                                required>

                                 <c:forEach var="organisation" items='${allOrganisations}'>

                                 <option value='${organisation.id}'>${organisation.name}</option>

                                  </c:forEach>

                          </select>


     <input type="submit" value="показать заказы">
 </form>

    <c:forEach var="order" items='${orders}'>


        <c:out value='| ID: ${order.id}'/>
        <c:out value='        '/>

        <c:choose>


           <c:when test='${order.sign.kind>-1}'>

               <c:out value='${order.sign.section}'/>
               <c:out value='.${order.sign.sign}'/>
               <c:out value='.${order.sign.kind}'/>
           </c:when>
           <c:otherwise>
               <c:out value='${order.sign.section}'/>
               <c:out value='.${order.sign.sign}'/>
           </c:otherwise>
           </c:choose>


        <c:out value='        '/>
        <c:out value='|size: ${order.standardSize}'/>
        <c:out value='        '/>
        <c:out value='|organisationName: ${order.customerOrganisation.name}'/>
        <c:out value='        '/>
       <c:out value='|organisationinfo: ${order.customerOrganisation.info}'/>
        <c:out value='        '/>
        <c:out value='|standard size: ${order.standardSize}'/>
          <c:out value='        '/>
          <c:out value='|transactionID: ${order.transactionID}'/>
          <c:out value='        '/>
                <c:out value='|money: ${order.transactionMoney}'/>
                  <c:out value='        '/>
                        <c:out value='|typeOFWork: ${order.typeOfWork.typeOfWork}'/>
        <c:out value='        '/>
        <c:out value='|workers: ${order.workersCrew}'/>
         <c:out value='        '/>
        <c:out value='|date order: ${order.dateOfOrder}'/>
         <c:out value='        '/>
        <c:out value='|date execution: ${order.dateOfExecution}'/>
         <c:out value='        '/>
        <c:out value='|info : ${order.info}'/>

        <c:out value='|sesRoel : ${sessionScope.organisationRole}'/>
        <c:out value='|org.Role : ${order.customerOrganisation.role.id}'/>
        <c:out value='|org.tra : ${order.transactionID}'/>


 <c:if test="${sessionScope.organisationRole==order.customerOrganisation.role.id && order.transactionID<1}">

<form action='${pageContext.request.contextPath}/pay_order' method="post" id='pay_order_form' accept-charset="UTF-8">

 <input type="text"  name="id" value='${order.id}' hidden required>

<label for="organisations_order"> оплатить:</label><select name="organisationTo" id='organisations_order'
                                                              required>

                               <c:forEach var="organisation" items='${organisations}'>

                               <option value='${organisation.id}'>${organisation.name}</option>

                                </c:forEach>


                        </select>

<label for="acceptPrice">price: <label> <input type="text" id="acceptPrice" name="acceptPrice"  pattern="\d+(\.\d*)?">


    <input type="submit" value="оплатить">
</form>

</c:if>

<br><br>

</c:forEach>


</body>

</html>