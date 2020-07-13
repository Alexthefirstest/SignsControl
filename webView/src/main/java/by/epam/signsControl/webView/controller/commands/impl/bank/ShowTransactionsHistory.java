package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.bean.Transaction;
import by.epam.bank.service.ITransactionsDeliverService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowTransactionsHistory implements Command {

    private static final Logger logger = LogManager.getLogger(ShowTransactionsHistory.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");


        try {

            ITransactionsDeliverService transactionsDeliver = ServiceFactory.getINSTANCE().getTransactionsDeliver();

            String findBy = request.getParameter("findBy");

            logger.info(findBy);

            Transaction[] transactions;

            if (findBy != null) {

                switch (findBy) {
                    case "OrgId":
                        int from = Integer.parseInt(request.getParameter("accountFrom"));
                        int to = Integer.parseInt(request.getParameter("accountTo"));

                        if (to < 0) {
                            transactions = transactionsDeliver.findTransactionsByFrom(from);
                        } else {
                            transactions = transactionsDeliver.findTransactionsByFromAndTo(from, to);
                        }
                        break;

                    case "date":
                        int fromWithDate = Integer.parseInt(request.getParameter("accountFromDate"));
                        String dateFrom = request.getParameter("dateFrom").replaceAll("-", ".");
                        String dateTo = request.getParameter("dateTo").replaceAll("-", ".");
                        logger.info("dateTo: "+dateTo);
                        transactions = transactionsDeliver.findTransactionsByDate(fromWithDate, dateFrom, dateTo);
                        break;
                    default:
                        transactions = transactionsDeliver.getTransactions();
                        break;
                }

            } else {
                transactions = transactionsDeliver.getTransactions();
            }

            request.setAttribute("transactions", transactions);

            request.setAttribute("bank_accounts", ServiceFactory.getINSTANCE().getBankAccountsDeliver().executeRequest(
                    ServiceFactory.getINSTANCE().getRequestBuilder().withSortByOrganisationName().build()
            ));

        } catch (ServiceException e) {
            logger.warn(e);
        }


        request.getRequestDispatcher("/WEB-INF/jsp/bank/transactions.jsp").forward(request, response);
    }

}

