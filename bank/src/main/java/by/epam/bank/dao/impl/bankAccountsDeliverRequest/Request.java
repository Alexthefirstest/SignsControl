package by.epam.bank.dao.impl.bankAccountsDeliverRequest;

import by.epam.bank.dao.IRequest;

/**
 * request for sql bank account base instance
 */
public class Request implements IRequest {


    /**
     * base request to get a correct data from data base with no when or order fields
     */
    private static final String BASE_REQUEST =
            "SELECT ba.balance, ba.min_allowed_balance, ba.is_blocked, ba.info, o.id, o.name, o.role, orr.role, o.is_blocked, o.info " +
                    "FROM bank_accounts as ba join organisations as o on id=organisation_id join organisation_roles as orr on o.role=orr.id";

    /**
     * request to execute it in {@link by.epam.bank.dao.IBankAccountsDeliver#executeRequest(IRequest)}
     */
    private String request;

    /**
     * constructor
     *
     * @param request set {@link Request#request}
     */
    Request(String request) {
        this.request = request;
    }

    /**
     * @return {@link Request#BASE_REQUEST}
     */
    public static String getBaseRequest() {
        return BASE_REQUEST;
    }

    /**
     * @return {@link Request#request} that will be executed
     */
    @Override
    public String getRequest() {
        return request;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("request='").append(request).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
