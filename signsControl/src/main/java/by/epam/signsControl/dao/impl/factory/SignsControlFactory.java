package by.epam.signsControl.dao.impl.factory;

import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.bean.MapPoint$LocalSign;
import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.bean.FactoryType;
import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
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

    public FactoryType createSignStaff(ResultSet rs, FactoryType signsStaff) throws SQLException, DAOValidationException {


        if (!rs.next()) {
            logger.info("rs.next = false");
            throw new DAOValidationException("cant find new object or it's wasn't created");
        }

        if (signsStaff instanceof LocalSign) {

            LocalSign localSign = (LocalSign) signsStaff;
            localSign.setLocalSignId(rs.getInt(1));
            localSign.setSignListId(rs.getInt(2));
            localSign.setId(rs.getInt(3));
            localSign.setSection(rs.getInt(4));
            localSign.setSign(rs.getInt(5));
            localSign.setKind(rs.getInt(6));
            localSign.setPicture(rs.getBytes(7));
            localSign.setStandardSize(rs.getInt(8));
            localSign.setDateOfAdd(rs.getDate(9));
            localSign.setDateOfRemove(rs.getDate(10));
            localSign.setAnnotation(rs.getString(11));
            localSign.setAngle(rs.getInt(12));

            return localSign;
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
            mapPoint.addAngle(rs.getInt(4));
            mapPoint.addAnnotation(rs.getString(5));

            while (rs.next()) {
                mapPoint.addAddress(rs.getString(2));
                mapPoint.addSignsList(rs.getInt(3));
                mapPoint.addAngle(rs.getInt(4));
                mapPoint.addAnnotation(rs.getString(5));
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

    public FactoryType[] createSignStaffArr(ResultSet rs, FactoryType signsStaff) throws SQLException {

        if (signsStaff instanceof MapPoint$LocalSign) {

            MapPoint$LocalSign mapPoint$LocalSign;
            MapPoint mapPoint;

            ArrayList<FactoryType> mapPoint$LocalSigns = new ArrayList<>();


            boolean stillNext = rs.next();

            while (stillNext) {

                mapPoint$LocalSign = new MapPoint$LocalSign();
                mapPoint = new MapPoint();

                mapPoint.setCoordinates(rs.getString(13));

                do {


                    mapPoint.addAddress(rs.getString(14));
                    mapPoint.addSignsList(rs.getInt(2));
                    mapPoint.addAngle(rs.getInt(12));
                    mapPoint.addAnnotation(rs.getString(11));

                    mapPoint$LocalSign.addLocalSignArr();

                    do {
                        mapPoint$LocalSign.addLocalSignToLastArr(new LocalSign(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                                rs.getInt(4), rs.getInt(5), rs.getInt(6),
                                rs.getBytes(7), rs.getInt(8), rs.getDate(9),
                                rs.getDate(10), rs.getString(11), rs.getInt(12),
                                rs.getString(15), rs.getString(16)));
                    } while ((stillNext = rs.next()) && rs.getInt(2) == mapPoint$LocalSign.getLocalSignFromLastArr(0).getSignListId());

                } while (stillNext && rs.getString(1).equals(mapPoint.getCoordinates()));

                mapPoint$LocalSign.setMapPoint(mapPoint);
                mapPoint$LocalSigns.add(mapPoint$LocalSign);
            }
            return mapPoint$LocalSigns.toArray(new MapPoint$LocalSign[0]);
        }

        if (signsStaff instanceof LocalSign) {

            ArrayList<FactoryType> signsStaffArr = new ArrayList<>();

            while (rs.next()) {

                signsStaffArr.add
                        (new LocalSign(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                                rs.getInt(4), rs.getInt(5), rs.getInt(6),
                                rs.getBytes(7), rs.getInt(8), rs.getDate(9),
                                rs.getDate(10), rs.getString(11), rs.getInt(12),
                                rs.getString(6), rs.getString(7)));
            }

            return signsStaffArr.toArray(new LocalSign[0]);

        }

        if (signsStaff instanceof Sign) {
            ArrayList<FactoryType> signsStaffArr = new ArrayList<>();

            while (rs.next()) {

                signsStaffArr.add
                        (new Sign(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                                rs.getInt(4), rs.getBytes(5), rs.getString(6), rs.getString(7)));//etogo net
            }

            return signsStaffArr.toArray(new Sign[0]);
        }

        if (signsStaff instanceof StandardSize) {

            ArrayList<FactoryType> signsStaffArr = new ArrayList<>();

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

                do {

                    mapPoint.addAddress(rs.getString(2));
                    mapPoint.addSignsList(rs.getInt(3));
                    mapPoint.addAngle(rs.getInt(4));
                    mapPoint.addAnnotation(rs.getString(5));

                } while ((stillNext = rs.next()) && rs.getString(1).equals(mapPoint.getCoordinates()));


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
