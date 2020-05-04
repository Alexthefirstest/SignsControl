package rolesOrganisationsUsersController.dao.impl.factory;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rolesOrganisationsUsersController.bean.FactoryType;
import rolesOrganisationsUsersController.bean.Organisation;
import rolesOrganisationsUsersController.bean.Role;
import rolesOrganisationsUsersController.bean.User;
import rolesOrganisationsUsersController.dao.exceptions.DAOException;
import rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Factory {

    private static Logger logger = LogManager.getLogger(Factory.class);

    private Factory() {
    }

    private static final Factory INSTANCE = new Factory();

    public FactoryType createSignStaff(ResultSet rs, FactoryType signsStaff) throws SQLException, DAOValidationException {


        if (!rs.next()) {
            logger.warn("rs.next = false: "+signsStaff);
            throw new DAOValidationException("cant find new object or it's wasn't created");
        }


        if (signsStaff instanceof Role) {

            Role role = (Role) signsStaff;
            role.setId(rs.getInt(1));
            role.setRole(rs.getString(2));

            return role;
        }

        if (signsStaff instanceof Organisation) {

            Organisation organisation = (Organisation) signsStaff;
            organisation.setId(rs.getInt(1));
            organisation.setName(rs.getString(2));
            organisation.setRoleID(rs.getInt(3));
            organisation.setRole(rs.getString(4));
            organisation.setBlocked(rs.getBoolean(5));
            organisation.setInfo(rs.getString(6));

            return organisation;
        }
        if (signsStaff instanceof User) {

            User user = (User) signsStaff;

            user.setId(rs.getInt(1));
            user.setLogin(rs.getString(2));
            user.setOrganisationID(rs.getInt(3));
            user.setOrganisation(rs.getString(4));
            user.setBlock(rs.getBoolean(5));
            user.setName(rs.getString(6));
            user.setSurname(rs.getString(7));
            user.setInfo(rs.getString(8));

            return user;
        }

        logger.warn("did't find if: createSignsStaff method, signStaff class: " + signsStaff.getClass());
        return null;

    }

    public FactoryType[] createSignStaffArr(ResultSet rs, FactoryType signsStaff) throws SQLException {

        if (signsStaff instanceof Role) {
            ArrayList<FactoryType> signsStaffArr = new ArrayList<>();

            while (rs.next()) {

                signsStaffArr.add
                        (new Role(rs.getInt(1), rs.getString(2)));
            }

            return signsStaffArr.toArray(new Role[0]);
        }

        if (signsStaff instanceof Organisation) {
            ArrayList<FactoryType> signsStaffArr = new ArrayList<>();

            while (rs.next()) {

                signsStaffArr.add
                        (new Organisation(rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4), rs.getBoolean(5), rs.getString(6)));
            }

            return signsStaffArr.toArray(new Organisation[0]);
        }

        if (signsStaff instanceof User) {
            ArrayList<FactoryType> signsStaffArr = new ArrayList<>();

            while (rs.next()) {

                signsStaffArr.add
                        (new User(rs.getInt(1), rs.getString(2), rs.getInt(3),
                                rs.getString(4), rs.getBoolean(5),
                                rs.getString(6), rs.getString(7), rs.getString(8)));
            }

            return signsStaffArr.toArray(new User[0]);
        }


        logger.warn("did't find if: createSignsStaff[] method, signStaff class: " + signsStaff.getClass());
       return null;

    }


    public static Factory getINSTANCE() {
        return INSTANCE;
    }
}
