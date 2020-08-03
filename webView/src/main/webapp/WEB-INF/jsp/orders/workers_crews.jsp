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

    <title>Workers crews</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body class="auto_center center">
<jsp:include page="../header.jsp"/>

<a href="${pageContext.request.contextPath}/workers_crews"><fmt:message key="label.show" /> <fmt:message key="label.all" /></a>
<hr><br>

<br>


<form action='${pageContext.request.contextPath}/workers_crews'  class="inline auto_center center" method="get"
id='showEmptyForm' accept-charset="UTF-8" id="choose_by_organisation_form">


    <label for="showEmpty"><fmt:message key="label.show" /> <fmt:message key="label.empty" /> <fmt:message key="label.workers_crews" /></label>
 <input type="checkbox" id="showEmpty" name="showEmpty">

    <input type="submit" value=<fmt:message key="label.show" />>

</form>


<br>
<hr>
<br>


<form action='${pageContext.request.contextPath}/add_workers_crew' method="post" id='add_crew' class="auto_center center"
      accept-charset="UTF-8">

   <input type="submit" value='<fmt:message key="label.add.workers_crew" />'>

    <label for="wcInfo"><fmt:message key="label.info" />: </label> <input type="text" id="wcInfo"
                                                    name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>

</form>


<table class="auto_center center">
<thead>


<tr>


<th scope="col">id</th>
<th scope="col"><fmt:message key="label.organisation" /></th>
<th scope="col"><fmt:message key="label.creation_date" /></th>
<th scope="col"><fmt:message key="label.remove_date" /></th>
<th scope="col"><fmt:message key="label.info" /></th>
<th scope="col"><fmt:message key="label.workers" /></th>

</tr>


</thead>


<tbody>


<c:forEach var="workers_crew" items='${workersCrews}'>

<tr>
<td>${workers_crew.id} <br>

 <label for="addWorker"><fmt:message key="label.add" /> <fmt:message key="label.worker" />: </label>
    <form action='${pageContext.request.contextPath}/add_worker_to_crew' method="post" id='show_crews'
          accept-charset="UTF-8" id="addWorker">
        <input type="text" name="wc" value="${workers_crew.id}" hidden required>

        <label for="userIDToAdd"><fmt:message key="label.user" />: </label>
        <select name="userID" required id="userIDToAdd">

            <c:forEach var="userOfOrg" items='${usersOfOrg}'>

                <option value='${userOfOrg.id}'>${userOfOrg.login}<br>${userOfOrg.name} ${userOfOrg.surname}</option>

            </c:forEach>

        </select>

        <br>
        <input type="submit" value=<fmt:message key="label.add" />>

    </form>
    </td>

<td>${workers_crew.organisation.name} - <br> ${workers_crew.organisation.info}</td>
<td>${workers_crew.creationDate}</td>
<td>

 <c:choose>

        <c:when test="${workers_crew.removeDate==null}">

 <h4 style="color: green" ><fmt:message key="label.active" /></h4><br>

             <form action='${pageContext.request.contextPath}/change_worker_crew/delete' method="post" id='delete_wc'
                    accept-charset="UTF-8">
                  <input type="text" name="wc" value="${workers_crew.id}" hidden required>
                  <input type="submit" value=<fmt:message key="label.delete" />>
              </form>

        </c:when>


        <c:otherwise>

<h4 style="color: red" ><fmt:message key="label.deleted" />: ${workers_crew.removeDate}</h4><br>


        </c:otherwise>

    </c:choose>

</td>
<td>
  <form action='${pageContext.request.contextPath}/change_worker_crew/set_info' method="post" id='delete_wc'
          accept-charset="UTF-8">
        <input type="text" name="wc" value="${workers_crew.id}" hidden required>
       <textarea id="wcInfoCh" name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">${workers_crew.info}</textarea>

<br>        <input type="submit" value=<fmt:message key="label.accept" />>
    </form>

</td>

<td>
    <c:forEach var="user" items='${workers_crew.workers}'>

        <p>

login:<a href="${pageContext.request.contextPath}/user_profile/${user.id}" >${user.login}</a>
<br>имя: ${user.name} ${user.surname}

        <form action='${pageContext.request.contextPath}/change_worker_crew/delete_worker' method="post" id='delete_worker_from_wc'
              accept-charset="UTF-8">
            <input type="text" name="wc" value="${workers_crew.id}" hidden required>
            <input type="text" name="worker" value="${user.id}" hidden required>
            <input type="submit" value='<fmt:message key="label.delete" /> <fmt:message key="label.worker" />'>
        </form>
</p>
   </c:forEach>

</td>


</tr>
</c:forEach>


</tbody>
</table>

</body>

</html>