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

//создать css. карта, объекты
    <style>
        html, body, #map {
            width: 100%;
            height: 100%;
            padding: 0;
            margin: 0;
        }

        #my-listbox {
            top: auto;
            left: auto;
        }
    </style>

</head>

<body>

<h2>mainjsp</h2>

<p><a href="${pageContext.request.contextPath}/login">log in</a>

<%--<div id="map" style=" height: 500px"></div>--%>
<div id="map" ></div>

</body>

</html>