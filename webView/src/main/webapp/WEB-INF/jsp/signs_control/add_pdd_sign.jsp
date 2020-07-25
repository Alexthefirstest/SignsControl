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


<form action='${pageContext.request.contextPath}/upload/add_pdd_sign_form' method="post" accept-charset="UTF-8"
      enctype='multipart/form-data'>

    <label for="pdd_section"> секция:</label><input type="text" name="pdd_section" id='pdd_section' pattern="\d+"
                                                         required>
    <label for="pdd_sign"> знак:</label><input type="text" name="pdd_sign" id='pdd_sign' pattern="\d+" required>
    <label for="pdd_kind">подвид:</label><input type="text" name="pdd_kind" id='pdd_kind' pattern="\d+"  placeholder="не обязательно">
    <label for="name"> название:</label><input type="text" name="name" id='name' pattern="[\wА-Яа-я\s:!.,)(-?\d]+"
                                               required>
    <br><label for="description"> описание:</label><input type="text" name="description" id='description'
                                                             pattern="[\wА-Яа-я\s:!.,)(-?\d]+">
    <br><label for="picture"> pdd_picture:</label><input type="file" name="image" id='picture' accept="image/*">


    <br><input type="reset" value="сбросить">
    <input type="submit" value="добавить">
</form>


</body>

</html>
