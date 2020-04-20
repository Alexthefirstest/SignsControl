module bank {

    requires connectionPoolForDataBase;

    requires org.apache.logging.log4j;
    requires java.sql;

    exports by.epam.bank.bean;
    exports by.epam.bank.dao;
    exports by.epam.bank.dao.factory;
    exports by.epam.bank.dao.exceptions;

}