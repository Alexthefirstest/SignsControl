package by.epam.bank.dao.impl;

import by.epam.bank.dao.IOrganisationsDeliver;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.connectionPoolForDataBase.connectionPool.factory.ConnectionPoolFactory;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrganisationDeliver implements IOrganisationsDeliver {

    private static Logger logger = LogManager.getLogger(OrganisationDeliver.class);

    private static final String GET_ORGANISATIONS_WITHOUT_BANK_ACCOUNTS = " SELECT org.id, org.name, org.role, orgR.role, org.is_blocked, " +
            " org.info FROM organisations as org left join bank_accounts on bank_accounts.organisation_id=org.id join organisation_roles as orgR on org.role=orgR.id " +
            " where bank_accounts.balance is null order by org.name ";

    @Override
    public Organisation[] showOrganisationsWithoutBankAccounts() throws DAOException {
        ResultSet rs = null;

        Connection connection = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance().retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ORGANISATIONS_WITHOUT_BANK_ACCOUNTS)) {

            rs = ps.executeQuery();

            ArrayList<Organisation> organisations = new ArrayList<>();

            while (rs.next()) {

                organisations.add
                        (new Organisation(rs.getInt(1), rs.getString(2),
                                new Role(rs.getInt(3), rs.getString(4)), rs.getBoolean(5), rs.getString(6)));
            }

            return organisations.toArray(new Organisation[0]);


        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn("closeResultSetEx", ex);
                }

            }

            ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance().releaseConnection(connection);
        }
    }
}
