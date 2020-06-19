<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>SignsControl</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script src="https://api-maps.yandex.ru/2.1/?apikey=1c5fb506-d2a0-4d35-9f1f-6db2c0c54aa1&lang=ru_RU"
            type="text/javascript" charset="UTF-8"></script>

    <script src="https://yandex.st/jquery/2.2.3/jquery.min.js" type="text/javascript"></script>

    <script src='${pageContext.request.contextPath}/js/map.js?8' type="text/javascript" charset="UTF-8"></script>


    <%--создать css: карта, стандартные объекты на ней--%>
    <style>
        html, body, #map {
            width: 100%;
            height: 90%;
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




    <%--<div id="map" style=" height: 500px"></div>--%>

<br>
    <select id="language">
        <option selected value="ru">ru</option>
        <option value="en">en</option>
    </select>


<div id="map"></div>


</body>

</html>