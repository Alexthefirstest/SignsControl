package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.bean.Transaction;
import by.epam.bank.service.ITransactionsDeliverService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;
import by.epam.bank.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.RequestParser;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.AccessRulesChecker;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * show transactions
 * show all transactions for bank workers
 * <p>
 * show only their organisation's transactions for administrators of this organisation
 */
public class ShowTransactionsHistory implements Command {

    private static final Logger logger = LogManager.getLogger(ShowTransactionsHistory.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        if (!AccessRulesChecker.userRoleCheckBool(request, Constants.ADMINISTRATOR_ROLE)) {
            AccessRulesChecker.organisationRoleCheck(request, Constants.BANK_ORGANISATION_ROLE);
        }


        int pageCount;
        int page;

        try {


            Transaction[] transactions;

            String findBy = request.getParameter("findBy");


            int organisationRole = (Integer) (request.getSession().getAttribute(Constants.ORGANISATION_ROLE));


            ITransactionsDeliverService transactionsDeliver = ServiceFactory.getINSTANCE().getTransactionsDeliver();

            try {
                page = Integer.parseInt(RequestParser.getSecondCommandFromURI(request));
            } catch (NullPointerException | NumberFormatException ex) {
                page = 1;
            }

            int organisationID = (Integer) (request.getSession().getAttribute(Constants.ORGANISATION_ID));

            logger.info(findBy);


            request.setAttribute("bank_accounts", ServiceFactory.getINSTANCE().getBankAccountsDeliver().executeRequest(
                    ServiceFactory.getINSTANCE().getRequestBuilder().withSortByOrganisationName().withoutID(organisationID).build()
            ));


            if (findBy != null && !findBy.isEmpty()) {

                if (organisationRole == Constants.BANK_ORGANISATION_ROLE) {

                    switch (findBy) {

                        case "OrgId":

                            int from = Integer.parseInt(request.getParameter("accountFrom"));
                            int to = Integer.parseInt(request.getParameter("accountTo"));


                            if (to < 0) {

                                transactions = transactionsDeliver.findTransactionsByFromPage(from, Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                                pageCount = transactionsDeliver.getPagesQuantityByFrom(from, Constants.COUNT_TRANSACTIONS_ON_PAGE);


                            } else if (from < 0) {
                                transactions = transactionsDeliver.findTransactionsByToPage(to, Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                                pageCount = transactionsDeliver.getPagesQuantityByTo(to, Constants.COUNT_TRANSACTIONS_ON_PAGE);
                            } else {

                                AccessRulesChecker.organisationRoleCheck(request, Constants.BANK_ORGANISATION_ROLE);

                                transactions = transactionsDeliver.findTransactionsByFromAndToPage(from, to, Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                                pageCount = transactionsDeliver.getPagesQuantityByFromAndTo(from, to, Constants.COUNT_TRANSACTIONS_ON_PAGE);
                            }
                            break;

                        case "date":
                            int fromWithDate = Integer.parseInt(request.getParameter("accountFromDate"));
                            String dateFrom = request.getParameter("dateFrom").replaceAll("-", ".");
                            String dateTo = request.getParameter("dateTo").replaceAll("-", ".");
                            logger.info("dateTo: " + dateTo);
                            transactions = transactionsDeliver.findTransactionsByDatePage(fromWithDate, dateFrom, dateTo, Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                            pageCount = transactionsDeliver.getPagesQuantityByDate(fromWithDate, dateFrom, dateTo, Constants.COUNT_TRANSACTIONS_ON_PAGE);
                            break;
                        default:
                            transactions = transactionsDeliver.getTransactionsPage(Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                            pageCount = transactionsDeliver.getPagesQuantity(Constants.COUNT_TRANSACTIONS_ON_PAGE);
                            break;
                    }

                } else {


                    int from = Integer.parseInt(request.getParameter("accountFrom"));
                    int to = Integer.parseInt(request.getParameter("accountTo"));

                    logger.info("from : " + from);
                    logger.info("to: " + to);

                    if (to < 0 && from < 0) {

                        if (to == -2) {
                            transactions = transactionsDeliver.findTransactionsByFromPage(organisationID, Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                            pageCount = transactionsDeliver.getPagesQuantityByFrom(organisationID, Constants.COUNT_TRANSACTIONS_ON_PAGE);
                        } else {
                            transactions = transactionsDeliver.findTransactionsByToPage(organisationID, Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                            pageCount = transactionsDeliver.getPagesQuantityByTo(organisationID, Constants.COUNT_TRANSACTIONS_ON_PAGE);
                        }

                    } else if (to < 0) {

                        transactions = transactionsDeliver.findTransactionsByFromAndToPage(from, organisationID, Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                        pageCount = transactionsDeliver.getPagesQuantityByFromAndTo(from, organisationID, Constants.COUNT_TRANSACTIONS_ON_PAGE);


                    } else {
                        transactions = transactionsDeliver.findTransactionsByFromAndToPage(organisationID, to, Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                        pageCount = transactionsDeliver.getPagesQuantityByFromAndTo(organisationID, to, Constants.COUNT_TRANSACTIONS_ON_PAGE);
                    }

                }


            } else { //find by

                if (organisationRole == Constants.BANK_ORGANISATION_ROLE) {
                    transactions = transactionsDeliver.getTransactionsPage(Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                    pageCount = transactionsDeliver.getPagesQuantity(Constants.COUNT_TRANSACTIONS_ON_PAGE);
                } else {
                    transactions = transactionsDeliver.findTransactionsByFromOrToPage(organisationID, Constants.COUNT_TRANSACTIONS_ON_PAGE, page);
                    pageCount = transactionsDeliver.getPagesQuantityByFromOrTo(organisationID, Constants.COUNT_TRANSACTIONS_ON_PAGE);
                }
            }

            if (page > pageCount) {

                if (pageCount == 0) {


                    request.setAttribute("transactionsNotFind", "true");

                    request.getRequestDispatcher("/WEB-INF/jsp/bank/transactions.jsp").forward(request, response);

                    return;
                }

                logger.info("wrong page page/pages quantity" + page + " / " + pageCount);
                request.getRequestDispatcher("/WEB-INF/error_pages/wrong_request.jsp").forward(request, response);
                return;
            }

            ArrayList<Integer> startPages = getStartPages(page);
            ArrayList<Integer> finishPages = getFinishPages(page, pageCount);


            request.setAttribute("currentPage", page);
            request.setAttribute("startPages", startPages);
            request.setAttribute("finishPages", finishPages);
            request.setAttribute("startPage", getStartPage(startPages));
            request.setAttribute("finishPage", getFinishPage(finishPages, pageCount));

            request.setAttribute("transactions", transactions);

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }


        request.getRequestDispatcher("/WEB-INF/jsp/bank/transactions.jsp").forward(request, response);
    }

    private ArrayList<Integer> getStartPages(int page) {

        ArrayList<Integer> pages = new ArrayList<>();
        int iPage = page - Constants.ADDITIONAL_TRANSACTIONS_PAGES_START;

        for (int i = 0; i < Constants.ADDITIONAL_TRANSACTIONS_PAGES_START; i++) {

            if (iPage > 0) {
                pages.add(iPage);
            }

            iPage++;
        }

        return pages;
    }

    private ArrayList<Integer> getFinishPages(int page, int pageCount) {

        ArrayList<Integer> pages = new ArrayList<>();

        for (int i = 0; i < Constants.ADDITIONA_TRANSACTIONSL_PAGES_FINISH; i++) {

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

