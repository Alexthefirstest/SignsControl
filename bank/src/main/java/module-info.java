module bank {

    requires connectionPoolForDataBase;
    requires rolesOrganisationsUsersController;

    requires org.apache.logging.log4j;
    requires java.sql;

    exports by.epam.bank.bean;
//    exports by.epam.orders.service;
//    exports by.epam.orders.service.factory;
//    exports by.epam.orders.service.exceptions;
}