package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.exceptions.AccessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * check access rules by user and organisation roles
 */
public class AccessRulesChecker {


    /**
     * logger
     */
    private static final Logger logger = LogManager.getLogger(AccessRulesChecker.class);

    /**
     * private constructor
     */
    private AccessRulesChecker() {

    }

    /**
     * check organisation role by {@link by.epam.signsControl.webView.Constants#ORGANISATION_ROLE} request attribute
     *
     * @param request       {@link HttpServletRequest}
     * @param requiredRoles roles to check
     * @throws AccessException if request.role not one of requiredRoles
     */
    public static void organisationRoleCheck(HttpServletRequest request, int... requiredRoles) throws AccessException {

        Object organisationRoleObj = (request.getAttribute(Constants.ORGANISATION_ROLE));

        if (organisationRoleObj != null) {

            int organisationRole = (Integer) organisationRoleObj;

            for (int requiredRole : requiredRoles) {
                if (requiredRole == organisationRole) {
                    return;
                }
            }

        }
        logger.warn("wrong organisation role");
        throw new AccessException("wrong organisation role access");
    }

    /**
     * check users role by {@link by.epam.signsControl.webView.Constants#USER_ROLE} request attribute
     *
     * @param request       {@link HttpServletRequest}
     * @param requiredRoles roles to check
     * @throws AccessException if request.role not one of requiredRoles
     */
    public static void userRoleCheck(HttpServletRequest request, int... requiredRoles) throws AccessException {

        Object userRoleObj = (request.getAttribute(Constants.USER_ROLE));

        if (userRoleObj != null) {


            int userRole = (Integer) userRoleObj;

            for (int requiredRole : requiredRoles) {
                if (requiredRole == userRole) {
                    return;
                }
            }

        }
        logger.warn("wrong  user role");
        throw new AccessException("wrong user role access");
    }

    /**
     * check users role by {@link by.epam.signsControl.webView.Constants#USER_ROLE} request attribute is not anonym
     *
     * @param request {@link HttpServletRequest}
     * @throws AccessException if anonym
     */
    public static void notAnonymCheck(HttpServletRequest request) throws AccessException {

        Object userRoleObj = (request.getAttribute(Constants.USER_ROLE));

        if (userRoleObj != null && (Integer) userRoleObj != Constants.USER_ANONYM_ROLE) {
            return;
        }
//send to the wrong role page
        logger.warn("anonym  user role");
        throw new AccessException("user is anonym");
    }


    /**
     * check organisation role by {@link by.epam.signsControl.webView.Constants#ORGANISATION_ROLE} request attribute
     *
     * @param request       {@link HttpServletRequest}
     * @param requiredRoles roles to check
     * @return true if request role is one of the required roles or false in other case
     */
    public static boolean organisationRoleCheckBool(HttpServletRequest request, int... requiredRoles) {


        Object organisationRoleObj = (request.getAttribute(Constants.ORGANISATION_ROLE));

        if (organisationRoleObj != null) {

            int organisationRole = (Integer) organisationRoleObj;

            for (int requiredRole : requiredRoles) {
                if (requiredRole == organisationRole) {
                    return true;
                }
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

        Object userRoleObj = (request.getAttribute(Constants.USER_ROLE));

        if (userRoleObj != null) {

            int userRole = (Integer) userRoleObj;

            for (int requiredRole : requiredRoles) {
                if (requiredRole == userRole) {
                    return true;
                }
            }

        }
        logger.warn("wrong  user role");
        return false;
    }

}
