<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>PddSigns</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>
<p><a href="${pageContext.request.contextPath}/add_pdd_sign">add sign</a>

    <c:forEach var="sign" items='${pdd_signs}'>

    <c:choose>


    <c:when test='${sign.kind>-1}'>

        <c:out value='${sign.section}'/>
        <c:out value='.${sign.sign}'/>
        <c:out value='.${sign.kind}'/>
    </c:when>
    <c:otherwise>
        <c:out value='${sign.section}'/>
        <c:out value='.${sign.sign}'/>
    </c:otherwise>
    </c:choose>

        <c:out value='        '/>
        <c:out value='${sign.name}'/>
        <c:out value='        '/>
        <c:out value='${sign.description}'/>
        <c:out value='        '/>

 <c:if test="${not empty sign.picture}">
       <img src="${pageContext.request.contextPath}/upload?id=${sign.id}" />
</c:if>

<form action='${pageContext.request.contextPath}/upload/set_sign_image' method="post" accept-charset="UTF-8" enctype='multipart/form-data'>

   <input type="text" name="sign_id" pattern="\d+" value="${sign.id}"  required hidden>
    <label for="picture_set"> image:</label><input type="file" name="image" id='picture_set' accept="image/*" required>

    <input type="submit" value="добавить">
</form>

<form action='${pageContext.request.contextPath}/change_pdd_sign' method="post" accept-charset="UTF-8">
<input type="text" name="sign_id" pattern="\d+" value="${sign.id}"  required hidden>
    <label for="name"> sign_name:</label><input type="text" name="name" value="${sign.name}" id='name' pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>
    <label for="description"> pdd_description:</label><input type="text" name="description"  value="${sign.description}" id='description'
                                                             pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <br><input type="reset" value="сбросить">
    <input type="submit" value="изменить знак">
</form>

<form action='${pageContext.request.contextPath}/remove_pdd_sign' method="post" accept-charset="UTF-8">

   <input type="text" name="sign_id" pattern="\d+" value="${sign.id}"  required hidden>
    <input type="submit" value="remove_sign">
</form>


<br>

</c:forEach>

</body>

</html>