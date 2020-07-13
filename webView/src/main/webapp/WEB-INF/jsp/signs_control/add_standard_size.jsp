<%--
  Created by IntelliJ IDEA.
   User: Bulgak Alexander

--%>
<!DOCTYPE html>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>add standard size</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>
<form action='${pageContext.request.contextPath}/add_standard_size_form' method="post" accept-charset="UTF-8">

    <label for="sizeID"> size number:</label>
    <input type="text" id="sizeID" name="size" pattern="\d+" required>

    <label for="size_info"> signs description:</label>
    <input type="text" id="size_info" name="info" pattern="[\wА-Яа-я\s:!.,)(-?\d]+" required>

    <input type="submit" value="добавить">

</form>


</body>

</html>
