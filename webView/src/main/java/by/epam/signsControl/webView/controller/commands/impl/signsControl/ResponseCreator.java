package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.bean.MapPoint$LocalSign;
import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.bean.StandardSize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create json response
 */
public class ResponseCreator {

    private static final Logger logger = LogManager.getLogger(ResponseCreator.class);

    /**
     * yandex map json point pattern
     */
    public static final String JSON_POINT_PATTERN = "{\"type\": \"Feature\", \"id\": , \"geometry\": {\"type\": \"Point\", \"coordinates\": []}, \"properties\": {\"balloonContent\": \"\", \"clusterCaption\" : \"\", \"hintContent\": \"\", \"pointCoordinates\": \"\"}}";

    /**
     * yandex map json point pattern only with hint parameter
     */
    public static final String JSON_POINT_ONLY_HINT_PATTERN = "{\"type\": \"Feature\", \"id\": , \"geometry\": {\"type\": \"Point\", \"coordinates\": []}, \"properties\": {\"hintContent\": \"\", \"pointCoordinates\": \"\",\"clusterCaption\":\"\" }}";

    /**
     * yandex map json start substring
     */
    public static final String JSON_POINTS_START_SUBSTRING = "{\"type\": \"FeatureCollection\",\"features\": [";

    /**
     * yandex map json finish substring
     */
    public static final String JSON_POINTS_FINISH_SUBSTRING = "] }";

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    /**
     * private constructor
     */
    private ResponseCreator() {
    }

    /**
     * create json from array
     *
     * @param objects array of objects
     * @return json string
     */
    public static String createJSON(Object[] objects) {

        return gson.toJson(objects);
    }

//    static String createChangeSignJSON(LocalSign[] localSigns) {
//
//        StringBuilder json = new StringBuilder("{signs:[");
//
//        Date dateOfRemove;
//
//        for (LocalSign localSign : localSigns) {
//            json.append("{\"id\":").append(localSign.getLocalSignId());
//            json.append("\"info\":\"")
//                    .append(createSign(localSign.getSection(), localSign.getSign(), localSign.getKind()))
//                    .append(' ').append(localSign.getDateOfAdd())
//                    .append(' ').append((dateOfRemove = localSign.getDateOfRemove()) == null ? '-' : dateOfRemove)
//                    .append("\"}");
//        }
//
//        json.append("]}");
//
//        return json.toString();
//    }

    /**
     * create info for add point
     *
     * @param directions    directions to create
     * @param standardSizes to create
     * @param pddSigns      to create
     * @return json string
     */
    static String createAddPointInfoJSON(Direction[] directions, StandardSize[] standardSizes, Sign[] pddSigns) {

        StringBuilder json = new StringBuilder("{");


        json.append("\"signsLists\" :").append(gson.toJson(directions)).append(",");
        json.append("\"pddSigns\" : ").append(createSignJSONArr(pddSigns)).append(",");
        json.append("\"standardSizes\" : ").append(gson.toJson(standardSizes));

        json.append("}");

        return json.toString();
    }

    /**
     * create pdd signs arr json
     *
     * @param pddSigns pdd signs to create
     * @return json string
     */
    public static String createSignJSONArr(Sign[] pddSigns) {

        StringBuilder json = new StringBuilder("[");

        for (Sign sign : pddSigns) {
            json.append("{\"id\":").append(sign.getId());
            json.append(",\"sign\":").append(createSignWithQuotes(sign.getSection(), sign.getSign(), sign.getKind()));
            json.append("},");
        }

        return json.substring(0, json.length() - 1) + "]";
    }

    /**
     * create json string from directions
     *
     * @param directionsObj directions
     * @return json string
     */
    static String createDirectionsJSON(Direction[] directionsObj) {
        StringBuilder ids = new StringBuilder("[");
        StringBuilder directions = new StringBuilder("[");

        for (Direction directionObj : directionsObj) {
            ids.append(directionObj.getId()).append(',');
            directions.append('\"').append(directionObj.getDirection()).append("\",");
        }

        String finalIDS = ids.substring(0, ids.length() - 1) + "]";
        String finalDirections = directions.substring(0, directions.length() - 1) + "]";

        return "{\"ids\":" + finalIDS + ",\"directions\":" + finalDirections + "}";
    }

    /**
     * create points yandex maps json from map points
     *
     * @param mapPoints to create
     * @return json string in yandex map format
     */
    static String createPointsJSON(MapPoint[] mapPoints) {


        StringBuilder jsonString = new StringBuilder(JSON_POINTS_START_SUBSTRING);

        int mapPointsLengthMinus1 = mapPoints.length - 1;


        for (int i = 0; i < mapPointsLengthMinus1; i++) {

            StringBuilder sb = new StringBuilder(JSON_POINT_ONLY_HINT_PATTERN);

            String hint = createHint(mapPoints[i]);

            sb.insert(153, hint);
            sb.insert(133, mapPoints[i].getCoordinates());
            sb.insert(109, hint);
            sb.insert(74, mysqlCoordinatesToJSONCoordinates(mapPoints[i].getCoordinates()));
            sb.insert(26, i);


            jsonString.append(sb).append(',');


        }

        StringBuilder sb = new StringBuilder(JSON_POINT_ONLY_HINT_PATTERN);

        sb.insert(133, mapPoints[mapPointsLengthMinus1].getCoordinates());
        sb.insert(109, createHint(mapPoints[mapPointsLengthMinus1]));
        sb.insert(74, mysqlCoordinatesToJSONCoordinates(mapPoints[mapPointsLengthMinus1].getCoordinates()));
        sb.insert(26, mapPointsLengthMinus1);
        jsonString.append(sb);

        jsonString.append(JSON_POINTS_FINISH_SUBSTRING);

        logger.info(jsonString);

        return jsonString.toString();

    }

    /**
     * create point json from map point with local signs
     *
     * @param mapPoints to create
     * @param allPoints boolean to add only point with date of remove is null
     * @return json yandex map format string
     */
    static String createPointsJSON(MapPoint$LocalSign[] mapPoints, boolean allPoints) {


        StringBuilder jsonString = new StringBuilder(JSON_POINTS_START_SUBSTRING);

        int mapPointsLengthMinus1 = mapPoints.length - 1;

        for (int i = 0; i < mapPointsLengthMinus1; i++) {
            jsonString.append(createPointJson(mapPoints[i], i, allPoints)).append(",");
        }

        jsonString.append(createPointJson(mapPoints[mapPointsLengthMinus1], mapPointsLengthMinus1, allPoints));

        jsonString.append(JSON_POINTS_FINISH_SUBSTRING);

        logger.info(jsonString);

        return jsonString.toString();

    }

    /*create json from point*/
    private static String createPointJson(MapPoint$LocalSign mapPoint$LocalSign, int id, boolean allPoints) {

        StringBuilder jsonPoint = new StringBuilder(JSON_POINT_PATTERN);

        String hint = createHint(mapPoint$LocalSign.getMapPoint());

        jsonPoint.insert(178, mapPoint$LocalSign.getMapPoint().getCoordinates());
        jsonPoint.insert(154, hint);
        jsonPoint.insert(135, hint);
        jsonPoint.insert(112, createBaloonJSON(mapPoint$LocalSign, allPoints));
        jsonPoint.insert(74, mysqlCoordinatesToJSONCoordinates(mapPoint$LocalSign.getMapPoint().getCoordinates()));
        jsonPoint.insert(26, id);

        return jsonPoint.toString();
    }

    /**
     * coordinates in mysql pattern to json pattern
     *
     * @param coordinates to create
     * @return json coordinates pattern
     */
    public static String mysqlCoordinatesToJSONCoordinates(String coordinates) {
        Pattern pattern = Pattern.compile("POINT\\((\\d+\\.?\\d*) (\\d+\\.?\\d*)\\)");
        Matcher matcher = pattern.matcher(coordinates);

        if (!matcher.find()) {
            logger.warn("wrong coordinate format: " + coordinates);
            return null;
        }

        return matcher.group(1) + ", " + matcher.group(2);
    }

    /**
     * create hint for yandex map point
     *
     * @param mapPoint map point to create hint
     * @return hint
     */
    public static String createHint(MapPoint mapPoint) {

        // String annotation;

        StringBuilder hint = new StringBuilder();

        ArrayList<String> addresses = new ArrayList<>(mapPoint.getAddresses());
        ArrayList<String> annotations = new ArrayList<>(mapPoint.getAnnotations());
        ArrayList<Character> angles = new ArrayList<>(mapPoint.getAngles());

        for (int i = 0; i < angles.size(); i++) {
            hint.append(addresses.get(i)).append(", ").
                    append(angles.get(i)).append(", ").
                    // append((annotation = annotations.get(i)) == null || annotation.isEmpty() ? "- " : annotation).append(";<br />");
                            append((annotations.get(i))).append(";<br />");
        }

        return hint.toString();

    }

    /*
     * create baloon for yandex maps
     */
    private static String createBaloonJSON(MapPoint$LocalSign mapPoint$LocalSign, boolean allPoints) {


        StringBuilder baloon = new StringBuilder();

        for (int i = 0; i < mapPoint$LocalSign.getListOfLocalSignsArrays().size(); i++) {

            baloon.append(createBalloonStringForDirection(mapPoint$LocalSign.getLocalSignsArrays(i), allPoints));
            baloon.append("<br /><br />");

        }

        return baloon.toString();

    }

    /*
     * create part of baloon for one direction
     */
    private static String createBalloonStringForDirection(ArrayList<LocalSign> localSignsWithCommonDirection, boolean allPoints) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < localSignsWithCommonDirection.size(); i++) {


            if ((allPoints && (localSignsWithCommonDirection.get(i).getDateOfRemove()) != null)) {

                continue;
            }


            sb.append("<h5 >" + createSign(localSignsWithCommonDirection.get(i).getSection(),
                    localSignsWithCommonDirection.get(i).getSign(),
                    localSignsWithCommonDirection.get(i).getKind()));


            sb.append(" -  " + localSignsWithCommonDirection.get(i).getName() + "</h5>");


//            sb.append((localSignsWithCommonDirection.get(i).getPicture() != null) ?
//                    (" "+pictureStartPattern+contextPath+pictureMiddlePatter + localSignsWithCommonDirection.get(i).getId() + pictureFinishPattern)
//                    : " -");


        }


        if (sb.length() > 0 && localSignsWithCommonDirection.get(0).getDateOfAdd() != null) {

            sb.insert(0, "<h3>      \u043d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435: " + localSignsWithCommonDirection.get(0).getAngle() + "</h3>");
//            sb.insert(0, "<h3>      direction: " + localSignsWithCommonDirection.get(0).getAngle()+"</h3>");

            return sb.toString();

        } else {

            return ("<h3>      \u043d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435: " + localSignsWithCommonDirection.get(0).getAngle() + "</h3><br />     \u043d\u0435\u0442 \u0430\u043a\u0442\u0443\u0430\u043b\u044c\u043d\u044b\u0445 \u0437\u043d\u0430\u043a\u043e\u0432 \u0434\u043b\u044f \u044d\u0442\u043e\u0433\u043e \u043d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u044f");
//            return ("<h3>      direction: " + localSignsWithCommonDirection.get(0).getAngle() + "</h3><br />     no current points for this direction");
        }


    }

    /**
     * create point history in json format
     *
     * @param localSigns  to create history
     * @param contextPath to find image of sign
     * @return json string
     */
    static String createPointHistory(LocalSign[] localSigns, String contextPath) {

        StringBuilder pointHistory = new StringBuilder();

        pointHistory.append("<table>").append("<thead>").append("<tr>");

//        pointHistory.append("<th scope=\"col\">direct</th>")
//                .append("<th scope=\"col\">sign</th>")
//                .append("<th scope=\"col\">name</th>")
//                .append("<th scope=\"col\">description</th>")
//                .append("<th scope=\"col\">standard size</th>")
//                .append("<th scope=\"col\">date of add</th>")
//                .append("<th scope=\"col\">date of remove</th>")
//                .append("<th scope=\"col\">information</th>")
//                .append("<th scope=\"col\">image</th>");

        pointHistory.append("<th scope=\"col\">\u043d\u0430\u043f\u0440.</th>")
                .append("<th scope=\"col\">\u0437\u043d\u0430\u043a</th>")
                .append("<th scope=\"col\">\u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435</th>")
                .append("<th scope=\"col\">\u043e\u043f\u0438\u0441\u0430\u043d\u0438\u0435</th>")
                .append("<th scope=\"col\">\u0442\u0438\u043f\u043e\u0440\u0430\u0437\u043c\u0435\u0440</th>")
                .append("<th scope=\"col\">\u0434\u0430\u0442\u0430 \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0438\u044f</th>")
                .append("<th scope=\"col\">\u0434\u0430\u0442\u0430 \u0441\u043d\u044f\u0442\u0438\u044f</th>")
                .append("<th scope=\"col\">\u0438\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u044f</th>")
                .append("<th scope=\"col\">\u0438\u0437\u043e\u0431\u0440\u0430\u0436\u0435\u043d\u0438\u0435</th>");

        pointHistory.append("</tr>").append("</thead>");

        pointHistory.append("<tbody>");

        for (LocalSign localSign : localSigns) {

            pointHistory.append("<tr>")
                    .append(createPointHistoryField(localSign, contextPath))
                    .append("</tr>");

        }


        pointHistory.append("</tbody>").append("</table>");

        return pointHistory.toString();

    }


    /*finish part for add picture to html*/
    private static final String pictureFinishPattern = "\"/>";

    /*middle part for add picture to html*/
    private static final String pictureMiddlePatter = "/upload?id=";

    /*start part for add picture to html*/
    private static final String pictureStartPattern = "<img src=\"";

    private static String createPointHistoryField(LocalSign localSign, String contextPath) {

        StringBuilder sb = new StringBuilder();

        //   String description;
        Date dateOfRemove;

        sb.append("<td>" + localSign.getAngle() + "</tb>");
        sb.append("<td>" + createSign(localSign.getSection(), localSign.getSign(), localSign.getKind()) + "</tb>");

        sb.append("<td>" + localSign.getName() + "</tb>");
        //sb.append(((description = localSign.getDescription()) != null) ? description : "-");
        sb.append("<td>" + localSign.getDescription() + "</tb>");
        sb.append("<td>" + localSign.getStandardSize() + "</tb>");

        sb.append("<td>" + localSign.getDateOfAdd() + "</tb>");
        sb.append("<td>" + (((dateOfRemove = localSign.getDateOfRemove()) != null) ? dateOfRemove : "-") + "</tb>");
        sb.append("<td>" + localSign.getAnnotation() + "</tb>");
        sb.append("<td>" + ((localSign.getPicture() != null) ?
                (pictureStartPattern + contextPath + pictureMiddlePatter + localSign.getId() + pictureFinishPattern)
                : "-") + "</tb>");
        return sb.toString();
    }

    /**
     * create full sign string from parameters
     *
     * @param section sign section
     * @param sign    sign number
     * @param kind    sign kind
     * @return string sign
     */
    public static String createSign(int section, int sign, int kind) {
        return (section + "." + sign + ((kind > -1) ? ("." + kind) : ""));
    }

    /**
     * create full sign string from parameters in quotes
     *
     * @param section sign section
     * @param sign    sign number
     * @param kind    sign kind
     * @return string sign
     */
    private static String createSignWithQuotes(int section, int sign, int kind) {

        return ("\"" + section + "." + sign + ((kind > -1) ? ("." + kind) : "") + '\"');
    }


}
