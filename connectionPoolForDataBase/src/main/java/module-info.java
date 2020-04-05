
module connectionPoolForDataBase {

    requires java.sql;
    requires org.apache.logging.log4j;


    exports by.epam.connectionPoolForDataBase.connectionPool;
    exports by.epam.connectionPoolForDataBase.connectionPool.factory;
}