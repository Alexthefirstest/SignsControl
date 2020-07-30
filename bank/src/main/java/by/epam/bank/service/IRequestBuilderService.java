package by.epam.bank.service;

import by.epam.bank.dao.IRequest;
import by.epam.bank.dao.IRequestBuilder;

/**
 * builder for create a {@link IRequest}
 * <p>
 * need to follow sql request rules for methods calling sequence: {@link IRequestBuilder#setDESC()} only after order methods calling
 */
public interface IRequestBuilderService extends IRequestBuilder {

    /**
     * @return {@link IRequest} request with no where and order parameters
     */
    IRequest getBasicRequest();

    /**
     * add sort by organisation name
     *
     * @return this
     */
    IRequestBuilderService withSortByOrganisationName();

    /**
     * add sort by balance
     *
     * @return this
     */
    IRequestBuilderService withSortByBalance();

    /**
     * add sort by minimum allowed balance
     *
     * @return this
     */
    IRequestBuilderService withSortByMinAllowedBalance();

    /**
     * add sort by block
     *
     * @return this
     */
    IRequestBuilderService withSortByBlocked();

    /**
     * set DESC to sort
     * <p>
     * call after withSortBy.*
     *
     * @return this
     */
    IRequestBuilderService setDESC();

    /**
     * show only with negative balance: <0
     *
     * @return this
     */
    IRequestBuilderService showOnlyWithNegativeBalance();

    /**
     * show only with positive balance: >=0
     *
     * @return this
     */
    IRequestBuilderService showOnlyWithPositiveBalance();

    /**
     * show only with balance more >= then number param
     *
     * @param number value to search
     * @return this
     */
    IRequestBuilderService whereBalanceMoreThen(int number);

    /**
     * show only with balance less < then number param
     *
     * @param number value to search
     * @return this
     */
    IRequestBuilderService whereBalanceLessThen(int number);

    /**
     * show only block accounts
     *
     * @return this
     */
    IRequestBuilderService showOnlyBlocked();

    /**
     * show only unblock accounts
     *
     * @return this
     */
    IRequestBuilderService showOnlyUnblocked();


    /**
     * @return ready to execution {@link IRequest}
     * with field request
     * consist of base request and parameters added during build
     * <p>
     * clean parameters to another using
     */
    IRequest build();

}
