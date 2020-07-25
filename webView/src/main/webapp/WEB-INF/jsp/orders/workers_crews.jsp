<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Workers crews</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<a href="${pageContext.request.contextPath}/workers_crews">показать все</a>

<br><label for="choose_by_organisation_form">Показать бригады организации: </label>
<form action='${pageContext.request.contextPath}/workers_crews' method="get" id='show_crews' accept-charset="UTF-8" id="choose_by_organisation_form">

<label for="choose_by_organisation">Организация: </label>
    <select name="organisationID" required id="choose_by_organisation">

        <c:forEach var="organisation" items='${organisations}'>

            <option value='${organisation.id}'>${organisation.name}</option>

        </c:forEach>

    </select>

 <input type="checkbox" id="showEmpty" name="showEmpty">
    <label for="showEmpty">показать пустые бригады</label>

    <input type="submit" value="показать">

</form>


<br>
<br>
<br>


<form action='${pageContext.request.contextPath}/add_workers_crew' method="post" id='add_crew'
      accept-charset="UTF-8">

   <input type="submit" value="Добавить бригаду    ">

    <label for="wcInfo">информация: </label> <input type="text" id="wcInfo"
                                                    name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>

</form>


<table>
<thead>


<tr>


<th scope="col">id</th>
<th scope="col">организация</th>
<th scope="col">дата создания</th>
<th scope="col">дата удаления</th>
<th scope="col">информация</th>
<th scope="col">состав</th>

</tr>


</thead>


<tbody>


<c:forEach var="workers_crew" items='${workersCrews}'>

<tr>
<td>${workers_crew.id} <br>

 <label for="addWorker">Добавить работника в бригаду: </label>
    <form action='${pageContext.request.contextPath}/add_worker_to_crew' method="post" id='show_crews'
          accept-charset="UTF-8" id="addWorker">
        <input type="text" name="wc" value="${workers_crew.id}" hidden required>

        <label for="userIDToAdd">Пользователь: </label>
        <select name="userID" required id="userIDToAdd">

            <c:forEach var="userOfOrg" items='${usersOfOrg}'>

                <option value='${userOfOrg.id}'>${userOfOrg.login}<br>${userOfOrg.name} ${userOfOrg.surname}</option>

            </c:forEach>

        </select>

        <br>
        <input type="submit" value="add worker">

    </form>
    </td>

<td>${workers_crew.organisation.name} - <br> ${workers_crew.organisation.info}</td>
<td>${workers_crew.creationDate}</td>
<td>

 <c:choose>

        <c:when test="${workers_crew.removeDate==null}">

 <h4 style="color: green" >активна</h4><br>

             <form action='${pageContext.request.contextPath}/change_worker_crew/delete' method="post" id='delete_wc'
                    accept-charset="UTF-8">
                  <input type="text" name="wc" value="${workers_crew.id}" hidden required>
                  <input type="submit" value="удалить">
              </form>

        </c:when>


        <c:otherwise>

<h4 style="color: red" >удалена: ${workers_crew.removeDate}</h4><br>


        </c:otherwise>

    </c:choose>

</td>
<td>
  <form action='${pageContext.request.contextPath}/change_worker_crew/set_info' method="post" id='delete_wc'
          accept-charset="UTF-8">
        <input type="text" name="wc" value="${workers_crew.id}" hidden required>
       <textarea id="wcInfoCh" name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">${workers_crew.info}</textarea>

<br>        <input type="submit" value="применить изменения">
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
            <input type="submit" value="delete worker">
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