package by.epam.signsControl.dao.impl.factory;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.bean.SignsStaff;
import by.epam.signsControl.bean.StandardSize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignsControlFactory {

    private static Logger logger = LogManager.getLogger(SignsControlFactory.class);

    private SignsControlFactory() {
    }

    private static final SignsControlFactory INSTANCE = new SignsControlFactory();

    public SignsStaff createSignStaff(ResultSet rs, SignsStaff signsStaff) throws SQLException {


        if (!rs.next()) {
            logger.info("rs.next = false");
            return null;
        }

        if (signsStaff instanceof Sign) {

            Sign sign = (Sign) signsStaff;
            sign.setId(rs.getInt(1));
            sign.setSection(rs.getInt(2));
            sign.setSign(rs.getInt(3));
            sign.setKind(rs.getInt(4));
            sign.setPicture(rs.getBytes(5));

            return sign;
        }

        if (signsStaff instanceof MapPoint) {

            MapPoint mapPoint = (MapPoint) signsStaff;

            mapPoint.setCoordinates(rs.getString(1));

            mapPoint.addAddress(rs.getString(2));
            mapPoint.addSignsList(rs.getInt(3));
            mapPoint.addAnnotation(rs.getString(4));

            while (rs.next()) {
                mapPoint.addAddress(rs.getString(2));
                mapPoint.addSignsList(rs.getInt(3));
                mapPoint.addAnnotation(rs.getString(4));
            }

            return mapPoint;
        }

        if (signsStaff instanceof StandardSize) {

            StandardSize standardSize = (StandardSize) signsStaff;
            standardSize.setSize(rs.getInt(1));
            standardSize.setInfo(rs.getString(2));

            return standardSize;
        }

        logger.warn("did't find if: createSignsStaff method, signStaff class: " + signsStaff.getClass());
        return null;

    }

    public SignsStaff[] createSignStaffArr(ResultSet rs, SignsStaff signsStaff) throws SQLException {

        if (signsStaff instanceof Sign) {
            ArrayList<SignsStaff> signsStaffArr = new ArrayList<>();

            while (rs.next()) {

                signsStaffArr.add
                        (new Sign(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                                rs.getInt(4), rs.getBytes(5)));
            }

            return signsStaffArr.toArray(new Sign[0]);
        }

        if (signsStaff instanceof StandardSize) {

            ArrayList<SignsStaff> signsStaffArr = new ArrayList<>();

            while (rs.next()) {

                signsStaffArr.add(new StandardSize(rs.getInt(1), rs.getString(2)));
            }


            return signsStaffArr.toArray(new StandardSize[0]);
        }

        if (signsStaff instanceof MapPoint) {

            MapPoint mapPoint;

            ArrayList<MapPoint> mapPoints = new ArrayList<>();

            boolean stillNext = rs.next();

            while (stillNext) {

                mapPoint = new MapPoint();

                mapPoint.setCoordinates(rs.getString(1));
                mapPoint.addAddress(rs.getString(2));
                mapPoint.addSignsList(rs.getInt(3));
                mapPoint.addAnnotation(rs.getString(4));


                while ((stillNext = rs.next()) && rs.getString(1).equals(mapPoint.getCoordinates())) {

                    mapPoint.addAddress(rs.getString(2));
                    mapPoint.addSignsList(rs.getInt(3));
                    mapPoint.addAnnotation(rs.getString(4));

                }


                mapPoints.add(mapPoint);

            }
            return mapPoints.toArray(new MapPoint[0]);
        }

        logger.warn("did't find if: createSignsStaff[] method, signStaff class: " + signsStaff.getClass());
        return null;

    }


    public static SignsControlFactory getINSTANCE() {
        return INSTANCE;
    }
}
