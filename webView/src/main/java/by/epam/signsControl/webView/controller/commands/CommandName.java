package by.epam.signsControl.webView.controller.commands;

public enum CommandName {

    LOGIN,
    WRONG_COMMAND,
    MAIN_PAGE,
    LOGIN_FORM,
    LOGOUT,

    GET_CURRENT_POINTS,
    GET_POINTS_BY_DATE,
    GET_POINT_HISTORY,
    ADD_MAP_POINT,
    GET_EMPTY_POINTS,
    GET_SIGN_ADD_INFO,
    GET_UNUSED_DIRECTIONS,
    ADD_SIGN,
    GET_DIRECTION_CHANGE_INFO,
    GET_DIRECTION_ADDRESS_ANNOTATION,
    CHANGE_DELETE_DIRECTION,
    GET_SIGN_CHANGE_INFO,
    CHANGE_DELETE_LOCAL_SIGN,

    PDD_SIGNS,
    STANDARD_SIZES,

    ADD_STANDARD_SIZE,
    CHANGE_STANDARD_SIZE,
    REMOVE_STANDARD_SIZE,
    ADD_STANDARD_SIZE_FORM,
    CHANGE_STANDARD_SIZE_FORM,

    ADD_PDD_SIGN,
    CHANGE_PDD_SIGN,
    REMOVE_PDD_SIGN,
    ADD_PDD_SIGN_FORM,
    SET_SIGN_IMAGE,

    //bank
    BANK_ACCOUNTS,
    ADD_BANK_ACCOUNT,
    ADD_BANK_ACCOUNT_FORM,
    CHANGE_BANK_ACCOUNT_FORM,
    ADD_MONEY,
    SHOW_TRANSACTIONS_HISTORY,

    //orders
    SHOW_ORDERS,
    ORDERS,
    ADD_ORDER,
    GET_ORDERS_CHANGE_INFO,
    CHANGE_DELETE_ORDER


}
