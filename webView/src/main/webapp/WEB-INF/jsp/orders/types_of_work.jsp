<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Types of work</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body class="auto_center center">

<jsp:include page="../header.jsp"/>

  <label for="add_type_form">Добавить тип работ: </label>
<form action='${pageContext.request.contextPath}/add_type_of_work' method="post" accept-charset="UTF-8" id="add_type_form">
    <label for="work_name">Название: </label> <input type="text" id="work_name" name="name"
                                                              pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>
    <label for="price">стоимость: </label> <input type="text" id="price" name="price" pattern="\d+(\.\d*)?" required>
    <br><input type="reset" value="сбросить">
    <input type="submit" value="добавить">
</form>
<br>
<hr>
<table>
<thead>


<tr>

<th scope="col">название</th>
<th scope="col">стоимость</th>
<th scope="col">состояние блокировки</th>


</tr>


</thead>


<tbody>


<c:forEach var="type_of_work" items='${types_of_work}'>

<tr>

<td>${type_of_work.typeOfWork}<br>
   <form action="${pageContext.request.contextPath}/remove_type_of_work" method="post" accept-charset="UTF-8">
        <input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>
        <br>
        <input type="submit" value="удалить">
    </form>
</td>
<td>${type_of_work.price}<br><br>
 <form action="${pageContext.request.contextPath}/change_type_of_work/set_price" method="post"
          accept-charset="UTF-8">

        <br>
        <input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>
        <label for="priceCh">новая цена: </label> <input type="text" id="priceCh" name="price"  pattern="-?\d+(\.\d*)?" required>
           <input type="submit" value="установить цену">
    </form>
</td>

    <c:choose>

        <c:when test="${type_of_work.block=='true'}">

  <td> <h4 style="color: red" >заблокирована</h4><br>

            <form action='${pageContext.request.contextPath}/change_type_of_work/unblock' method="post">

                <input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>

                <input type="submit" value="разблокировать">
            </form>
 </td>
        </c:when>


        <c:otherwise>

 <td ><h4 style="color: green" >активен</h4><br>

            <form action='${pageContext.request.contextPath}/change_type_of_work/block' method="post">
                <input type="text" name="id" pattern="\d+" value="${type_of_work.id}" required hidden>
                <input type="submit" value="заблокировать">
            </form>
</td>
        </c:otherwise>

    </c:choose>



</tr>

</c:forEach>

</tbody>
</table>

</body>

</html>