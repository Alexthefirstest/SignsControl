package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.dao.ILocalSignsControl;
import by.epam.signsControl.dao.IPDDSignsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PDDSignsControlServiceTest {

    private IPDDSignsControl pddSignsControlMockDao;
    private PDDSignsControlService pddSignsControlService;

    @Before
    public void setUp() {

        pddSignsControlMockDao = mock(IPDDSignsControl.class);

        pddSignsControlService = new PDDSignsControlService(pddSignsControlMockDao);

    }

    @After
    public void tearDown() {
        pddSignsControlMockDao = null;
        pddSignsControlService = null;
    }

    @Test
    public void addSign() throws DAOException, ServiceException {

        when(pddSignsControlMockDao.addSign(anyInt(), anyInt(), anyInt(), anyString())).
                thenReturn(new Sign(1, 2, 3, 4, null, "name", "description"));

        Assert.assertEquals(pddSignsControlService.addSign(1, 2, 3, "name"),
                new Sign(1, 2, 3, 4, null, "name", "description"));


    }


    @Test(expected = ServiceException.class)
    public void addSignException() throws DAOException, ServiceException {

        when(pddSignsControlMockDao.addSign(anyInt(), anyInt(), anyInt(), anyString())).
                thenThrow(DAOException.class);

        pddSignsControlService.addSign(1, 2, 3, "name");


    }

    @Test(expected = ServiceValidationException.class)
    public void addSignValidationException() throws DAOException, ServiceException {

        when(pddSignsControlMockDao.addSign(anyInt(), anyInt(), anyInt(), anyString())).
                thenThrow(DAOValidationException.class);

        pddSignsControlService.addSign(1, 2, 3, "name");


    }

    @Test(expected = ServiceValidationException.class)
    public void addSignDataIsNull() throws DAOException, ServiceException {

        pddSignsControlService.addSign(1, 2, 3, null);


    }


}