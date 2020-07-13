package by.epam.bank.service.impl;

import by.epam.bank.dao.IRequest;
import by.epam.bank.dao.IRequestBuilder;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IRequestBuilderService;


public class RequestBuilderService implements IRequestBuilderService {

    private static IRequestBuilder daoRequestBuilder = DaoFactory.getINSTANCE().getRequestBuilder();

    @Override
    public IRequest getBasicRequest() {
        return daoRequestBuilder.getBasicRequest();
    }

    @Override
    public IRequestBuilderService withSortByOrganisationName() {
        daoRequestBuilder.withSortByOrganisationName();
        return this;
    }

    @Override
    public IRequestBuilderService withSortByBalance() {
        daoRequestBuilder.withSortByBalance();
        return this;
    }

    @Override
    public IRequestBuilderService withSortByMinAllowedBalance() {
        daoRequestBuilder.withSortByMinAllowedBalance();
        return this;
    }

    @Override
    public IRequestBuilderService withSortByBlocked() {
        daoRequestBuilder.withSortByBlocked();
        return this;
    }

    @Override
    public IRequestBuilderService setDESC() {
        daoRequestBuilder.setDESC();
        return this;
    }

    @Override
    public IRequestBuilderService showOnlyWithNegativeBalance() {
        daoRequestBuilder.showOnlyWithNegativeBalance();
        return this;
    }

    @Override
    public IRequestBuilderService showOnlyWithPositiveBalance() {
        daoRequestBuilder.showOnlyWithPositiveBalance();
        return this;
    }

    @Override
    public IRequestBuilderService whereBalanceMoreThen(int number) {
        daoRequestBuilder.whereBalanceMoreThen(number);
        return this;
    }

    @Override
    public IRequestBuilderService whereBalanceLessThen(int number) {
        daoRequestBuilder.whereBalanceLessThen(number);
        return this;
    }

    @Override
    public IRequestBuilderService showOnlyBlocked() {
        daoRequestBuilder.showOnlyBlocked();
        return this;
    }

    @Override
    public IRequestBuilderService showOnlyUnblocked() {
        daoRequestBuilder.showOnlyUnblocked();
        return this;
    }

    @Override
    public IRequest build() {
        return daoRequestBuilder.build();
    }
}
