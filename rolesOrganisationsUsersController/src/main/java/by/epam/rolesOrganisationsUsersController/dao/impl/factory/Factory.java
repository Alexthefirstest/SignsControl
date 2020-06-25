package by.epam.rolesOrganisationsUsersController.dao.impl.factory;


import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.rolesOrganisationsUsersController.bean.FactoryType;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * class for creating bean objects, that implements {@link FactoryType} from {@link ResultSet}
 *
 * @author Bulgak Alexander
 */
public class Factory {

    /**
     * log4j2 logger
     */
    private static Logger logger = LogManager.getLogger(Factory.class);

    /**
     * this class instance
     */
    private static final Factory INSTANCE = new Factory();

    /**
     * empty constructor
     */
    private Factory() {
    }

    /**
     * create exemplar of bean object, that implements {@link FactoryType} from {@link ResultSet} by filling object
     * from parameters using setters
     *
     * @param rs         {@link ResultSet} for creating bean object
     * @param signsStaff bean object implements {@link FactoryType}
     * @return bean object, that implements {@link FactoryType} or null in case of can't find object creator inside method
     * @throws SQLException           if it been threw {@link ResultSet}
     * @throws DAOValidationException in case of empty {@link ResultSet}
     */
    public FactoryType createSignStaff(ResultSet rs, FactoryType signsStaff) throws SQLException, DAOValidationException {


        if (!rs.next()) {
            logger.warn("rs.next = false: " + signsStaff);
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
            user.setRole(rs.getInt(3));
            user.setOrganisationID(rs.getInt(4));
            user.setOrganisation(rs.getString(5));
            user.setBlock(rs.getBoolean(6));
            user.setName(rs.getString(7));
            user.setSurname(rs.getString(8));
            user.setInfo(rs.getString(9));

            return user;
        }

        logger.warn("did't find if: createSignsStaff method, signStaff class: " + signsStaff.getClass());
        return null;

    }


    /**
     * create array of exemplars of one of the bean objects, that implements {@link FactoryType} from {@link ResultSet}
     *
     * @param rs         {@link ResultSet} for creating bean object
     * @param signsStaff bean object implements {@link FactoryType} for defining a type of object
     * @return array of bean object, that implements {@link FactoryType}, including empty array in case of empty {@link ResultSet}
     * or null in case of can't find object creator inside method
     * @throws SQLException if it been threw {@link ResultSet}
     */
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
                        (new User(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                                rs.getString(5), rs.getBoolean(6),
                                rs.getString(7), rs.getString(8), rs.getString(9)));
            }

            return signsStaffArr.toArray(new User[0]);
        }


        logger.warn("did't find if: createSignsStaff[] method, signStaff class: " + signsStaff.getClass());
        return null;

    }


    /**
     * @return {@link Factory#INSTANCE}
     */
    public static Factory getINSTANCE() {
        return INSTANCE;
    }
}
