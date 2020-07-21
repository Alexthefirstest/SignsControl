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

<a href="${pageContext.request.contextPath}/workers_crews">show all</a>

<form action='${pageContext.request.contextPath}/workers_crews' method="get" id='show_crews' accept-charset="UTF-8">

    <select name="organisationID" required>

        <c:forEach var="organisation" items='${organisations}'>

            <option value='${organisation.id}'>${organisation.name}</option>

        </c:forEach>

    </select>

 <input type="checkbox" id="showEmpty" name="showEmpty">
    <label for="showEmpty">showEmpty</label>
    <br>
    <input type="submit" value="submit">

</form>


<p>


<form action='${pageContext.request.contextPath}/add_workers_crew' method="post" id='add_crew'
      accept-charset="UTF-8">


    <label for="wcInfo">информация: </label> <input type="text" id="wcInfo"
                                                    name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>



    <br>
    <input type="submit" value="add crew">

</form>

<c:forEach var="workers_crew" items='${workersCrews}'>


    <c:out value='id : ${workers_crew.id}'/>
    <c:out value='  ||      '/>
    <c:out value='creationDate: ${workers_crew.creationDate}'/>
    <c:out value='    ||    '/>
    <c:out value='removeDate : ${workers_crew.removeDate}'/>
    <c:out value='    ||    '/>
    <c:out value='Org name: ${workers_crew.organisation.name}'/>
    <c:out value='        '/>
    <c:out value=' orgBlock ${workers_crew.organisation.blocked}'/>
    <c:out value='        '/>
    <c:out value='ocg info ${workers_crew.organisation.info}'/>
    <c:out value='into: ${workers_crew.info}'/>

    <form action='${pageContext.request.contextPath}/add_worker_to_crew' method="post" id='show_crews'
          accept-charset="UTF-8">
        <input type="text" name="wc" value="${workers_crew.id}" hidden required>
        <select name="userID" required>

            <c:forEach var="userOfOrg" items='${usersOfOrg}'>

                <option value='${userOfOrg.id}'>${userOfOrg.login} ${userOfOrg.name} ${userOfOrg.surname}</option>

            </c:forEach>

        </select>

        <br>
        <input type="submit" value="add worker">

    </form>

    <c:forEach var="user" items='${workers_crew.workers}'>

        <p>


                <c:out value='USER'/>
                <c:out value='id : ${user.id}'/>
                <c:out value='  ||      '/>
                <c:out value='login: ${user.login}'/>
                <c:out value='    ||    '/>
                <c:out value='role : ${user.role.role}'/>
                <c:out value='    ||    '/>
                <c:out value='organisation: ${user.organisation.name}'/>
                <c:out value='    ||    '/>

                <c:out value='block: ${user.block}'/>
                <c:out value='   ||   '/>
                <c:out value='name: ${user.name}'/>
                <c:out value='   ||   '/>
                <c:out value='surname: ${user.surname}'/>
                <c:out value='   ||   '/>
                <c:out value='info: ${user.info}'/>

        <form action='${pageContext.request.contextPath}/change_worker_crew/delete_worker' method="post" id='delete_worker_from_wc'
              accept-charset="UTF-8">
            <input type="text" name="wc" value="${workers_crew.id}" hidden required>
            <input type="text" name="worker" value="${user.id}" hidden required>
            <input type="submit" value="delete worker">
        </form>

        </p>
    </c:forEach>

    <br>

    <c:if test='${workers_crew.removeDate==null}'>

    <form action='${pageContext.request.contextPath}/change_worker_crew/delete' method="post" id='delete_wc'
          accept-charset="UTF-8">
        <input type="text" name="wc" value="${workers_crew.id}" hidden required>
        <input type="submit" value="delete">
    </form>
     </c:if>
    <br>

    <form action='${pageContext.request.contextPath}/change_worker_crew/set_info' method="post" id='delete_wc'
          accept-charset="UTF-8">
        <input type="text" name="wc" value="${workers_crew.id}" hidden required>
        <label for="wcInfo">информация: </label> <input type="text" value='${workers_crew.info}' id="wcInfoCh"
                                                        name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">
        <input type="submit" value="set info">
    </form>


    <br>


</c:forEach>


</body>

</html>