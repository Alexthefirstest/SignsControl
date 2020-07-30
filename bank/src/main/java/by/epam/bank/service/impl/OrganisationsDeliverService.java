package by.epam.bank.service.impl;

import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IOrganisationsDeliverService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;

/**
 * service to supply {@link Organisation}
 */
public class OrganisationsDeliverService implements IOrganisationsDeliverService {

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
            return DaoFactory.getINSTANCE().getOrganisationsDeliver().showOrganisationsWithoutBankAccounts();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
