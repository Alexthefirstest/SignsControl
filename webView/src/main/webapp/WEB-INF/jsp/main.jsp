<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>SignsControl</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script>let ctx = "${pageContext.request.contextPath}"</script>

    <script src="https://api-maps.yandex.ru/2.1/?apikey=1c5fb506-d2a0-4d35-9f1f-6db2c0c54aa1&lang=ru_RU"
            type="text/javascript" charset="UTF-8"></script>

    <script src="https://yandex.st/jquery/2.2.3/jquery.min.js" type="text/javascript"></script>

    <script src='${pageContext.request.contextPath}/js/map.js?35' type="text/javascript" charset="UTF-8"></script>


    <%--создать css: карта, стандартные объекты на ней--%>
    <style>
        html, body, #map {
            width: 100%;
            height: 80%;
            padding: 0;
            margin: 0;
        }

        #my-listbox {
            top: auto;
            left: auto;
        }

        #language {
            cursor: pointer;
            margin-left: 5px;
            font-size: 100%;
        }

        #point_form {
            width: 100%;
            height: 10%;
            padding: 0;
            margin: 0;
        }

        #signsHistoryTable {
            width: 100%;
            height: 10%;
            padding: 0;
            margin: 0;
        }
    </style>


</head>

<body>

<jsp:include page="header.jsp"/>

<%-- контент с учетом роли --%>
<c:choose>

<c:when test="${empty sessionScope.role}">

<p><a href="${pageContext.request.contextPath}/login">log in</a>

    </c:when>


    <c:otherwise>

<p> User: ${sessionScope.username} <a href="${pageContext.request.contextPath}/logout">log out</a>

    </c:otherwise>

    </c:choose>
    <%-- контент для роли 1 - одд --%>
    <c:if test='${sessionScope.role==1}'>

    <button id="addPointButton">добавить точку</button>

<form>
    <input type="checkbox" id="addDirectionForm" name="directionBox">
    <label for="addDirectionForm">добавить направление</label>
</form>

<form>
    <input type="checkbox" id="addSignCBox" name="addSignBox">
    <label for="addSignCBox">добавить знак</label>
</form>

<form>
    <input type="checkbox" id="changeDirectionBox" name="changeDirectionBox">
    <label for="changeDirectionBox">изменить направление</label>
</form>

<form>
    <input type="checkbox" id="change_local_sign_box" name="change_local_sign_box">
    <label for="change_local_sign_box">изменить знак</label>
</form>
    <%--  --%>

    <%-- добавляет точку иили направление --%>
<form action='${pageContext.request.contextPath}/add_map_point' method="post" id='point_form' accept-charset="UTF-8">

    <input type="hidden" name="coordinatesToSend" value='null' required>

    <label for="address">address: </label><input type="text" id="address" name="address"
                                                 pattern="[\wА-Яа-я\s:!.,)(-?\d]+"
                                                 required>
    <label for="direction"> direction:</label><select name="direction" id='direction' required>


        <%--<c:forEach var="cDirection" items='${directions}'> --%>

        <%--        <option value='${cDirection.id}'>${cDirection.direction}</option> --%>


        <%-- </c:forEach> --%>

</select>

    <label for="annotation"> annotation:</label><input type="text" id="annotation"
                                                       name="annotation" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">
    <br><input type="reset" value="сбросить">
    <input type="submit" value="добавить">
</form>

    <%--  добавляет локальный знак --%>
<form action='${pageContext.request.contextPath}/add_sign' method="post" id='addSign_form' accept-charset="UTF-8">

    <label for="pdd_sign"> pdd_sign:</label><select name="pdd_sign" id='pdd_sign' required> </select>

    <label for="standard_size"> standard_size:</label><select name="standard_size" id='standard_size'
                                                              required> </select>


    <label for="sign_list"> direction:</label><select name="sign_list" id='sign_list' required> </select>


    <label for="signAnnotation"> annotation:</label><input type="text" id="signAnnotation"
                                                           name="annotation" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <label for="date_of_add">date_of_add: </label> <input type="date" name="date_of_add" id="date_of_add">
    <label for="date_of_remove">date_of_remove: </label><input type="date" name="date_of_remove" id="date_of_remove">

    <br><input type="reset" value="сбросить">
    <input type="submit" value="добавить">
</form>

    <%--  для изменения/удаления направления - удалить все направления = удалить точку --%>
<form action='${pageContext.request.contextPath}/change_delete_direction' method="post" id='direction_control_form'
      accept-charset="UTF-8">

    <label for="old_direction"> old direction:</label><select name="old_direction" id='old_direction'
                                                              required> </select>

    <label for="new_direction"> new direction:</label><select name="new_direction" id='new_direction'
                                                              required> </select>

    <label for="addressDirCh">address: </label><input type="text" id="addressDirCh" name="address"
                                                      pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <label for="annotationDirCh"> annotation:</label><input type="text" id="annotationDirCh"
                                                            name="annotation" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <br><input type="reset" value="сбросить">
    <input type="submit" value="добавить">
</form>


    <%--  для изменения/удаления знака --%>
<form action='${pageContext.request.contextPath}/change_delete_local_sign' method="post" id='sign_control_form'
      accept-charset="UTF-8">

    <label for="sign_info"> sign:</label><select name="local_sign_id" id='sign_info'> </select>

    <label for="sign_annotation_change"> annotation:</label><input type="text" id="sign_annotation_change"
                                                                   name="annotation" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <label for="date_of_add_change">date_of_add: </label> <input type="date" name="date_of_add" id="date_of_add_change">
    <label for="date_of_remove_change">date_of_remove: </label><input type="date" name="date_of_remove"
                                                                      id="date_of_remove_change">

    <input type="checkbox" id="deleteSign" name="deleteSign" value="delete_sign">
    <label for="deleteSign">удалить знак</label>

    <br><input type="reset" value="сбросить">
    <input type="submit" value="добавить">
</form>


    <%--<div id="map" style=" height: 500px"></div>--%>


<button id="showEmptyPointsButton">показать пустые точки</button>

</c:if>


<%--<div id="map" style=" height: 500px"></div>--%>

<br>
<select id="language">
    <option selected value="ru">ru</option>
    <option value="en">en</option>
</select>

<form>
    <p>Выберите дату:
        <input type="date" name="calendar" max="2020-06-25" id="chosenDate">
        <input type="reset" id="resetButton">
    </p>
</form>

<form>
    <input type="checkbox" id="signsHistory" name="historyBox">
    <label for="signsHistory">показать историю знаков</label>
</form>


<div id="map"></div>

<div id="signsHistoryTable"></div>


</body>

</html>