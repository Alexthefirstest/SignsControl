<!DOCTYPE html>

<html>

<head>

    <title>SignsControl</title>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script src="https://api-maps.yandex.ru/2.1/?apikey=1c5fb506-d2a0-4d35-9f1f-6db2c0c54aa1&lang=ru_RU"
            type="text/javascript" charset="UTF-8"></script>

    <script src="https://yandex.st/jquery/2.2.3/jquery.min.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath}/js/map.js" type="text/javascript" charset="UTF-8"></script>

    <%--создать css. карта, объекты. локализацуия еще--%>
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
<h1>SignsControl</h1>
<h2>алло, как с русскими буквами обстоит вопрос?</h2>
прекрасно

<p><a href="${pageContext.request.contextPath}/login">log in</a>

    <%--<div id="map" style=" height: 500px"></div>--%>

    <select id="language">
        <option selected value="ru">ru</option>
        <option value="en">en</option>
    </select>
    hey bro
<div id="map"></div>


</body>

</html>