package by.epam.bank.dao;

public interface IRequestBuilder {

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
