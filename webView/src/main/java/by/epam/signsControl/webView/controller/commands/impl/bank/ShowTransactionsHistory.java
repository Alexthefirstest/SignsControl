package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.bean.Transaction;
import by.epam.bank.service.ITransactionsDeliverService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.RequestParser;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ShowTransactionsHistory implements Command {

    private static final Logger logger = LogManager.getLogger(ShowTransactionsHistory.class);

    private static final int COUNT_ON_PAGE = 20;
    private static final int ADDITIONAL_PAGES_START = 2;
    private static final int ADDITIONAL_PAGES_FINISH = 2;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        int pageCount;

        try {

            ITransactionsDeliverService transactionsDeliver = ServiceFactory.getINSTANCE().getTransactionsDeliver();

            int page;
            try {
                page = Integer.parseInt(RequestParser.getSecondCommandFromURI(request));
            } catch (NullPointerException | NumberFormatException ex) {
                page = 1;
            }

            String findBy = request.getParameter("findBy");

            logger.info(findBy);

            Transaction[] transactions;

            if (findBy != null) {

                switch (findBy) {
                    case "OrgId":
                        int from = Integer.parseInt(request.getParameter("accountFrom"));
                        int to = Integer.parseInt(request.getParameter("accountTo"));

                        if (to < 0) {
                            transactions = transactionsDeliver.findTransactionsByFromPage(from, COUNT_ON_PAGE, page);
                            pageCount = transactionsDeliver.getPagesQuantityByFrom(from, COUNT_ON_PAGE);
                        } else {
                            transactions = transactionsDeliver.findTransactionsByFromAndToPage(from, to, COUNT_ON_PAGE, page);
                            pageCount = transactionsDeliver.getPagesQuantityByFromAndTo(from, to, COUNT_ON_PAGE);
                        }
                        break;

                    case "date":
                        int fromWithDate = Integer.parseInt(request.getParameter("accountFromDate"));
                        String dateFrom = request.getParameter("dateFrom").replaceAll("-", ".");
                        String dateTo = request.getParameter("dateTo").replaceAll("-", ".");
                        logger.info("dateTo: " + dateTo);
                        transactions = transactionsDeliver.findTransactionsByDatePage(fromWithDate, dateFrom, dateTo, COUNT_ON_PAGE, page);
                        pageCount = transactionsDeliver.getPagesQuantityByDate(fromWithDate, dateFrom, dateTo, COUNT_ON_PAGE);
                        break;
                    default:
                        transactions = transactionsDeliver.getTransactionsPage(COUNT_ON_PAGE, page);
                        pageCount = transactionsDeliver.getPagesQuantity(COUNT_ON_PAGE);
                        break;
                }

            } else {
                transactions = transactionsDeliver.getTransactionsPage(COUNT_ON_PAGE, page);
                pageCount = transactionsDeliver.getPagesQuantity(COUNT_ON_PAGE);
            }

            ArrayList<Integer> startPages = getStartPages(page);
            ArrayList<Integer> finishPages = getFinishPages(page, pageCount);

            logger.info(page);
            logger.info(startPages);
            logger.info(finishPages);
            logger.info(getStartPage(startPages));
            logger.info(getFinishPage(finishPages, pageCount));


            request.setAttribute("currentPage", page);
            request.setAttribute("startPages", startPages);
            request.setAttribute("finishPages", finishPages);
            request.setAttribute("startPage", getStartPage(startPages));
            request.setAttribute("finishPage", getFinishPage(finishPages, pageCount));

            request.setAttribute("transactions", transactions);

            request.setAttribute("bank_accounts", ServiceFactory.getINSTANCE().getBankAccountsDeliver().executeRequest(
                    ServiceFactory.getINSTANCE().getRequestBuilder().withSortByOrganisationName().build()
            ));

        } catch (ServiceException e) {
            logger.warn(e);
        }


        request.getRequestDispatcher("/WEB-INF/jsp/bank/transactions.jsp").forward(request, response);
    }

    private ArrayList<Integer> getStartPages(int page) {

        ArrayList<Integer> pages = new ArrayList<>();
        int iPage = page - ADDITIONAL_PAGES_START;

        for (int i = 0; i < ADDITIONAL_PAGES_START; i++) {

            if (iPage > 0) {
                pages.add(iPage);
            }

            iPage++;
        }

        return pages;
    }

    private ArrayList<Integer> getFinishPages(int page, int pageCount) {

        ArrayList<Integer> pages = new ArrayList<>();

        for (int i = 0; i < ADDITIONAL_PAGES_FINISH; i++) {

            page++;

            if (page <= pageCount) {
                pages.add(page);
            }


        }

        return pages;
    }

    private int getStartPage(ArrayList<Integer> startPages) {
        return (startPages.size() > 0) && (startPages.get(0) > 1) ? 1 : -1;
    }

    private int getFinishPage(ArrayList<Integer> finishPages, int pageCount) {
        return (finishPages.size() > 0) && (finishPages.get(finishPages.size() - 1) < pageCount) ? pageCount : -1;
    }


}

