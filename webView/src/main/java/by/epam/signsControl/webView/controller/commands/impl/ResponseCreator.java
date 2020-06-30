package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.bean.MapPoint$LocalSign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseCreator {

    private static final Logger logger = LogManager.getLogger(ResponseCreator.class);

    private static final String JSON_POINT_PATTERN = "{\"type\": \"Feature\", \"id\": , \"geometry\": {\"type\": \"Point\", \"coordinates\": []}, \"properties\": {\"balloonContent\": \"\", \"clusterCaption\" : \"\", \"hintContent\": \"\", \"pointCoordinates\": \"\"}}";
    private static final String JSON_POINT_ONLY_HINT_PATTERN = "{\"type\": \"Feature\", \"id\": , \"geometry\": {\"type\": \"Point\", \"coordinates\": []}, \"properties\": {\"hintContent\": \"\", \"pointCoordinates\": \"\",\"clusterCaption\":\"\" }}";
    private static final String JSON_POINTS_START_SUBSTRING = "{\"type\": \"FeatureCollection\",\"features\": [";
    private static final String JSON_POINTS_FINISH_SUBSTRING = "] }";

    private ResponseCreator() {
    }

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

    private static String mysqlCoordinatesToJSONCoordinates(String coordinates) {
        Pattern pattern = Pattern.compile("POINT\\((\\d+\\.?\\d*) (\\d+\\.?\\d*)\\)");
        Matcher matcher = pattern.matcher(coordinates);

        if (!matcher.find()) {
            logger.warn("wrong coordinate format: " + coordinates);
            return null;
        }

        return matcher.group(1) + ", " + matcher.group(2);
    }

    private static String createHint(MapPoint mapPoint) {

        String annotation;

        StringBuilder hint = new StringBuilder();

        ArrayList<String> addresses = new ArrayList<>(mapPoint.getAddresses());
        ArrayList<String> annotations = new ArrayList<>(mapPoint.getAnnotations());
        ArrayList<Character> angles = new ArrayList<>(mapPoint.getAngles());

        for (int i = 0; i < angles.size(); i++) {
            hint.append(addresses.get(i)).append(", ").
                    append(angles.get(i)).append(", ").
                    append((annotation = annotations.get(i)) == null || annotation.isEmpty() ? "- " : annotation).append(";<br />");
        }

        return hint.toString();

    }


    private static String createBaloonJSON(MapPoint$LocalSign mapPoint$LocalSign, boolean allPoints) {


        StringBuilder baloon = new StringBuilder();

        for (int i = 0; i < mapPoint$LocalSign.getListOfLocalSignsArrays().size(); i++) {

            baloon.append(createBalloonStringForDirection(mapPoint$LocalSign.getLocalSignsArrays(i), allPoints));
            baloon.append("<br /><br />");

        }

        return baloon.toString();

    }

    private static String createBalloonStringForDirection(ArrayList<LocalSign> localSignsWithCommonDirection, boolean allPoints) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < localSignsWithCommonDirection.size(); i++) {


            if ((allPoints && (localSignsWithCommonDirection.get(i).getDateOfRemove()) != null)) {

                continue;
            }


            sb.append("<br />" + localSignsWithCommonDirection.get(i).getSection() + ".");

            sb.append(localSignsWithCommonDirection.get(i).getSign());

            int kind;
            if ((kind = localSignsWithCommonDirection.get(i).getKind()) > -1) {

                sb.append("." + kind);
            }


            sb.append("   " + localSignsWithCommonDirection.get(i).getName());

            byte[] picture;
            if ((picture = localSignsWithCommonDirection.get(i).getPicture()) != null) {

                sb.append("   " + picture);
            }

        }


        if (sb.length() > 0 && localSignsWithCommonDirection.get(0).getDateOfAdd() != null) {

            sb.insert(0, "direction: " + localSignsWithCommonDirection.get(0).getAngle());

            return sb.toString();

        } else {

            return ("direction: " + localSignsWithCommonDirection.get(0).getAngle() + "<br /> no current signs for this direction");
        }


    }

    public static String createPointHistory(LocalSign[] localSigns) {

        StringBuilder pointHistory = new StringBuilder();

        pointHistory.append("sign").append("|name").append("|description").append("|size").append("|direction")
                .append("|date of add").append("|date of remove").append("|picture<br />");

        for (LocalSign localSign : localSigns) {
            pointHistory.append(createPointHistoryField(localSign)).append("<br/>");

        }

        return pointHistory.toString();

    }

    private static String createPointHistoryField(LocalSign localSigns) {

        StringBuilder sb = new StringBuilder();

        int signKind;
        String description;
        Date dateOfRemove;
        byte[] picture;

        sb.append(localSigns.getSection()).
                append("." + localSigns.getSign())
                .append(((signKind = localSigns.getKind()) > -1) ? "." + signKind : "");

        sb.append("|" + localSigns.getName() + "|");
        sb.append(((description = localSigns.getDescription()) != null) ? description : "-");
        sb.append("|" + localSigns.getStandardSize());
        sb.append("|" + localSigns.getAngle());
        sb.append("|" + localSigns.getDateOfAdd() + "|");
        sb.append(((dateOfRemove = localSigns.getDateOfRemove()) != null) ? dateOfRemove + "|" : "-|");
        sb.append(((picture = localSigns.getPicture()) != null) ? picture : "-");

        return sb.toString();
    }


}
