package by.epam.signsControl.dao;

import by.epam.signsControl.bean.Sign;

public interface IPDDSignsControl {

    boolean addSign(int section, int number, int kind);

    boolean addSign(int section, int number);

    boolean removeSign(int section, int number, int kind);

    boolean removeSign(int section, int number);

    boolean removeSign(int id);

    boolean updateSection(int id);

    boolean updateNumber(int id);

    boolean updateKind(int id);

    Sign[] getPddSigns();

    Sign[] getPddSigns(int section);

    Sign[] getPddSigns(int section, int number);

// BLOB
}
