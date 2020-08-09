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

    <title>User profile</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<c:if test='${param.success==true}'>
   <h4 class="auto_center center" style="color: green"><fmt:message key="label.success" /></h4>
</c:if>
<c:if test='${param.success==false}'>
   <h4 class="auto_center center" style="color: red"><fmt:message key="label.fail.wrong_login_or_password" /></h4>

</c:if>

<div class="auto_center center">

   <h3 class="fullwidthblock">id : ${user.id} </h3>
   <hr>
    <h3 class="fullwidthblock"><fmt:message key="label.login" />: ${user.login} </h3>
    <hr>
  <h3 class="fullwidthblock"><fmt:message key="label.role" /> : ${user.role.role} </h3>
   <hr>
    <h3 class="fullwidthblock"><fmt:message key="label.organisation" />: ${user.organisation.name} </h3>
    <hr>

    <h3 class="fullwidthblock inline"><fmt:message key="label.block_condition" />:  </h3>
    <c:choose>

           <c:when test="${user.block=='true'}">

        <h3 class="fullwidthblock inline" style="color: red" >  <fmt:message key="label.block" /></h3>

           </c:when>

           <c:otherwise>
           <h3  class="fullwidthblock inline" style="color: green" >  <fmt:message key="label.active" /></h3>

           </c:otherwise>

       </c:choose>

 <hr>
   <h3 class="fullwidthblock"><fmt:message key="label.user_name" />: ${user.name} </h3>
   <hr>
    <h3 class="fullwidthblock"><fmt:message key="label.surname" />: ${user.surname} </h3>
 <hr>
    <h3 class="fullwidthblock"><fmt:message key="label.info" />: ${user.info} </h3>

  <c:if test="${sessionScope.userID==user.id}">
<form action='${pageContext.request.contextPath}/change_login_password/login' method="post">
    <label for="fieldUser"><fmt:message key="label.login" />:</label><br><input type="text" id="fieldUser" name="login"  class="fullwidthblock"
                                                    pattern="\w+"
                                                    placeholder="max - 20 symbols" maxlength="20"
                                                    required>
    <br> <hr><br>
    <label for="fieldPassword"><fmt:message key="label.password" />:</label><br><input type="password" id="fieldPassword"  class="fullwidthblock"
                                                           name="password" pattern="\w+"
                                                           placeholder="max - 20 symbols" maxlength="20"
                                                           required>

    <label for="newLogin"><fmt:message key="label.new.he" /> <fmt:message key="label.login" />:</label><br><input type="text" id="newLogin" name="newLogin"  class="fullwidthblock"
                                                   pattern="\w+"
                                                   placeholder="max - 20 symbols" maxlength="20"
                                                   required>
    <br><input type="reset" value=<fmt:message key="label.reset" /> class="registerbtn">
    <input type="submit" value=<fmt:message key="label.set" /> class="registerbtn">
</form>
<br> <hr><br>
<form action='${pageContext.request.contextPath}/change_login_password/password' method="post">
    <label for="fieldUserPass"><fmt:message key="label.login" />:</label><br><input type="text" id="fieldUserPass" name="login"  class="fullwidthblock"
                                                        pattern="\w+"
                                                        placeholder="max - 20 symbols" maxlength="20"
                                                        required>
    <br> <br>
    <label for="fieldPasswordLog"><fmt:message key="label.password" />:</label><br><input type="password" id="fieldPasswordLog"  class="fullwidthblock"
                                                              name="password" pattern="\w+"
                                                              placeholder="max - 20 symbols" maxlength="20"
                                                              required>
    <br> <br>
    <label for="newPassword"><fmt:message key="label.new.he" /> <fmt:message key="label.password" />:</label><br><input type="password" id="newPassword"  class="fullwidthblock"
                                                         name="newPassword" pattern="\w+"
                                                         placeholder="max - 20 symbols" maxlength="20"
                                                         required>

    <br><input type="reset" value=<fmt:message key="label.reset" /> class="registerbtn">
    <input type="submit" value=<fmt:message key="label.set" /> class="registerbtn">
</form>
  </c:if>
<br> <hr>
<br>

 <c:if test="${ sessionScope.userID!= user.id &&((user.organisation.role.id==5  && sessionScope.organisationRole==5 && sessionScope.userRole==2) || (user.organisation.role.id!=5 && ( (sessionScope.userRole==2 &&  sessionScope.organisationID==user.organisation.id) || (sessionScope.organisationRole==5 && user.role.id==2) ) ) )}">

<form action='${pageContext.request.contextPath}/change_user_form_handler' method="post" id='change_user'
      accept-charset="UTF-8" >


    <input type="text" id="userID" name="id" value='${user.id}' hidden required>



    <label for="setRole"><fmt:message key="label.change" /> <fmt:message key="label.role" /></label>
    <input type="checkbox" id="setRole" name="setRole" >
 <label for="roleSelect"><fmt:message key="label.role" />:</label>
    <select name="role" required id=roleSelect>

        <c:forEach var="role" items='${roles}'>

            <option value='${role.id}'>${role.role}</option>

        </c:forEach>

    </select>
<br>
<br>

    <label for="setName"><fmt:message key="label.change" /> <fmt:message key="label.user_name" /></label>
    <input type="checkbox" id="setName" name="setName">


    <label for="name"><fmt:message key="label.user_name" />: </label> <input type="text" id="name" name="name"  class="fullwidthblock"
                                            value='${user.name}' required>

<br>
<br>
    <label for="setSurname"><fmt:message key="label.change.surname" /></label>
    <input type="checkbox" id="setSurname" name="setSurname">
 <label for="surname"><fmt:message key="label.surname" />: </label>
<input type="text" id="surname" name="surname"  class="fullwidthblock"
                                           value='${user.surname}' required>

<br>

 <%--<br>--%>
    <%-- <label for="setOrganisation"><fmt:message key="label.change.organisation" /> </label>--%>
     <%--<input type="checkbox" id="setOrganisation" name="setOrganisation">--%>


    <%-- <label for="organisationSelect"><fmt:message key="label.organisation" />: </label>--%>
    <%-- <select name="organisation" required id="organisationSelect">--%>

       <%--  <c:forEach var="organisation" items='${organisations}'>--%>

          <%--   <option value='${organisation.id}'>${organisation.name}</option>--%>

        <%-- </c:forEach> --%>

    <%-- </select>   --%>

 <%-- <br>    --%>
<br>
    <label for="setInfo"><fmt:message key="label.change.info" /></label>
    <input type="checkbox" id="setInfo" name="setInfo">

    <label for="info"><fmt:message key="label.info" />: </label> <br>
       <textarea id="info" name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">${user.info}</textarea>
<br>
<br>
    <c:if test='${user.block==true}'>
        <label for="unblock"><fmt:message key="label.unblock_action" /></label>
        <input type="radio" id="unblock" value='false' name="block">
    </c:if>

    <c:if test='${user.block==false}'>
        <label for="block"><fmt:message key="label.block_action" /></label>
        <input type="radio" id="block" value='true' name="block">
    </c:if>


    <br><input type="reset" value=<fmt:message key="label.reset" /> class="registerbtn">
    <input type="submit" value=<fmt:message key="label.accept" /> class="registerbtn">
</form>
  </c:if>
</div>
</body>

</html>