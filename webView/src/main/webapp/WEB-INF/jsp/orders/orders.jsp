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
        <c:out value='|transactionID: ${order.standardSize}'/>
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


<br><br>

</c:forEach>


</body>

</html>