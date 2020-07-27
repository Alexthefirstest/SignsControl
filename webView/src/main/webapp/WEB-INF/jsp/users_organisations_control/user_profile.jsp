<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>User profile</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>


<div class="auto_center center">

   <h3 class="fullwidthblock">id : ${user.id} </h3>
   <hr>
    <h3 class="fullwidthblock">login: ${user.login} </h3>
    <hr>
  <h3 class="fullwidthblock">role : ${user.role.role} </h3>
   <hr>
    <h3 class="fullwidthblock">organisation: ${user.organisation.name} </h3>
    <hr>

    <h3 class="fullwidthblock inline">block:  </h3>
    <c:choose>

           <c:when test="${user.block=='true'}">

        <h3 class="fullwidthblock inline" style="color: red" >  заблокирован</h3>

           </c:when>

           <c:otherwise>
           <h3  class="fullwidthblock inline" style="color: green" >  не заблокирован</h3>

           </c:otherwise>

       </c:choose>

 <hr>
   <h3 class="fullwidthblock">name: ${user.name} </h3>
   <hr>
    <h3 class="fullwidthblock">surname: ${user.surname} </h3>
 <hr>
    <h3 class="fullwidthblock">info: ${user.info} </h3>


<form action='${pageContext.request.contextPath}/change_login_password/login' method="post">
    <label for="fieldUser">Login:</label><br><input type="text" id="fieldUser" name="login"  class="fullwidthblock"
                                                    pattern="\w+"
                                                    placeholder="max - 20 symbols" maxlength="20"
                                                    required>
    <br> <hr><br>
    <label for="fieldPassword">Password:</label><br><input type="password" id="fieldPassword"  class="fullwidthblock"
                                                           name="password" pattern="\w+"
                                                           placeholder="max - 20 symbols" maxlength="20"
                                                           required>

    <label for="newLogin">Login:</label><br><input type="text" id="newLogin" name="newLogin"  class="fullwidthblock"
                                                   pattern="\w+"
                                                   placeholder="max - 20 symbols" maxlength="20"
                                                   required>
    <br><input type="reset" value="Reset" class="registerbtn">
    <input type="submit" value=" set" class="registerbtn">
</form>
<br> <hr><br>
<form action='${pageContext.request.contextPath}/change_login_password/password' method="post">
    <label for="fieldUserPass">Login:</label><br><input type="text" id="fieldUserPass" name="login"  class="fullwidthblock"
                                                        pattern="\w+"
                                                        placeholder="max - 20 symbols" maxlength="20"
                                                        required>
    <br> <br>
    <label for="fieldPasswordLog">Password:</label><br><input type="password" id="fieldPasswordLog"  class="fullwidthblock"
                                                              name="password" pattern="\w+"
                                                              placeholder="max - 20 symbols" maxlength="20"
                                                              required>
    <br> <br>
    <label for="newPassword">Password:</label><br><input type="password" id="newPassword"  class="fullwidthblock"
                                                         name="newPassword" pattern="\w+"
                                                         placeholder="max - 20 symbols" maxlength="20"
                                                         required>

    <br><input type="reset" value="Reset" class="registerbtn">
    <input type="submit" value="set" class="registerbtn">
</form>

<br> <hr>
<br>

<form action='${pageContext.request.contextPath}/change_user_form_handler' method="post" id='change_user'
      accept-charset="UTF-8" >


    <input type="text" id="userID" name="id" value='${user.id}' hidden required>



    <label for="setRole">изменить роль</label>
    <input type="checkbox" id="setRole" name="setRole" >
 <label for="roleSelect">роль:</label>
    <select name="role" required id=roleSelect>

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    </select>
<br>
<br>

    <label for="setName">изменить имя</label>
    <input type="checkbox" id="setName" name="setName">


    <label for="name">имя: </label> <input type="text" id="name" name="name"  class="fullwidthblock"
                                            value='${user.name}' required>

<br>
<br>
    <label for="setSurname">изменить фамилию</label>
    <input type="checkbox" id="setSurname" name="setSurname">
 <label for="surname">фамилия: </label>
<input type="text" id="surname" name="surname"  class="fullwidthblock"
                                           value='${user.surname}' required>

<br>
<br>
    <label for="setOrganisation">изменить организацию</label>
    <input type="checkbox" id="setOrganisation" name="setOrganisation">


    <label for="organisationSelect">организация: </label>
    <select name="organisation" required id="organisationSelect">

        <c:forEach var="organisation" items='${organisations}'>

            <option value='${organisation.id}'>${organisation.name}</option>

        </c:forEach>

    </select>

<br>
<br>
    <label for="setInfo">setInfo</label>
    <input type="checkbox" id="setInfo" name="setInfo">

    <label for="info">info: </label> <br>
       <textarea id="info" name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+"> value='${user.info}'</textarea>
<br>
<br>
    <c:if test='${user.block==true}'>
        <label for="unblock">разблокировать</label>
        <input type="radio" id="unblock" value='false' name="block">
    </c:if>

    <c:if test='${user.block==false}'>
        <label for="block">заблокировать</label>
        <input type="radio" id="block" value='true' name="block">
    </c:if>


    <br><input type="reset" value="сбросить" class="registerbtn">
    <input type="submit" value="применить" class="registerbtn">
</form>

</div>
</body>

</html>