module signsControl {

    requires connectionPoolForDataBase;

    requires org.apache.logging.log4j;
    requires java.sql;

    exports by.epam.signsControl.bean;
    exports by.epam.signsControl.service;
    exports by.epam.signsControl.service.factory;
    exports by.epam.signsControl.service.exceptions;

}