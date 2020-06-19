<%--
  Created by IntelliJ IDEA.
  User: Наталья
  Date: 19.06.2020
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error</title>
</head>

<body>

Error during ${pageContext.errorData.requestURI} request
<br/><br/><br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
exception: ${pageContext.exception}
<br/>
message: ${pageContext.exception.message}


</body>
</html>
