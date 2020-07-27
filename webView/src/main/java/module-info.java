module webView {

    requires connectionPoolForDataBase;
    requires signsControl;
    requires rolesOrganisationsUsersController;
    requires bank;
    requires orders;

    requires org.apache.logging.log4j;
    requires java.sql;
    requires javax.servlet.api;
    requires com.google.gson;
    requires javax.servlet.jsp.api;

}