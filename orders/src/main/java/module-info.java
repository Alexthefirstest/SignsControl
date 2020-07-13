module orders {

    requires connectionPoolForDataBase;
    requires rolesOrganisationsUsersController;
    requires bank;
    requires signsControl;

    requires org.apache.logging.log4j;
    requires java.sql;

    exports by.epam.orders.bean;
    exports by.epam.orders.service;
    exports by.epam.orders.service.factory;
    exports by.epam.orders.service.exceptions;

}