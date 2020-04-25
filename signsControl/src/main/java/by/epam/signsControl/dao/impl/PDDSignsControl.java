package by.epam.signsControl.dao.impl;

import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.dao.IPDDSignsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class PDDSignsControl implements IPDDSignsControl {


    private static Logger logger = LogManager.getLogger(PDDSignsControl.class);

    private static final String SQL_SELECT_USE_LAST_INSERT_ID = "SELECT * FROM `pdd_signs` where id=LAST_INSERT_ID();";
    private static final String SQL_INSERT_UNIQUE = "INSERT INTO `pdd_signs` (`pdd_section`, `pdd_sign`, `pdd_kind`) " +
            "SELECT * FROM (SELECT ? as se, ? as si, ? as ki) as rt " +
            "where not exists(SELECT `pdd_section`, `pdd_sign`, `pdd_kind` FROM pdd_signs where `pdd_section`=? AND `pdd_sign`=? AND `pdd_kind`=? ) LIMIT 1";
    private static final String SQL_DELETE = "DELETE FROM `pdd_signs` WHERE (`id` = ?);";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM pdd_signs where id=";
    private static final String SQL_SET_SECTION = "UPDATE `pdd_signs` SET `pdd_section` = ? WHERE (`id` = ?);";
    private static final String SQL_SET_SIGN = "UPDATE `pdd_signs` SET `pdd_sign` = ? WHERE (`id` = ?);";
    private static final String SQL_SET_KIND = "UPDATE `pdd_signs` SET `pdd_kind` = ? WHERE (`id` = ?);";
    private static final String SQL_SELECT_ALL = "SELECT * FROM pdd_signs order by pdd_section, pdd_sign, pdd_kind";
    private static final String SQL_SELECT_BY_SECTION = "SELECT * FROM pdd_signs WHERE pdd_section=? order by pdd_section, pdd_sign, pdd_kind";
    private static final String SQL_SELECT_BY_SECTION_AND_SIGN = "SELECT * FROM pdd_signs WHERE pdd_section=? AND pdd_sign=? order by pdd_section, pdd_sign, pdd_kind";

    //private static final String SQL_SELECT_BY_SECTION_SIGN_KIND = "SELECT id FROM `pdd_signs` where pdd_section=? AND pdd_sign=? AND pdd_kind=?";

    @Override
    public Sign addSign(int section, int number, int kind) throws DAOException {

        try {

            return (Sign) RequestExecutor.createFieldWithExistingCheck
                    (SQL_INSERT_UNIQUE, SQL_SELECT_USE_LAST_INSERT_ID, new Sign(), section, number, kind, section, number, kind);

        } catch (SQLException ex) {

            logger.warn("add sign fail: section, number, kind: " + section + ", " + number + ", " + kind, ex);
            throw new DAOException(ex);

        }

    }

    @Override
    public Sign addSign(int section, int number) throws DAOException {
        return addSign(section, number, 0);
    }

    @Override
    public Sign removeSign(int id) throws DAOException {
        try {

            return (Sign) RequestExecutor.createField
                    (SQL_DELETE, SQL_FIND_BY_ID + id, new Sign(), id);

        } catch (SQLException ex) {

            logger.warn("remove sign fail: id: " + id, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean updateSection(int id, int section) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_SECTION, id, section);

        } catch (SQLException ex) {

            logger.warn("updateSection sign fail: id: " + id, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean updateNumber(int id, int number) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_SIGN, id, number);

        } catch (SQLException ex) {

            logger.warn("updateNumber fail: id: " + id, ex);
            throw new DAOException(ex);

        }

    }

    @Override
    public boolean updateKind(int id, int kind) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_KIND, id, kind);

        } catch (SQLException ex) {

            logger.warn("updateKind fail: id: " + id, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setPicture(int id, Byte[] bytes) {
        return false;
    }

    @Override
    public Byte[] getPicture(int id) {
        return new Byte[0];
    }

    private Sign[] getPddSignsDef(String sql, int...parameters) throws DAOException {
        try {

            return (Sign[]) RequestExecutor.getSignsStaff(sql, new Sign(), parameters);

        } catch (SQLException ex) {

            logger.warn("getSigns fail ", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public Sign[] getPddSigns() throws DAOException {

       return getPddSignsDef(SQL_SELECT_ALL);
    }

    @Override
    public Sign[] getPddSigns(int section) throws DAOException {
        return getPddSignsDef(SQL_SELECT_BY_SECTION, section);
    }

    @Override
    public Sign[] getPddSigns(int section, int number) throws DAOException {
        return getPddSignsDef(SQL_SELECT_BY_SECTION_AND_SIGN, section, number);
    }

// private static final String SQL_SELECT_LAST_INSERT_ID = "SELECT LAST_INSERT_ID() FROM standard_sizes where number=LAST_INSERT_ID();";


}
