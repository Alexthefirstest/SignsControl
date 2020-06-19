module rolesOrganisationsUsersController {

    requires connectionPoolForDataBase;

    requires org.apache.logging.log4j;
    requires java.sql;
    requires jbcrypt;

    exports by.epam.rolesOrganisationsUsersController.bean;
    exports by.epam.rolesOrganisationsUsersController.service;
    exports by.epam.rolesOrganisationsUsersController.service.factory;
    exports by.epam.rolesOrganisationsUsersController.service.exceptions;
}