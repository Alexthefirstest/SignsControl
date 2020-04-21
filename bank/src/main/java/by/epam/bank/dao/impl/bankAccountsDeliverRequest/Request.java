package by.epam.bank.dao.impl.bankAccountsDeliverRequest;

import by.epam.bank.dao.IRequest;

public class Request implements IRequest {


    Request(String request) {
        this.request = request;
    }

    private static final String BASE_REQUEST = "SELECT name, organisation_id, balance, min_allowed_balance, ba.is_blocked, ba.info " +
            "FROM bank_accounts as ba join organisations on id=organisation_id";

    private String request;

    public static String getBaseRequest() {
        return BASE_REQUEST;
    }

    @Override
    public String getRequest() {
        return request;
    }

}
