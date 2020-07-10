package by.epam.bank.service.impl;

import by.epam.bank.dao.IRequest;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IRequestBuilder;

public class RequestBuilder implements IRequestBuilder {

    by.epam.bank.dao.IRequestBuilder requestBuilder= DaoFactory.getINSTANCE().getRequestBuilder();

    @Override
    public IRequest getBasicRequest() {
        return requestBuilder.getBasicRequest();
    }

    @Override
    public IRequestBuilder withSortByOrganisationName() {
        return null;
//        return requestBuilder.withSortByOrganisationName();
    }

    @Override
    public IRequestBuilder withSortByBalance() {
//        return requestBuilder.withSortByBalance();
        return null;
    }

    @Override
    public IRequestBuilder withSortByMinAllowedBalance() {
        return null;
    }

    @Override
    public IRequestBuilder withSortByBlocked() {
        return null;
    }

    @Override
    public IRequestBuilder setDESC() {
        return null;
    }

    @Override
    public IRequestBuilder showOnlyWithNegativeBalance() {
        return null;
    }

    @Override
    public IRequestBuilder showOnlyWithPositiveBalance() {
        return null;
    }

    @Override
    public IRequestBuilder whereBalanceMoreThen(int number) {
        return null;
    }

    @Override
    public IRequestBuilder whereBalanceLessThen(int number) {
        return null;
    }

    @Override
    public IRequestBuilder showOnlyBlocked() {
        return null;
    }

    @Override
    public IRequestBuilder showOnlyUnblocked() {
        return null;
    }

    @Override
    public IRequest build() {
        return null;
    }
}
