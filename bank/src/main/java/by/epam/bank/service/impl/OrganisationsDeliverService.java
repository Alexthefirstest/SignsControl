package by.epam.bank.service.impl;

import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IOrganisationsDeliverService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;

public class OrganisationsDeliverService implements IOrganisationsDeliverService {
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
