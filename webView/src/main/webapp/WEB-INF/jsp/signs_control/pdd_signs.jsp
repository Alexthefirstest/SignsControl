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

    <title>PddSigns</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<p><a href="${pageContext.request.contextPath}/add_pdd_sign"><fmt:message key="label.add" /> <fmt:message key="label.sign" /></a>
<br>

<table>
<thead>


<tr>


<th scope="col"><fmt:message key="label.sign" /></th>
<th scope="col"><fmt:message key="label.name" /></th>
<th scope="col"><fmt:message key="label.description" /></th>
<th scope="col"><fmt:message key="label.image" /></th>
<th scope="col"><fmt:message key="label.change" /> <fmt:message key="label.sign" /></th>

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

        <input type="submit" value='<fmt:message key="label.add" /> <fmt:message key="label.image" />'>
    </form>
<br>


<label for="changeNameDesc"> <fmt:message key="label.change" /> <fmt:message key="label.name" /> <fmt:message key="label.and" /> <fmt:message key="label.description" />:</label>
<form action='${pageContext.request.contextPath}/change_pdd_sign' method="post" accept-charset="UTF-8" id="changeNameDesc">
    <input type="text" name="sign_id" pattern="\d+" value="${sign.id}" required hidden>
    <label for="name"> <fmt:message key="label.name" />:</label><input type="text" name="name" value="${sign.name}" id='name'
                                                pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>
    <label for="description"> <fmt:message key="label.description" />:</label><input type="text" name="description" value="${sign.description}"
                                                             id='description'
                                                             pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <br><input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.accept" />>
</form>
<br>
<form action='${pageContext.request.contextPath}/remove_pdd_sign' method="post" accept-charset="UTF-8">

    <input type="text" name="sign_id" pattern="\d+" value="${sign.id}" required hidden>
    <input type="submit" value='<fmt:message key="label.delete"/> <fmt:message key="label.sign" />'>
</form>

</td>

</tr>

</c:forEach>



</tbody>
</table>

</body>

</html>