package by.epam.signsControl.webView;


public final class Constants {

    private static final Constants INSTANCE = new Constants();

    private Constants() {

    }

    public static final String MAIN_PAGE = "main_page";

    public static final String USER_ID = "userID";
    public static final String USER_ROLE = "userRole";
    public static final String USERNAME = "username";
    public static final String ORGANISATION_ID = "organisationID";
    public static final String ORGANISATION_ROLE = "organisationRole";


    public static final String SERVLET_PATH = "/app";
    public static final String REQUIRED_URI = "uri";

    public static final int USER_ANONYM_ROLE = 1;
    public static final int ADMINISTRATOR_ROLE = 2;
    public static final int CUSTOMER_ROLE = 3;

    public static final int ODD_ORGANISATION_ROLE = 1;
    public static final int PERFORMERS_ORGANISATIONS_ROLE = 3;
    public static final int BANK_ORGANISATION_ROLE = 4;
    public static final int ADMINISTRATOR_ORGANISATION_ROLE = 5;


    public static final int BANK_ID = 3;
    public static final int ADMINISTRATORS_ORGANISATION_ID = 4;


    public static final int COUNT_TRANSACTIONS_ON_PAGE = 20;
    public static final int ADDITIONAL_TRANSACTIONS_PAGES_START = 2;
    public static final int ADDITIONA_TRANSACTIONSL_PAGES_FINISH = 2;

}
