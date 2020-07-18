package by.epam.signsControl.webView.controller;

import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.CommandName;
import by.epam.signsControl.webView.controller.commands.impl.*;
import by.epam.signsControl.webView.controller.commands.impl.bank.AddBankAccountFormHandler;
import by.epam.signsControl.webView.controller.commands.impl.bank.AddMoney;
import by.epam.signsControl.webView.controller.commands.impl.bank.ChangeBankAccountFormHandler;
import by.epam.signsControl.webView.controller.commands.impl.bank.CreateBankAccount;
import by.epam.signsControl.webView.controller.commands.impl.bank.ShowBankAccounts;
import by.epam.signsControl.webView.controller.commands.impl.bank.ShowTransactionsHistory;
import by.epam.signsControl.webView.controller.commands.impl.orders.*;
import by.epam.signsControl.webView.controller.commands.impl.signsControl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

class CommandProvider {

    private CommandProvider() {

        commands.put(CommandName.MAIN_PAGE, new MainPage());
        commands.put(CommandName.LOGIN, new Login());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.WRONG_COMMAND, new WrongCommand());
        commands.put(CommandName.LOGIN_FORM, new LoginFormHandler());

        commands.put(CommandName.GET_CURRENT_POINTS, new GetCurrentPoints());
        commands.put(CommandName.GET_POINTS_BY_DATE, new GetPointsByDate());
        commands.put(CommandName.GET_POINT_HISTORY, new GetPointHistory());
        commands.put(CommandName.GET_EMPTY_POINTS, new GetEmptyPoints());
        commands.put(CommandName.GET_SIGN_ADD_INFO, new GetSignAddInfo());
        commands.put(CommandName.GET_UNUSED_DIRECTIONS, new GetUnusedDirections());
        commands.put(CommandName.GET_DIRECTION_CHANGE_INFO, new GetDirectionChangeInfo());
        commands.put(CommandName.GET_DIRECTION_ADDRESS_ANNOTATION, new GetDirectionAddressAnnotation());
        commands.put(CommandName.GET_SIGN_CHANGE_INFO, new GetSignsChangeInfo());

        commands.put(CommandName.ADD_MAP_POINT, new AddMapPoint());
        commands.put(CommandName.ADD_SIGN, new AddSign());
        commands.put(CommandName.CHANGE_DELETE_DIRECTION, new ChangeDeleteDirection());
        commands.put(CommandName.CHANGE_DELETE_LOCAL_SIGN, new ChangeDeleteLocalSign());
        commands.put(CommandName.PDD_SIGNS, new PDDSigns());
        commands.put(CommandName.STANDARD_SIZES, new StandardSizes());

        commands.put(CommandName.ADD_STANDARD_SIZE, new AddStandardSize());
        commands.put(CommandName.CHANGE_STANDARD_SIZE, new ChangeStandardSize());
        commands.put(CommandName.REMOVE_STANDARD_SIZE, new RemoveStandardSize());
        commands.put(CommandName.ADD_STANDARD_SIZE_FORM, new AddStandardSizeFormHandler());
        commands.put(CommandName.CHANGE_STANDARD_SIZE_FORM, new ChangeStandardSizeFormHandler());

        commands.put(CommandName.REMOVE_PDD_SIGN, new RemovePDDSign());
        commands.put(CommandName.ADD_PDD_SIGN, new AddPDDSign());
        commands.put(CommandName.ADD_PDD_SIGN_FORM, new AddPDDSignFormHandler());
        commands.put(CommandName.CHANGE_PDD_SIGN, new ChangePDDSign());
        commands.put(CommandName.SET_SIGN_IMAGE, new SetSignImage());

        //bank
        commands.put(CommandName.BANK_ACCOUNTS, new ShowBankAccounts());
        commands.put(CommandName.ADD_BANK_ACCOUNT, new CreateBankAccount());
        commands.put(CommandName.ADD_BANK_ACCOUNT_FORM, new AddBankAccountFormHandler());
        commands.put(CommandName.CHANGE_BANK_ACCOUNT_FORM, new ChangeBankAccountFormHandler());

        commands.put(CommandName.SHOW_TRANSACTIONS_HISTORY, new ShowTransactionsHistory());
        commands.put(CommandName.ADD_MONEY, new AddMoney());

        //orders
        commands.put(CommandName.SHOW_ORDERS, new GetOrders());
        commands.put(CommandName.ORDERS, new GetOrdersJSP());
        commands.put(CommandName.ADD_ORDER, new AddOrderFormHandler());
        commands.put(CommandName.GET_ORDERS_CHANGE_INFO, new GetOrdersChangeInfo());
        commands.put(CommandName.CHANGE_DELETE_ORDER, new ChangeDeleteOrder());
        commands.put(CommandName.REMOVE_TYPE_OF_WORK, new RemoveTypeOfWork());
        commands.put(CommandName.ADD_TYPE_OF_WORK, new AddTypeOfWork());
        commands.put(CommandName.TYPES_OF_WORK, new GetTypesOfWork());
        commands.put(CommandName.CHANGE_TYPE_OF_WORK, new ChangeTypeOfWork());

    }

    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private final static CommandProvider commandProvider = new CommandProvider();

    private final Map<CommandName, Command> commands = new HashMap<>();


    static CommandProvider getCommandProvider() {
        return commandProvider;
    }

    Command getCommand(String commandName) {


        try {
            return commands.get(CommandName.valueOf(commandName.toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            logger.warn("wrong command: " + commandName);
            return commands.get(CommandName.WRONG_COMMAND);
        }
    }

}
