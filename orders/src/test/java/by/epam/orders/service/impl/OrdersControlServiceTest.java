package by.epam.orders.service.impl;

import by.epam.orders.bean.Order;
import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.dao.IOrdersControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.orders.service.IOrdersControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.signsControl.bean.Sign;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import java.sql.Timestamp;
import java.util.Date;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrdersControlServiceTest {

    private IOrdersControl ordersControlMock;
    private IOrdersControlService ordersControlService;

    @Before
    public void setUp() throws Exception {

        ordersControlMock = mock(IOrdersControl.class);
        ordersControlService = new OrdersControlService(ordersControlMock);

    }

    @After
    public void tearDown() throws Exception {

        ordersControlService = null;
        ordersControlMock = null;

    }

    @Test
    public void addOrder() throws DAOException, ServiceException {

        Date date = new Date();

        when(ordersControlMock.addOrder(1, 1, 1, 1, 1, "info")).thenReturn(
                new Order(1, 1, new Sign(1, 1, 1, 1, null, "name", "description"),
                        2, new Organisation(1, "name", new Role(1, "role"), true, "info"),
                        2, 4, new TypeOfWork(2, "type", 3, false), 5, new Timestamp(date.getTime()),
                        new Timestamp(date.getTime()), "info")
        );

        Assert.assertEquals(ordersControlService.addOrder(1, 1, 1, 1, 1, "info"),
                new Order(1, 1, new Sign(1, 1, 1, 1, null, "name", "description"),
                        2, new Organisation(1, "name", new Role(1, "role"), true, "info"),
                        2, 4, new TypeOfWork(2, "type", 3, false), 5, new Timestamp(date.getTime()),
                        new Timestamp(date.getTime()), "info"));
    }

    @Test(expected = ServiceValidationException.class)
    public void addOrderValidationException() throws DAOException, ServiceException {


        when(ordersControlMock.addOrder(1, 1, 1, 1, 1, "info"))
                .thenThrow(DAOValidationException.class);

        ordersControlService.addOrder(1, 1, 1, 1, 1, "info");
    }

    @Test(expected = ServiceException.class)
    public void addOrderException() throws DAOException, ServiceException {


        when(ordersControlMock.addOrder(1, 1, 1, 1, 1, "info"))
                .thenThrow(DAOException.class);

        ordersControlService.addOrder(1, 1, 1, 1, 1, "info");
    }

    @Test(expected = ServiceValidationException.class)
    public void addOrderNullInfo() throws DAOException, ServiceException {

        ordersControlService.addOrder(1, 1, 1, 1, 1, null);
    }


    @Test
    public void setDateOfExecutionDatePatterns() throws DAOException, ServiceException {


        when(ordersControlMock.setDateOfExecution(Mockito.anyInt(), Mockito.anyString())).thenReturn(true);

        ordersControlService.setDateOfExecution(1, "2020.08.07");
        ordersControlService.setDateOfExecution(1, "2020.08");
        ordersControlService.setDateOfExecution(1, "2020");


    }


    @Test(expected = ServiceValidationException.class)
    public void setDateOfExecutionInvalidDateFormatTest1() throws DAOException, ServiceException {


        ordersControlService.setDateOfExecution(1, "1999.21.");

    }

    @Test(expected = ServiceValidationException.class)
    public void setDateOfExecutionInvalidDateFormatTest2() throws DAOException, ServiceException {

        ordersControlService.setDateOfExecution(1, "1999.");

    }

    @Test(expected = ServiceValidationException.class)
    public void setDateOfExecutionInvalidDateFormatTest3() throws DAOException, ServiceException {


        ordersControlService.setDateOfExecution(1, "08.05.2020");

    }

    @Test(expected = ServiceValidationException.class)
    public void setDateOfExecutionInvalidDateFormatTest4() throws DAOException, ServiceException {


        ordersControlService.setDateOfExecution(1, "05.2020");

    }


}