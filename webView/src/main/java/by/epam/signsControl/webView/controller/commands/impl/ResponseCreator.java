package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.bean.LocalSign;
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
    private static final String JSON_POINTS_START_SUBSTRING = "{\"type\": \"FeatureCollection\",\"features\": [";
    private static final String JSON_POINTS_FINISH_SUBSTRING = "] }";

    private ResponseCreator() {
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

        String hint = createHint(mapPoint$LocalSign);

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

    private static String createHint(MapPoint$LocalSign mapPoint$LocalSign) {


        StringBuilder hint = new StringBuilder();

        ArrayList<String> addresses = new ArrayList<>(mapPoint$LocalSign.getMapPoint().getAddresses());
        ArrayList<String> annotations = new ArrayList<>(mapPoint$LocalSign.getMapPoint().getAnnotations());
        ArrayList<Integer> angles = new ArrayList<>(mapPoint$LocalSign.getMapPoint().getAngles());  //мб будет стринг или чар потом!!!

        for (int i = 0; i < angles.size(); i++) {
            hint.append(addresses.get(i)).append(", ").
                    append(angles.get(i)).append(", ").
                    append(annotations.get(i)).append(";<br />");
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

        logger.info("inside createBaloonString");


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


        if (sb.length() > 0) {

            sb.insert(0, "direction: " + localSignsWithCommonDirection.get(0).getAngle());
        } else {

            sb.append("direction: " + localSignsWithCommonDirection.get(0).getAngle() + "<br /> no current signs for this direction");
        }


        return sb.toString();

    }

    public static String createPointHistory(LocalSign[] localSigns) {

        StringBuilder pointHistory = new StringBuilder();

        pointHistory.append("sign").append("|name").append("|опиcание").append("|размер").append("|направление")
                .append("|дата добавления").append("|дата удаления").append("|картинка<br />");

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
        sb.append("|" + localSigns.getDateOfAdd()+"|");
        sb.append(((dateOfRemove = localSigns.getDateOfRemove()) != null) ? dateOfRemove+"|" : "-|");
        sb.append(((picture = localSigns.getPicture()) != null) ? picture : "-");

        return sb.toString();
    }


}
