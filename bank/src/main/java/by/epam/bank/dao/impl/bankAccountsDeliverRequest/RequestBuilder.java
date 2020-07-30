package by.epam.bank.dao.impl.bankAccountsDeliverRequest;

import by.epam.bank.dao.IRequestBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * builder for create a {@link Request}
 * use {@link Request#getBaseRequest()} like a core and add when/order parameters to it
 * <p>
 * need to follow sql request rules for methods calling sequence: {@link RequestBuilder#setDESC()} only after order methods calling
 */
public class RequestBuilder implements IRequestBuilder {

    /**
     * logger
     */
    private static Logger logger = LogManager.getLogger(RequestBuilder.class);

    /**
     * where sql part field
     */
    private String where = "";

    /**
     * order by sql part empty field
     */
    private String orderBy = "";

    /**
     * empty constructor
     */
    public RequestBuilder() {
    }

    /**
     * @return {@link Request} request with no where and order parameters
     */
    @Override
    public Request getBasicRequest() {

        return new Request(Request.getBaseRequest());
    }


    /**
     * add sort by organisation name
     *
     * @return this
     */
    @Override
    public RequestBuilder withSortByOrganisationName() {
        orderBy += " o.name,";
        return this;
    }

    /**
     * add sort by balance
     *
     * @return this
     */
    @Override
    public RequestBuilder withSortByBalance() {
        orderBy += " ba.balance,";
        return this;
    }

    /**
     * add sort by minimum allowed balance
     *
     * @return this
     */
    @Override
    public RequestBuilder withSortByMinAllowedBalance() {
        orderBy += " ba.min_allowed_balance,";
        return this;
    }

    /**
     * add sort by block
     *
     * @return this
     */
    @Override
    public RequestBuilder withSortByBlocked() {
        orderBy += " ba.is_blocked,";
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
    public RequestBuilder setDESC() {
        orderBy = orderBy.substring(0, orderBy.length() - 1);
        orderBy += " DESC,";
        return this;
    }

    /**
     * show only with negative balance: <0
     *
     * @return this
     */
    @Override
    public RequestBuilder showOnlyWithNegativeBalance() {
        where += " ba.balance<0 AND";
        return this;
    }

    /**
     * show only with positive balance: >=0
     *
     * @return this
     */
    @Override
    public RequestBuilder showOnlyWithPositiveBalance() {
        where += " ba.balance>=0 AND";
        return this;
    }

    /**
     * show only with balance more >= then number param
     *
     * @param number value to search
     * @return this
     */
    @Override
    public RequestBuilder whereBalanceMoreThen(int number) {
        where += " ba.balance>=" + number + " AND";
        return this;
    }

    /**
     * show only with balance less < then number param
     *
     * @param number value to search
     * @return this
     */
    @Override
    public RequestBuilder whereBalanceLessThen(int number) {
        where += " ba.balance<" + number + " AND";
        return this;
    }

    /**
     * show only block accounts
     *
     * @return this
     */
    @Override
    public RequestBuilder showOnlyBlocked() {
        where += " ba.is_blocked=true AND";
        return this;
    }

    /**
     * show only unblock accounts
     *
     * @return this
     */
    @Override
    public RequestBuilder showOnlyUnblocked() {
        where += " ba.is_blocked=false AND";
        return this;
    }

    /**
     * @return ready to execution {@link Request}
     * with field request
     * consist of {@link Request#getBaseRequest()} and parameters added during build
     * <p>
     * available to another using:
     * make {@link RequestBuilder#where} empty
     * make {@link RequestBuilder#orderBy} empty
     */
    @Override
    public Request build() {

        String additionToRequest = "";

        if (!where.isEmpty()) {
            additionToRequest += " where" + where.substring(0, where.length() - 3);
            where = "";
        }

        if (!orderBy.isEmpty()) {
            additionToRequest += " order by" + orderBy.substring(0, orderBy.length() - 1);
            orderBy = "";
        }

        Request finalRequest = new Request(Request.getBaseRequest() + additionToRequest);

        logger.info(finalRequest);

        return finalRequest;
    }


}
