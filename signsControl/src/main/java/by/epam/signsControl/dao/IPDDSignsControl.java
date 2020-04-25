package by.epam.signsControl.dao;

import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.dao.exceptions.DAOException;

import java.io.InputStream;

public interface IPDDSignsControl {

    Sign addSign(int section, int number, int kind) throws DAOException;

    Sign addSign(int section, int number) throws DAOException;

    Sign removeSign(int id) throws DAOException;

    boolean updateSection(int id, int section) throws DAOException;

    boolean updateNumber(int id, int number) throws DAOException;

    boolean updateKind(int id, int kind) throws DAOException;

    boolean setPicture(int id, InputStream inputStream) throws DAOException;

    byte[] getPicture(int id) throws DAOException;

    Sign[] getPddSigns() throws DAOException;

    Sign[] getPddSigns(int section) throws DAOException;

    Sign[] getPddSigns(int section, int number) throws DAOException;

}
