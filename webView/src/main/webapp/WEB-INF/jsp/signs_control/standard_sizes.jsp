<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>StandardSizes</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>
<p><a href="${pageContext.request.contextPath}/add_standard_size">add st size</a>


<table>

<thead>


<tr>


<th scope="col">стандартый размер</th>
<th scope="col">описание</th>
<th scope="col">изменить стандартый размер</th>

</tr>


</thead>


<tbody>


    <c:forEach var="standard_size" items='${standard_sizes}'>
<tr>
<td>${standard_size.size}</td>
<td>${standard_size.info}</td>
<td>

<form action='${pageContext.request.contextPath}/change_standard_size' method="post">
    <input type="text" id="size" name="size" value='${standard_size.size}' hidden>
    <input type="submit" value="изменить размер">
</form>

<form action='${pageContext.request.contextPath}/remove_standard_size' method="post">
    <input type="text" name="size" value='${standard_size.size}' hidden>
    <input type="submit" value="удалить размер">
</form>

</td>



</tr>
</c:forEach>

</tbody>
</table>

</body>

</html>