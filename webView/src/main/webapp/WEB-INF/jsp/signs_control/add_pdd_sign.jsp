<%--
  Created by IntelliJ IDEA.
   User: Bulgak Alexander

--%>
<!DOCTYPE html>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>add pdd sign</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>

<body>
<jsp:include page="../header.jsp"/>
<form action='${pageContext.request.contextPath}/add_pdd_sign_form' method="post" accept-charset="UTF-8" enctype='multipart/form-data'>

    <label for="pdd_section"> pdd_section:</label><input type="text" name="pdd_section" id='pdd_section' pattern="\d+"
                                                         required>
    <label for="pdd_sign"> pdd_sign:</label><input type="text" name="pdd_sign" id='pdd_sign' pattern="\d+" required>
    <label for="pdd_kind"> pdd_king:</label><input type="text" name="pdd_kind" id='pdd_kind' pattern="\d+">
    <label for="name"> pdd_name:</label><input type="text" name="name" id='name' pattern="[\wА-Яа-я\s:!.,)(-?\d]+"
                                               required>
    <label for="description"> pdd_description:</label><input type="text" name="description" id='description'
                                                             pattern="[\wА-Яа-я\s:!.,)(-?\d]+">
    <label for="picture"> pdd_picture:</label><input type="file" name="image" id='picture' accept="image/*">

 <input name="name" value="Виктор">

    <br><input type="reset" value="сбросить">
    <input type="submit" value="добавить">
</form>


</body>

</html>
