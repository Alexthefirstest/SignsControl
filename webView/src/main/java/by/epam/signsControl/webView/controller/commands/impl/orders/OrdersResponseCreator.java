package by.epam.signsControl.webView.controller.commands.impl.orders;


import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.bean.Order;
import by.epam.signsControl.webView.controller.commands.impl.signsControl.ResponseCreator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.ArrayList;

public class OrdersResponseCreator {

    private static Logger logger = LogManager.getLogger(OrdersResponseCreator.class);

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();


    static String createJSON(Object[]... objects) {

        StringBuilder json = new StringBuilder("{");

        logger.info(objects.length);

        for (int i = 0; i < objects.length; i++) {


            json.append('\"' + (objects[i].getClass().getSimpleName()).replaceAll("\\[]", "s") + "\":").append(gson.toJson(objects[i])).append(',');
        }

        return json.substring(0, json.length() - 1) + '}';
    }

    private static String createBalloonStringForPoint(MapPoint$Orders mapPoints$Orders) {

        StringBuilder sb = new StringBuilder();

        ArrayList<Order> orders = mapPoints$Orders.getListOfOrders();

        int j = 0;


        int workersCrew;
        Timestamp dateOfExecution;

        sb.append("<table>")
                .append("<thead>").append("<tr>");

        sb.append("<th>id</th>")
                .append("<th>sign</th>")
                .append("<th>size</th>")
                .append("<th>type of work</th>")
                .append("<th>workers crew id</th>")
                .append("<th>date of order</th>")
                .append("<th>date of execution</th>")
                .append("<th>pay</th>")
                .append("<th>information</th>");

        sb.append("</tr>").append("</thead>");

        sb.append("<tbody>");


        for (int i = 0; i < orders.size(); i++) {



            if (i == 0 || (orders.get(i).getSignList() != orders.get(i - 1).getSignList())) {

                sb.append("<tr><th><h4>      direction: " + mapPoints$Orders.getMapPoint().getAngles().get(j) + "<h4></th></tr>");

                j++;
            }
            sb.append("<tr>");
            sb.append("<td>" + orders.get(i).getId() + "</tb>");
            sb.append("<td>" + (ResponseCreator.createSign(orders.get(i).getSign().getSection(),
                    orders.get(i).getSign().getSign(),
                    orders.get(i).getSign().getKind())) + "</tb>");

            sb.append("<td>" + (orders.get(i).getStandardSize()) + "</tb>");

            sb.append("<td>" + (orders.get(i).getTypeOfWork().getTypeOfWork()) + "</tb>");
            sb.append("<td>" + (((workersCrew = orders.get(i).getWorkersCrew()) == 0 ? "-" : workersCrew)) + "</tb>");
            sb.append("<td>" + (orders.get(i).getDateOfOrder()) + "</tb>");

            sb.append("<td>" + (((dateOfExecution = orders.get(i).getDateOfExecution()) == null ? "-" : dateOfExecution)) + "</tb>");

            sb.append(orders.get(i).getTransactionID() < 1 ?
                    ("<td>!UNPAID!</tb>") :
                    ("<td>paid</tb>"));

            sb.append("<td>" + (orders.get(i).getInfo()) + "</tb>");

            sb.append("/tr");
        }

        sb.append("</tbody>")
                .append("</table>");

        return sb.toString();


    }

    static String createPointsJSON(MapPoint$Orders[] mapPoints) {


        StringBuilder jsonString = new StringBuilder(ResponseCreator.JSON_POINTS_START_SUBSTRING);

        int mapPointsLengthMinus1 = mapPoints.length - 1;

        for (int i = 0; i < mapPointsLengthMinus1; i++) {
            jsonString.append(createPointJson(mapPoints[i], i)).append(",");
        }

        jsonString.append(createPointJson(mapPoints[mapPointsLengthMinus1], mapPointsLengthMinus1));

        jsonString.append(ResponseCreator.JSON_POINTS_FINISH_SUBSTRING);

        logger.info(jsonString);

        return jsonString.toString();

    }


    private static String createPointJson(MapPoint$Orders mapPoint$Orders, int id) {

        StringBuilder jsonPoint = new StringBuilder(ResponseCreator.JSON_POINT_PATTERN);

        String hint = ResponseCreator.createHint(mapPoint$Orders.getMapPoint());

        jsonPoint.insert(178, mapPoint$Orders.getMapPoint().getCoordinates());
        jsonPoint.insert(154, hint);
        jsonPoint.insert(135, hint);
        jsonPoint.insert(112, createBalloonStringForPoint(mapPoint$Orders));
        jsonPoint.insert(74, ResponseCreator.mysqlCoordinatesToJSONCoordinates(mapPoint$Orders.getMapPoint().getCoordinates()));
        jsonPoint.insert(26, id);

        return jsonPoint.toString();
    }
}
