package by.epam.signsControl.service;

import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.service.exceptions.ServiceException;

import java.io.InputStream;

public interface IPDDSignsControlService {

    Sign addSign(int section, int number, int kind) throws ServiceException;

    Sign addSign(int section, int number) throws ServiceException;

    boolean removeSign(int id) throws ServiceException;

    boolean updateSection(int id, int section) throws ServiceException;

    boolean updateNumber(int id, int number) throws ServiceException;

    boolean updateKind(int id, int kind) throws ServiceException;

    boolean setPicture(int id, InputStream inputStream) throws ServiceException;

    byte[] getPicture(int id) throws ServiceException;

    Sign[] getPddSigns() throws ServiceException;

    Sign[] getPddSigns(int section) throws ServiceException;

    Sign[] getPddSigns(int section, int number) throws ServiceException;

}
