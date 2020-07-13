package by.epam.bank.service;

import by.epam.bank.dao.IRequest;
import by.epam.bank.dao.IRequestBuilder;

public interface IRequestBuilderService extends IRequestBuilder {

    IRequest getBasicRequest();

    IRequestBuilderService withSortByOrganisationName();

    IRequestBuilderService withSortByBalance();

    IRequestBuilderService withSortByMinAllowedBalance();

    IRequestBuilderService withSortByBlocked();

    /*
     *
     *call after withSortBy.*
     *
     */
    IRequestBuilderService setDESC();

    IRequestBuilderService showOnlyWithNegativeBalance();

    IRequestBuilderService showOnlyWithPositiveBalance();

    IRequestBuilderService whereBalanceMoreThen(int number);

    IRequestBuilderService whereBalanceLessThen(int number);

    IRequestBuilderService showOnlyBlocked();


    IRequestBuilderService showOnlyUnblocked();


    IRequest build();

}
