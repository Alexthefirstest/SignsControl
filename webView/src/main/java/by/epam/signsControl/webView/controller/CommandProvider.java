package by.epam.signsControl.webView.controller;

import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.CommandName;
import by.epam.signsControl.webView.controller.commands.impl.Login;
import by.epam.signsControl.webView.controller.commands.impl.LoginFormHandler;
import by.epam.signsControl.webView.controller.commands.impl.Logout;
import by.epam.signsControl.webView.controller.commands.impl.MainPage;
import by.epam.signsControl.webView.controller.commands.impl.SetLocale;
import by.epam.signsControl.webView.controller.commands.impl.WrongCommand;
import by.epam.signsControl.webView.controller.commands.impl.bank.AddBankAccountFormHandler;
import by.epam.signsControl.webView.controller.commands.impl.bank.AddMoney;
import by.epam.signsControl.webView.controller.commands.impl.bank.ChangeBankAccountFormHandler;
import by.epam.signsControl.webView.controller.commands.impl.bank.CreateBankAccount;
import by.epam.signsControl.webView.controller.commands.impl.bank.ExecuteTransaction;
import by.epam.signsControl.webView.controller.commands.impl.bank.OrganisationProfile;
import by.epam.signsControl.webView.controller.commands.impl.bank.ShowBankAccounts;
import by.epam.signsControl.webView.controller.commands.impl.bank.ShowTransactionsHistory;
import by.epam.signsControl.webView.controller.commands.impl.orders.*;
import by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl.*;
import by.epam.signsControl.webView.controller.commands.impl.signsControl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * provider of {@link Command} instances
 *
 */
class CommandProvider {

    /*
     *logger
     */
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    /**
     * this class instance
     */
    private final static CommandProvider commandProvider = new CommandProvider();

    /**
     * store for commands by {@link CommandName} key
     */
    private final Map<CommandName, Command> commands = new HashMap<>();

    private CommandProvider() {

        commands.put(CommandName.MAIN_PAGE, new MainPage());
        commands.put(CommandName.LOGIN, new Login());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.WRONG_COMMAND, new WrongCommand());
        commands.put(CommandName.LOGIN_FORM, new LoginFormHandler());
        commands.put(CommandName.SET_LOCALE, new SetLocale());

        //signs control
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
        commands.put(CommandName.EXECUTE_TRANSACTION, new ExecuteTransaction());
        commands.put(CommandName.ORGANISATION_PROFILE, new OrganisationProfile());

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
        commands.put(CommandName.PAY_ORDER, new PayOrder());

        commands.put(CommandName.WORKERS_CREWS, new WorkersCrews());
        commands.put(CommandName.ADD_WORKERS_CREW, new AddWorkersCrew());
        commands.put(CommandName.CHANGE_WORKER_CREW, new ChangeWorkersCrew());
        commands.put(CommandName.ADD_WORKER_TO_CREW, new AddWorkerToCrew());

        //usersOrganisations
        commands.put(CommandName.ADD_ORGANISATION_FORM_HANDLER, new AddOrganisationFormHandler());
        commands.put(CommandName.ADD_USER, new AddUser());
        commands.put(CommandName.ADD_USER_FORM_HANDLER, new AddUserFormHandler());
        commands.put(CommandName.CHANGE_LOGIN_PASSWORD, new ChangeLoginPasswordFormHandler());
        commands.put(CommandName.CHANGE_ORGANISATION_FORM_HANDLER, new ChangeOrganisationFormHandler());
        commands.put(CommandName.CHANGE_USER_FORM_HANDLER, new ChangeUserFormHandler());
        commands.put(CommandName.ORGANISATIONS, new OrganisationsList());
        commands.put(CommandName.USERS, new UsersList());
        commands.put(CommandName.USER_PROFILE, new UserProfile());

    }

    /**
     * @return {@link CommandProvider#commandProvider}
     */
   public static CommandProvider getCommandProvider() {
        return commandProvider;
    }

    /**
     * return command by commandName param, if can't find command - return WRONG_COMMAND command
     *
     * @param commandName name to find command, upper or lower case doesn't matter
     * @return command by name or WRONG_COMMAND if catch exception or can't find command
     */
  public   Command getCommand(String commandName) {


        try {
            return commands.get(CommandName.valueOf(commandName.toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            logger.warn("wrong command: " + commandName);
            return commands.get(CommandName.WRONG_COMMAND);
        }
    }

}
