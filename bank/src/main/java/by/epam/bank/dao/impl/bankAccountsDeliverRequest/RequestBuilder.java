package by.epam.bank.dao.impl.bankAccountsDeliverRequest;

import by.epam.bank.dao.IRequestBuilder;

public class RequestBuilder implements IRequestBuilder {

    private String where = "";
    private String orderBy = "";

    public RequestBuilder() {
    }

    @Override
    public Request getBasicRequest() {

        return new Request(Request.getBaseRequest());
    }



    @Override
    public RequestBuilder withSortByOrganisationName() {
        orderBy += " o.name,";
        return this;
    }

    @Override
    public RequestBuilder withSortByBalance() {
        orderBy += " ba.balance,";
        return this;
    }

    @Override
    public RequestBuilder withSortByMinAllowedBalance() {
        orderBy += " ba.min_allowed_balance,";
        return this;
    }

    @Override
    public RequestBuilder withSortByBlocked() {
        orderBy += " ba.is_blocked,";
        return this;
    }

    /*
     *
     *call after withSortBy.*
     *
     */

    @Override
    public RequestBuilder setDESC() {
        orderBy = orderBy.substring(0, orderBy.length() - 1);
        orderBy += " DESC,";
        return this;
    }

    @Override
    public RequestBuilder showOnlyWithNegativeBalance() {
        where += " ba.balance<0,";
        return this;
    }

    @Override
    public RequestBuilder showOnlyWithPositiveBalance() {
        where += " ba.balance>=0,";
        return this;
    }

    @Override
    public RequestBuilder whereBalanceMoreThen(int number) {
        where += " ba.balance>=" + number + ",";
        return this;
    }

    @Override
    public RequestBuilder whereBalanceLessThen(int number) {
        where += " ba.balance<" + number + ",";
        return this;
    }

    @Override
    public RequestBuilder showOnlyBlocked() {
        where += " ba.is_blocked=true,";
        return this;
    }

    @Override
    public RequestBuilder showOnlyUnblocked() {
        where += " ba.is_blocked=false,";
        return this;
    }

    @Override
    public Request build() {

        String additionToRequest = "";

        if (!where.isEmpty()) {
            additionToRequest += " where" + where.substring(0, where.length() - 1);
            where = "";
        }

        if (!orderBy.isEmpty()) {
            additionToRequest += " order by" + orderBy.substring(0, orderBy.length() - 1);
            orderBy = "";
        }

        return new Request(Request.getBaseRequest() + additionToRequest);
    }


}
