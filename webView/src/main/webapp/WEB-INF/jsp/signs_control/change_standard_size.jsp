<%--
  Created by IntelliJ IDEA.
   User: Bulgak Alexander

--%>
<!DOCTYPE html>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>change standard size</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>

<form action='${pageContext.request.contextPath}/change_standard_size_form' method="post" accept-charset="UTF-8">

    <input type="text" name="old_size" value='${size}' pattern="\d+" hidden>

    <label for="sizeID"> новый размер:</label>
    <input type="text" id="sizeID" name="size" value='${size}' pattern="\d+" required>

    <label for="size_info"> описание:</label>
    <input type="text" id="size_info" name="info" value='${info}' pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <input type="submit" value="изменить">

</form>


</body>

</html>
