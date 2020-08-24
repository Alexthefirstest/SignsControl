<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="date" uri="/WEB-INF/tag/getDateTags.tld" %>


<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:choose>

        <c:when test="${empty sessionScope.locale}">

        <fmt:setLocale value="ru"/>

            </c:when>

            <c:otherwise> <fmt:setLocale value="${sessionScope.locale}"/> </c:otherwise>

           </c:choose>

<fmt:setBundle basename="messages"/>

<html>

<head>

    <title>SignsControl</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script>let ctx = "${pageContext.request.contextPath}"</script>

    <script src="https://api-maps.yandex.ru/2.1/?apikey=1c5fb506-d2a0-4d35-9f1f-6db2c0c54aa1&lang=ru_RU"
            type="text/javascript" charset="UTF-8"></script>

    <script src="https://yandex.st/jquery/2.2.3/jquery.min.js" type="text/javascript"></script>

    <script src='${pageContext.request.contextPath}/js/map.js?52' type="text/javascript" charset="UTF-8"></script>




</head>

<body>

<jsp:include page="header.jsp"/>

<br>
<br>
<a href="${pageContext.request.contextPath}/types_of_work"><fmt:message key="label.type_of_work" /></a>
<br>
<br>

<%-- контент с учетом роли --%>

    <%-- контент для роли 1 - одд --%>


    <c:if test="${sessionScope.organisationRole==1}">


<table class="auto_center chTable">
<tr>
<td>
<form>
    <input type="checkbox" id="addDirectionForm" name="directionBox">
    <label for="addDirectionForm"><fmt:message key="label.add" /> <fmt:message key="label.direction" /></label>
</form>

<form>
    <input type="checkbox" id="changeDirectionBox" name="changeDirectionBox">
    <label for="changeDirectionBox"><fmt:message key="label.change" /> <fmt:message key="label.direction" /></label>
</form>


</td>
<td>

<form>
    <input type="checkbox" id="addSignCBox" name="addSignBox">
    <label for="addSignCBox"><fmt:message key="label.add" /> <fmt:message key="label.sign" /></label>
</form>

<form>
    <input type="checkbox" id="change_local_sign_box" name="change_local_sign_box">
    <label for="change_local_sign_box"><fmt:message key="label.change" /> <fmt:message key="label.sign" /></label>
</form>

</td>

<td>

<form>
    <input type="checkbox" id="addSignOrder" name="addSignOrder">
    <label for="addSignOrder"><fmt:message key="label.add" /> <fmt:message key="label.order" /></label>
</form>

<form>
    <input type="checkbox" id="execute_delete_order" name="execute_delete_order">
    <label for="execute_delete_order"> <fmt:message key="label.delete" /> <fmt:message key="label.order" /></label>
</form>
</td>
<td>
 <button id="addPointButton"><fmt:message key="label.add" /> <fmt:message key="label.point" /></button>
</td>
</tr>
</table>


<div style="position: fixed;  bottom: 1px; z-index: 4">

    <%-- добавляет точку иили направление --%>
<p class="center">
<form action='${pageContext.request.contextPath}/add_map_point' method="post" id='point_form' accept-charset="UTF-8" style="display: none">

    <input type="hidden" name="coordinatesToSend" value='null' required>

    <label for="address"><fmt:message key="label.address" />: </label><input type="text" id="address" name="address"
                                                 pattern="[\wА-Яа-я\s:!.,)(-?\d]+"
                                                 required>
    <label for="direction"> <fmt:message key="label.direction" />: </label><select name="direction" id='direction' required>


</select>

    <label for="annotation"> <fmt:message key="label.annotation" /> :</label><input type="text" id="annotation"
                                                       name="annotation" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">
    <br><input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.add" />>
</form>

    <%--  добавляет локальный знак --%>
<form action='${pageContext.request.contextPath}/add_sign' method="post" id='addSign_form' accept-charset="UTF-8" style="display: none">

    <label for="pdd_sign"> <fmt:message key="label.pdd_sign" /> :</label><select name="pdd_sign" id='pdd_sign' required> </select>

    <label for="standard_size"> <fmt:message key="label.standard_size" /> :</label><select name="standard_size" id='standard_size'
                                                              required> </select>


    <label for="sign_list"> <fmt:message key="label.direction" />:</label><select name="sign_list" id='sign_list' required> </select>


    <label for="signAnnotation"> <fmt:message key="label.annotation" />:</label><input type="text" id="signAnnotation"
                                                           name="annotation" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <label for="date_of_add"><fmt:message key="label.date_of_add" />: </label> <input type="date" max=<date:getCurrentDate/> name="date_of_add" id="date_of_add">
    <label for="date_of_remove"><fmt:message key="label.date_of_remove" />: </label><input type="date" max=<date:getCurrentDate/> name="date_of_remove" id="date_of_remove">

    <br><input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.add" />>
</form>

    <%--  для изменения/удаления направления - удалить все направления = удалить точку --%>
<form action='${pageContext.request.contextPath}/change_delete_direction' method="post" id='direction_control_form'
      accept-charset="UTF-8" style="display: none">

    <label for="old_direction"> <fmt:message key="label.old" /> <fmt:message key="label.direction" /> :</label><select name="old_direction" id='old_direction'
                                                              required> </select>

    <label for="new_direction"><fmt:message key="label.new" /> <fmt:message key="label.direction" /> :</label><select name="new_direction" id='new_direction'
                                                              required> </select>

    <label for="addressDirCh"><fmt:message key="label.address" />: </label><input type="text" id="addressDirCh" name="address"
                                                      pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <label for="annotationDirCh"> <fmt:message key="label.annotation" /> :</label><input type="text" id="annotationDirCh"
                                                            name="annotation" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <br><input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.change" />>
</form>


    <%--  для изменения/удаления знака --%>
<form action='${pageContext.request.contextPath}/change_delete_local_sign' method="post" id='sign_control_form'
      accept-charset="UTF-8" style="display: none">

    <label for="sign_info"> <fmt:message key="label.sign" /> :</label><select name="local_sign_id" id='sign_info'> </select>

    <label for="sign_annotation_change"> <fmt:message key="label.annotation" />:</label><input type="text" id="sign_annotation_change"
                                                                   name="annotation" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

    <label for="date_of_add_change"><fmt:message key="label.date_of_add" />: </label> <input type="date" max=<date:getCurrentDate/> name="date_of_add" id="date_of_add_change">
    <label for="date_of_remove_change"><fmt:message key="label.date_of_remove" />: </label><input type="date" max=<date:getCurrentDate/> name="date_of_remove"
                                                                      id="date_of_remove_change">

    <input type="checkbox" id="deleteSign" name="deleteSign" value="delete_sign">
    <label for="deleteSign"><fmt:message key="label.delete" /> <fmt:message key="label.sign" /> </label>

    <br><input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.change" />>
</form>


    <%--  добавляет  заказ --%>
<form action='${pageContext.request.contextPath}/add_order' method="post" id='add_order_form' accept-charset="UTF-8" style="display: none">

    <label for="sign_list_order"> <fmt:message key="label.direction" />:</label><select name="sign_list" id='sign_list_order' required> </select>

    <label for="pdd_sign_order"> <fmt:message key="label.sign" /> :</label><select name="pdd_sign" id='pdd_sign_order' required> </select>

    <label for="standard_size_order"> <fmt:message key="label.standard_size" /> :</label><select name="standard_size" id='standard_size_order'
                                                                    required> </select>
    <label for="type_of_work_order"><fmt:message key="label.type_of_work" /> :</label><select name="type_of_work" id='type_of_work_order'
                                                                  required>

    <c:forEach var="type_of_work" items='${types_of_work}'>

        <option value='${type_of_work.id}'>${type_of_work.typeOfWork} - ${type_of_work.price}</option>

    </c:forEach>

</select>

    <label for="organisations_order"> <fmt:message key="label.pay" /> :</label><select name="organisationTo" id='organisations_order'
                                                               required>
    <option value=0><fmt:message key="label.no" /></option>

    <c:forEach var="organisation" items='${organisations}'>

        <option value='${organisation.id}'>${organisation.name}</option>

    </c:forEach>

</select>

    <label for="acceptPrice"><fmt:message key="label.price" /> : </label> <input type="text" id="acceptPrice" name="acceptPrice"
                                                    pattern="\d+(\.\d*)?">

    <label for="signAnnotation_order"> <fmt:message key="label.annotation" /> :</label><input type="text" id="signAnnotation_order"
                                                                 name="annotation" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">


    <br><input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.add" />>
</form>

    <%--  удаляет заказ --%>
<form action='${pageContext.request.contextPath}/change_delete_order' method="post" id='change_delete_order_form'
      accept-charset="UTF-8" style="display: none">

  <input type="text" name="order_action" id='order_action' value='delete'
                    hidden required>

    <label for="order_id"> <fmt:message key="label.order" />:</label><select name="order_id" id='order_id'
                                                 required> </select>

    <br><input type="reset" value=<fmt:message key="label.reset" />>
    <input type="submit" value=<fmt:message key="label.delete" />>
</form>
  </p>
</div>



<p class="center">
<button id="showEmptyPointsButton"><fmt:message key="label.show" /> <fmt:message key="label.empty_points" /> </button>
</p>

    </c:if>


      <%--  роль 3 --%>
    <c:if test="${sessionScope.organisationRole==3}">
        <p class="center auto_center">
        <form >
            <input type="checkbox" id="execute_delete_order" name="execute_delete_order">
            <label for="execute_delete_order"> <fmt:message key="label.execute" />  <fmt:message key="label.order" /></label>
        </form>
    </p>
    <div style="position: fixed;  bottom: 1px; z-index: 4">

    <p class="center auto_center">




       <%--  выполняет заказ --%>
    <form action='${pageContext.request.contextPath}/change_delete_order' method="post" id='change_delete_order_form'
          accept-charset="UTF-8" style="display: none">

     <input type="text" name="order_action" id='order_action' value='execute'
                        hidden required>

        <label for="order_id"> <fmt:message key="label.order" />:</label><select name="order_id" id='order_id'
                                                     required> </select>


        <label for="workers_crews"> <fmt:message key="label.workers_crew" />:</label><select name="workers_crew" id='workers_crews'
                                                                 required> </select>

        <label for="execute_order_info"> <fmt:message key="label.annotation" /> :</label><input type="text" id="execute_order_info"
                                                                   name="annotation" pattern="[\wА-Яа-я\s:!.,)(-?\d]+">

        <br><input type="reset" value=<fmt:message key="label.reset" />>
        <input type="submit" value=<fmt:message key="label.execute" />>
    </form>
  </p></div>
    </c:if>


<c:if test="${sessionScope.organisationRole==3 || sessionScope.organisationRole==1}">
<p class="center">
<button id="showOrdersButton"><fmt:message key="label.show" /> <fmt:message key="label.all" /> <fmt:message key="label.orders" /> </button>
<button id="showOrdersExecutedButton"><fmt:message key="label.show" /> <fmt:message key="label.executed" /> <fmt:message key="label.orders" /></button>
<button id="showOrdersUnExecutedButton"><fmt:message key="label.show" /> <fmt:message key="label.un_executed" /> <fmt:message key="label.orders" /></button>
</p>
</c:if>

<div class="auto_center">
<form class ="inline">
 <label for="chosenDate"><fmt:message key="label.show" /> <fmt:message key="label.actual_signs" /> : </label>
        <input type="date" name="calendar" max=<date:getCurrentDate/> id="chosenDate">

        <input type="reset" id="resetButton" value=<fmt:message key="label.update_map" />>
</form>

<form  class ="inline">
    <label for="signsHistory"><fmt:message key="label.show_signs_history" /></label>
    <input type="checkbox" id="signsHistory" name="historyBox">
</form>


<label for="language"> <fmt:message key="label.map_language" />: </label>
<select id="language">
    <option selected value="ru">ru</option>
    <option value="en">en</option>
</select>
</div>


<div id="map"></div>

<div id="signsHistoryTable"></div>


</body>

</html>