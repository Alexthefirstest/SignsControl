module bank {

    requires connectionPoolForDataBase;
    requires rolesOrganisationsUsersController;

    requires org.apache.logging.log4j;
    requires java.sql;

    exports by.epam.bank.bean;
    exports by.epam.bank.service;
    exports by.epam.bank.service.factory;
    exports by.epam.bank.service.exceptions;
}