package by.epam.bank.dao;

/**
 * builder for {@link IRequest}
 */
public interface IRequestBuilder {

    /**
     * @return {@link IRequest} request with no where and order parameters
     */
    IRequest getBasicRequest();

    /**
     * add sort by organisation name
     *
     * @return this
     */
    IRequestBuilder withSortByOrganisationName();

    /**
     * add sort by balance
     *
     * @return this
     */
    IRequestBuilder withSortByBalance();

    /**
     * add sort by minimum allowed balance
     *
     * @return this
     */
    IRequestBuilder withSortByMinAllowedBalance();

    /**
     * add sort by block
     *
     * @return this
     */
    IRequestBuilder withSortByBlocked();


    /**
     * set DESC to sort
     * <p>
     *
     * @return this
     */
    IRequestBuilder setDESC();

    /**
     * show only with negative balance: <0
     *
     * @return this
     */
    IRequestBuilder showOnlyWithNegativeBalance();

    /**
     * show only with positive balance: >=0
     *
     * @return this
     */
    IRequestBuilder showOnlyWithPositiveBalance();

    /**
     * show only with balance more >= then number param
     *
     * @param number value to search
     * @return this
     */
    IRequestBuilder whereBalanceMoreThen(int number);

    /**
     * show only with balance less < then number param
     *
     * @param number value to search
     * @return this
     */
    IRequestBuilder whereBalanceLessThen(int number);

    /**
     * show only block accounts
     *
     * @return this
     */
    IRequestBuilder showOnlyBlocked();


    /**
     * show only unblock accounts
     *
     * @return this
     */
    IRequestBuilder showOnlyUnblocked();

    /**
     * find by id
     *
     * @return this
     */
    IRequestBuilder findById(int id) ;


    /**
     * @return ready to execution {@link IRequest} with build result
     */
    IRequest build();

}
