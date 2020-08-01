<%--
  Created by  Bulgak Alexander

--%>
<!DOCTYPE html>

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

    <title>add pdd sign</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>


<form action='${pageContext.request.contextPath}/upload/add_pdd_sign_form' method="post" accept-charset="UTF-8" class="auto_center center"
      enctype='multipart/form-data'>

    <label for="pdd_section"><fmt:message key="label.section" />:</label><input type="text" name="pdd_section" id='pdd_section' pattern="\d+"
                                                     class="fullwidthblock"     required>
    <label for="pdd_sign"><fmt:message key="label.sign" />:</label><input type="text" name="pdd_sign" id='pdd_sign' pattern="\d+" required  class="fullwidthblock">
    <label for="pdd_kind"><fmt:message key="label.kind" />:</label><input type="text"  class="fullwidthblock" name="pdd_kind" id='pdd_kind' pattern="\d+"  placeholder="не обязательно">
    <label for="name"> <fmt:message key="label.name" />:</label><input type="text"  class="fullwidthblock" name="name" id='name' pattern="[\wА-Яа-я\s:!.,)(-?\d]+"
                                               required>
    <br><label for="description"> <fmt:message key="label.description" />:</label><input type="text"  class="fullwidthblock" name="description" id='description'
                                                             pattern="[\wА-Яа-я\s:!.,)(-?\d]+">
    <br><label for="picture"><fmt:message key="label.image" />:</label><input type="file" name="image" id='picture' accept="image/*">


    <br><input type="reset" value=<fmt:message key="label.reset" /> class="registerbtn">
    <input type="submit" value='<fmt:message key="label.add" /> <fmt:message key="label.sign" />' class="registerbtn">
</form>


</body>

</html>
