package by.epam.bank.service.impl;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IOrganisationsDeliver;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IOrganisationsDeliverService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;

/**
 * service to supply {@link Organisation}
 */
public class OrganisationsDeliverService implements IOrganisationsDeliverService {

    private final IOrganisationsDeliver organisationsDeliver;

    /**
     * empty constructor
     */
    public OrganisationsDeliverService() {

        organisationsDeliver = DaoFactory.getINSTANCE().getOrganisationsDeliver();
    }

    /**
     * @param organisationsDeliverDao {@link IOrganisationsDeliver}
     */
    OrganisationsDeliverService(IOrganisationsDeliver organisationsDeliverDao) {
        organisationsDeliver = organisationsDeliverDao;
    }

    /**
     * get organisations with no bank accounts
     *
     * @return {@link Organisation}
     * @throws ServiceValidationException when {@link by.epam.bank.dao.IOrganisationsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link by.epam.bank.dao.IOrganisationsDeliver} throw {@link DAOException}
     */
    @Override
    public Organisation[] showOrganisationsWithoutBankAccounts() throws ServiceException {
        try {
            return organisationsDeliver.showOrganisationsWithoutBankAccounts();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get organisation with bank account or without in case account don't exist by id
     *
     * @param id organisation id
     * @return {@link Organisation} or null if organisation do not exist
     * @throws ServiceValidationException when {@link by.epam.bank.dao.IOrganisationsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link by.epam.bank.dao.IOrganisationsDeliver} throw {@link DAOException}
     */
    @Override
    public BankAccount findOrganisationByID(int id) throws ServiceException {

        try {
            return organisationsDeliver.findOrganisationByID(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
