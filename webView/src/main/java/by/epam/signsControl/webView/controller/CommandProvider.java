package by.epam.signsControl.webView.controller;

import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.CommandName;
import by.epam.signsControl.webView.controller.commands.impl.GetPointHistory;
import by.epam.signsControl.webView.controller.commands.impl.GetPointsByDate;
import by.epam.signsControl.webView.controller.commands.impl.GetCurrentPoints;
import by.epam.signsControl.webView.controller.commands.impl.Login;
import by.epam.signsControl.webView.controller.commands.impl.LoginFormHandler;
import by.epam.signsControl.webView.controller.commands.impl.Logout;
import by.epam.signsControl.webView.controller.commands.impl.MainPage;
import by.epam.signsControl.webView.controller.commands.impl.WrongCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

class CommandProvider {

    private CommandProvider() {

        commands.put(CommandName.MAIN_PAGE, new MainPage());
        commands.put(CommandName.LOGIN, new Login());
        commands.put(CommandName.WRONG_COMMAND, new WrongCommand());
        commands.put(CommandName.LOGIN_FORM, new LoginFormHandler());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.GET_CURRENT_POINTS, new GetCurrentPoints());
        commands.put(CommandName.GET_POINTS_BY_DATE, new GetPointsByDate());
        commands.put(CommandName.GET_POINT_HISTORY, new GetPointHistory());

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
