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

    <script src='${pageContext.request.contextPath}/js/map.js?18' type="text/javascript" charset="UTF-8"></script>


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

<header>
    SignsControl
</header>



<c:choose>

<c:when test="${empty sessionScope.role}">

<p><a href="${pageContext.request.contextPath}/login">log in</a>

    </c:when>


    <c:otherwise>

<p> User: ${sessionScope.username} <a href="${pageContext.request.contextPath}/logout">log out</a>

    </c:otherwise>

    </c:choose>

<c:if test='${sessionScope.role==1}'>

  <button id="addPointButton">добавить точку</button>

 <form>
     <input type="checkbox" id="addDirectionForm" name="directionBox">
     <label for="addDirection">добавить направление</label>
 </form>

  <button id="addSignButton">добавить знак</button>

  <form action='${pageContext.request.contextPath}/add_map_point' method="post" id='point_form'>

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