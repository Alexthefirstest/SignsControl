package by.epam.bank.service.impl;

import by.epam.bank.dao.IRequest;
import by.epam.bank.dao.IRequestBuilder;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IRequestBuilderService;


/**
 * builder for create a {@link IRequest}
 * <p>
 * need to follow sql request rules for methods calling sequence: {@link IRequestBuilder#setDESC()} only after order methods calling
 */
public class RequestBuilderService implements IRequestBuilderService {

    /**
     * {@link IRequestBuilder} instance
     */
    private static IRequestBuilder daoRequestBuilder = DaoFactory.getINSTANCE().getRequestBuilder();

    /**
     * @return {@link IRequest} request with no where and order parameters
     */
    @Override
    public IRequest getBasicRequest() {
        return daoRequestBuilder.getBasicRequest();
    }

    /**
     * add sort by organisation name
     *
     * @return this
     */
    @Override
    public IRequestBuilderService withSortByOrganisationName() {
        daoRequestBuilder.withSortByOrganisationName();
        return this;
    }

    /**
     * add sort by balance
     *
     * @return this
     */
    @Override
    public IRequestBuilderService withSortByBalance() {
        daoRequestBuilder.withSortByBalance();
        return this;
    }

    /**
     * add sort by minimum allowed balance
     *
     * @return this
     */
    @Override
    public IRequestBuilderService withSortByMinAllowedBalance() {
        daoRequestBuilder.withSortByMinAllowedBalance();
        return this;
    }

    /**
     * add sort by block
     *
     * @return this
     */
    @Override
    public IRequestBuilderService withSortByBlocked() {
        daoRequestBuilder.withSortByBlocked();
        return this;
    }

    /**
     * set DESC to sort
     * <p>
     * call after withSortBy.*
     *
     * @return this
     */
    @Override
    public IRequestBuilderService setDESC() {
        daoRequestBuilder.setDESC();
        return this;
    }

    /**
     * show only with negative balance: <0
     *
     * @return this
     */
    @Override
    public IRequestBuilderService showOnlyWithNegativeBalance() {
        daoRequestBuilder.showOnlyWithNegativeBalance();
        return this;
    }

    /**
     * show only with positive balance: >=0
     *
     * @return this
     */
    @Override
    public IRequestBuilderService showOnlyWithPositiveBalance() {
        daoRequestBuilder.showOnlyWithPositiveBalance();
        return this;
    }

    /**
     * show only with balance more >= then number param
     *
     * @param number value to search
     * @return this
     */
    @Override
    public IRequestBuilderService whereBalanceMoreThen(int number) {
        daoRequestBuilder.whereBalanceMoreThen(number);
        return this;
    }

    /**
     * show only with balance less < then number param
     *
     * @param number value to search
     * @return this
     */
    @Override
    public IRequestBuilderService whereBalanceLessThen(int number) {
        daoRequestBuilder.whereBalanceLessThen(number);
        return this;
    }

    /**
     * show only block accounts
     *
     * @return this
     */
    @Override
    public IRequestBuilderService showOnlyBlocked() {
        daoRequestBuilder.showOnlyBlocked();
        return this;
    }

    /**
     * show only unblock accounts
     *
     * @return this
     */
    @Override
    public IRequestBuilderService showOnlyUnblocked() {
        daoRequestBuilder.showOnlyUnblocked();
        return this;
    }

    /**
     * find by id
     *
     * @param id id to find
     * @return this
     */
    @Override
    public IRequestBuilderService findById(int id) {
        daoRequestBuilder.findById(id);
        return this;
    }

    /**
     * except bank account with id from list
     *
     * @param id
     * @return this
     */
    @Override
    public IRequestBuilderService withoutID(int id) {
        daoRequestBuilder.withoutID(id);
        return this;
    }

    /**
     * @return ready to execution {@link IRequest}
     * with field request
     * consist of base request and parameters added during build
     * <p>
     * clean parameters to another using
     */
    @Override
    public IRequest build() {
        return daoRequestBuilder.build();
    }
}
