package by.epam.bank.dao.impl.bankAccountsDeliverRequest;

import by.epam.bank.dao.IRequest;

public class Request implements IRequest {


    Request(String request) {
        this.request = request;
    }

    private static final String BASE_REQUEST =
            "SELECT ba.balance, ba.min_allowed_balance, ba.is_blocked, ba.info, o.id, o.name, o.role, orr.role, o.is_blocked, o.info " +
                    "FROM bank_accounts as ba join organisations as o on id=organisation_id join organisation_roles as orr on o.role=orr.id";

    private String request;

    public static String getBaseRequest() {
        return BASE_REQUEST;
    }

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
