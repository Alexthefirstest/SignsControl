package by.epam.signsControl.webView.controller;

import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.CommandName;
import by.epam.signsControl.webView.controller.commands.impl.Login;
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

    }

    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private static CommandProvider commandProvider = new CommandProvider();

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
