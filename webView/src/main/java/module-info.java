module webView {

   requires connectionPoolForDataBase;
   requires signsControl;
   requires rolesOrganisationsUsersController;

    requires org.apache.logging.log4j;
    requires java.sql;
    requires javax.servlet.api;

}