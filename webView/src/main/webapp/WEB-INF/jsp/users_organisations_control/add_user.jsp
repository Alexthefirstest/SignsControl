<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Add user</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<form action='${pageContext.request.contextPath}/add_user_form_handler' method="post" class="fullwidthblock center">

    <label for="fieldUserPass">Login:</label><br><input class="fullwidthblock" type="text" id="fieldUserPass" name="login"
                                                        pattern="\w+"
                                                        placeholder="max - 20 symbols" maxlength="20"
                                                        required>
    <br><br>
    <label for="fieldPasswordLog">Password:</label><br><input class="fullwidthblock" type="password" id="fieldPasswordLog"
                                                              name="password" pattern="\w+"
                                                              placeholder="max - 20 symbols" maxlength="20"
                                                    required>
 <br><br>
 <label for="role_user">Роль:</label>
    <select name="role" required id="role_user">

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    </select>

    <label for="name">Имя: </label> <input class="fullwidthblock" type="text" id="name" name="name"
                                            required>
<br>
    <label for="surname">Фамилия: </label> <input  class="fullwidthblock" type="text" id="surname" name="surname"
                                           required>
<br>
 <label for="userOrg">Организация: </label>
    <select name="organisation" required id="userOrg">

        <c:forEach var="organisation" items='${organisations}'>

            <option value='${organisation.id}'>${organisation.name}</option>

        </c:forEach>

    </select>

<br>
    <label for="info">информация: </label> <textarea id="info" name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+"></textarea>

    <br><input type="reset" value="сбросить" class="registerbtn">
    <input type="submit" value="добавить" class="registerbtn">

</form>

</body>


</html>