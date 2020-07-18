<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Types of work</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>

<jsp:include page="../header.jsp"/>



<form action='${pageContext.request.contextPath}/add_type_of_work' method="post"  accept-charset="UTF-8">
<label for="work_name">type of work name: <label> <input type="text" id="work_name" name="name" pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>
<label for="price">price: <label> <input type="text" id="price" name="price"  pattern="\d+(\\.\d*)?" required>
<br><input type="reset" value="сбросить">
    <input type="submit" value="добавить">
</form>
<br>
<br>
<br>

<c:forEach var="type_of_work" items='${types_of_work}'>

        <c:out value='${type_of_work.typeOfWork}'/>
        <c:out value='${type_of_work.price}'/>
        <c:out value='${type_of_work.block}'/>

<form action="${pageContext.request.contextPath}/remove_type_of_work" method="post"  accept-charset="UTF-8">
<input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>
<br>
    <input type="submit" value="удалить">
</form>


<form action="${pageContext.request.contextPath}/change_type_of_work/set_price" method="post"  accept-charset="UTF-8">
<input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>
<label for="priceCh">price: <label> <input type="text" id="priceCh" name="price"  pattern="\d+(.\d*)?" required>
<br>
    <input type="submit" value="set price">
</form>



<c:choose>

<c:when test="${type_of_work.block=='true'}">

<form action='${pageContext.request.contextPath}/change_type_of_work/unblock' method="post">

 <input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>

          <input type="submit" value="разблокировать">
  </form>

    </c:when>


    <c:otherwise>

 <form action='${pageContext.request.contextPath}/change_type_of_work/block' method="post">
<input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>
           <input type="submit" value="заблокировать">
   </form>

    </c:otherwise>

    </c:choose>




<br><br>
</c:forEach>



</body>

</html>