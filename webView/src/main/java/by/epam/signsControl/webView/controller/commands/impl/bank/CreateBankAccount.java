package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateBankAccount  implements Command {

    private static final Logger logger = LogManager.getLogger(CreateBankAccount.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{



        logger.info("inside execute");



            try {

               request.setAttribute("organisations", ServiceFactory.getINSTANCE().getOrganisationsDeliver().showOrganisationsWithoutBankAccounts());

            } catch (ServiceException e) {
                logger.warn(e);
            }

        request.getRequestDispatcher("/WEB-INF/jsp/bank/add_bank_account.jsp").forward(request, response);
    }
}
