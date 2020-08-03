package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * check access rules by user and organisation roles
 */
public class AccessRulesChecker {


    private static final Logger logger = LogManager.getLogger(AccessRulesChecker.class);

    private AccessRulesChecker() {

    }

    /**
     * check organisation role by {@link by.epam.signsControl.webView.Constants#ORGANISATION_ROLE} request attribute
     *
     * @param request       {@link HttpServletRequest}
     * @param requiredRoles roles to check
     * @throws CommandControllerValidationException if request.role not one of requiredRoles
     */
    public static void organisationRoleCheck(HttpServletRequest request, int... requiredRoles) throws CommandControllerValidationException {

        int organisationRole = (Integer) (request.getAttribute(Constants.ORGANISATION_ROLE));

        for (int i = 0; i < requiredRoles.length; i++) {
            if (requiredRoles[i] == organisationRole) {
               return;
            }
        }
        logger.warn("wrong organisation role");
        throw new CommandControllerValidationException("wrong organisation role access");
    }

    /**
     * check users role by {@link by.epam.signsControl.webView.Constants#USER_ROLE} request attribute
     *
     * @param request       {@link HttpServletRequest}
     * @param requiredRoles roles to check
     * @throws CommandControllerValidationException if request.role not one of requiredRoles
     */
    public static void userRoleCheck(HttpServletRequest request, int... requiredRoles) throws CommandControllerValidationException {

        int userRole = (Integer) (request.getAttribute(Constants.USER_ROLE));

        for (int requiredRole : requiredRoles) {
            if (requiredRole == userRole) {
                return;
            }
        }
//send to the wrong role page
        logger.warn("wrong  user role");
        throw new CommandControllerValidationException("wrong user role access");
    }

    /**
     * check users role by {@link by.epam.signsControl.webView.Constants#USER_ROLE} request attribute is not anonym
     *
     * @param request {@link HttpServletRequest}
     * @throws CommandControllerValidationException if anonym
     */
    public static void notAnonymCheck(HttpServletRequest request) throws CommandControllerValidationException {

        int userRole = (Integer) (request.getAttribute(Constants.USER_ROLE));

        if (userRole != Constants.USER_ANONYM_ROLE) {
            return;
        }
//send to the wrong role page
        logger.warn("anonym  user role");
        throw new CommandControllerValidationException("wrong organisation role access");
    }


    /**
     * check organisation role by {@link by.epam.signsControl.webView.Constants#ORGANISATION_ROLE} request attribute
     *
     * @param request       {@link HttpServletRequest}
     * @param requiredRoles roles to check
     * @return true if request role = one of the required roles or false in other case
     */
    public static boolean organisationRoleCheckBool(HttpServletRequest request, int... requiredRoles)  {

        int organisationRole = (Integer) (request.getAttribute(Constants.ORGANISATION_ROLE));

        for (int i = 0; i < requiredRoles.length; i++) {
            if (requiredRoles[i] == organisationRole) {
                return true;
            }
        }

       return false;
    }

    /**
     * check users role by {@link by.epam.signsControl.webView.Constants#USER_ROLE} request attribute
     *
     * @param request       {@link HttpServletRequest}
     * @param requiredRoles roles to check
     * @return true if request role = one of the required roles or false in other case
     */
    public static boolean userRoleCheckBool(HttpServletRequest request, int... requiredRoles) {

        int userRole = (Integer) (request.getAttribute(Constants.USER_ROLE));

        for (int i = 0; i < requiredRoles.length; i++) {
            if (requiredRoles[i] == userRole) {
                return true;
            }
        }

        logger.warn("wrong  user role");
        return false;
    }

}
