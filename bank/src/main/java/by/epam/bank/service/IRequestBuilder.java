package by.epam.bank.service;

import by.epam.bank.dao.IRequest;

public interface IRequestBuilder extends by.epam.bank.dao.IRequestBuilder {

    IRequest getBasicRequest();

    IRequestBuilder withSortByOrganisationName();

    IRequestBuilder withSortByBalance();

    IRequestBuilder withSortByMinAllowedBalance();

    IRequestBuilder withSortByBlocked();

    /*
     *
     *call after withSortBy.*
     *
     */
    IRequestBuilder setDESC();

    IRequestBuilder showOnlyWithNegativeBalance();

    IRequestBuilder showOnlyWithPositiveBalance();

    IRequestBuilder whereBalanceMoreThen(int number);

    IRequestBuilder whereBalanceLessThen(int number);

    IRequestBuilder showOnlyBlocked();


    IRequestBuilder showOnlyUnblocked();


    IRequest build();

}
