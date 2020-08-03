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

    <title>Types of work</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body class="auto_center center">

<jsp:include page="../header.jsp"/>

    <c:if test="${sessionScope.organisationRole==5}">
  <label for="add_type_form"><fmt:message key="label.add" /> <fmt:message key="label.type_of_work" />: </label>
<form action='${pageContext.request.contextPath}/add_type_of_work' method="post" accept-charset="UTF-8" id="add_type_form">
    <label for="work_name"><fmt:message key="label.name" />: </label> <input type="text" id="work_name" name="name"
                                                              pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>
    <label for="price">стоимость: </label> <input type="text" id="price" name="price" pattern="\d+(\.\d*)?" required>
    <br><input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.add" />>
</form>
  </c:if>
<br>
<hr>
<table>
<thead>


<tr>

<th scope="col"><fmt:message key="label.name" /></th>
<th scope="col"><fmt:message key="label.price" /></th>
<th scope="col"><fmt:message key="label.block_condition" /></th>


</tr>


</thead>


<tbody>


<c:forEach var="type_of_work" items='${types_of_work}'>

<tr>

<td>${type_of_work.typeOfWork}<br>
  <c:if test="${sessionScope.organisationRole==5}">
   <form action="${pageContext.request.contextPath}/remove_type_of_work" method="post" accept-charset="UTF-8">
        <input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>
        <br>
        <input type="submit" value=<fmt:message key="label.delete" />>
    </form>
      </c:if>
</td>
<td>${type_of_work.price}<br><br>
  <c:if test="${sessionScope.organisationRole==5}">
 <form action="${pageContext.request.contextPath}/change_type_of_work/set_price" method="post"
          accept-charset="UTF-8">

        <br>
        <input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>
        <label for="priceCh"><fmt:message key="label.new.she" /> <fmt:message key="label.price" />: </label> <input type="text" id="priceCh" name="price"  pattern="-?\d+(\.\d*)?" required>
           <input type="submit" value='<fmt:message key="label.set.price" />'>
    </form>
      </c:if>
</td>

    <c:choose>

        <c:when test="${type_of_work.block=='true'}">

  <td> <h4 style="color: red" ><fmt:message key="label.block" /></h4><br>
  <c:if test="${sessionScope.organisationRole==5}">
            <form action='${pageContext.request.contextPath}/change_type_of_work/unblock' method="post">

                <input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>

                <input type="submit" value=<fmt:message key="label.unblock_action" />>
            </form>
              </c:if>
 </td>
        </c:when>


        <c:otherwise>

 <td ><h4 style="color: green" ><fmt:message key="label.active" /></h4><br>
   <c:if test="${sessionScope.organisationRole==5}">
            <form action='${pageContext.request.contextPath}/change_type_of_work/block' method="post">
                <input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>
                <input type="submit" value=<fmt:message key="label.block_action" />>
            </form>
             </c:if>
</td>
        </c:otherwise>

    </c:choose>



</tr>

</c:forEach>

</tbody>
</table>

</body>

</html>