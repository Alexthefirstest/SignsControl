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
<br>

<table>
<thead>


<tr>


<th scope="col">номер знака</th>
<th scope="col">название</th>
<th scope="col">описание</th>
<th scope="col">изображение</th>
<th scope="col">изменить знак</th>

</tr>


</thead>


<tbody>

    <c:forEach var="sign" items='${pdd_signs}'>
<tr>

<td>

<c:choose><c:when test='${sign.kind>-1}'>
    ${sign.section}.${sign.sign}.${sign.kind}
            </c:when>
            <c:otherwise>
               ${sign.section}.${sign.sign}
            </c:otherwise>
        </c:choose>

        </td>

   <td>${sign.name}</td>
   <td>${sign.description}</td>

 <td>
    <c:if test="${not empty sign.picture}">
    <img src="${pageContext.request.contextPath}/upload?id=${sign.id}"/>
    </c:if>

</td>

 <td>
  <form action='${pageContext.request.contextPath}/upload/set_sign_image' method="post" accept-charset="UTF-8"
          enctype='multipart/form-data'>

    <input type="text" name="sign_id" pattern="\d+" value="${sign.id}" required hidden>
        <label for="picture_set"> image:</label><input type="file" name="image" id='picture_set' accept="image/*" required>

        <input type="submit" value="добавить изображение">
    </form>
<br>


<label for="changeNameDesc"> изменить название и описание:</label>
<form action='${pageContext.request.contextPath}/change_pdd_sign' method="post" accept-charset="UTF-8" id="changeNameDesc">
    <input type="text" name="sign_id" pattern="\d+" value="${sign.id}" required hidden>
    <label for="name"> название:</label><input type="text" name="name" value="${sign.name}" id='name'
                                                pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>
    <label for="description"> описание:</label><input type="text" name="description" value="${sign.description}"
                                                             id='description'
                                                             pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <br><input type="reset" value="сбросить">
    <input type="submit" value="применить изменения">
</form>
<br>
<form action='${pageContext.request.contextPath}/remove_pdd_sign' method="post" accept-charset="UTF-8">

    <input type="text" name="sign_id" pattern="\d+" value="${sign.id}" required hidden>
    <input type="submit" value="удалить знак">
</form>

</td>

</tr>

</c:forEach>



</tbody>
</table>

</body>

</html>