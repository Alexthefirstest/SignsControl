package by.epam.signsControl.dao.impl;

import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.dao.IPDDSignsControl;

public class PDDSignsControl implements IPDDSignsControl {

// private static final String SQL_SELECT_LAST_INSERT_ID = "SELECT LAST_INSERT_ID() FROM standard_sizes where number=LAST_INSERT_ID();";

    @Override
    public Sign addSign(int section, int number, int kind) {
        return null;
    }

    @Override
    public Sign addSign(int section, int number) {
        return null;
    }

    @Override
    public boolean removeSign(int section, int number, int kind) {
        return false;
    }

    @Override
    public boolean removeSign(int section, int number) {
        return false;
    }

    @Override
    public boolean removeSign(int id) {
        return false;
    }

    @Override
    public boolean updateSection(int id) {
        return false;
    }

    @Override
    public boolean updateNumber(int id) {
        return false;
    }

    @Override
    public boolean updateKind(int id) {
        return false;
    }

    @Override
    public Sign[] getPddSigns() {
        return new Sign[0];
    }

    @Override
    public Sign[] getPddSigns(int section) {
        return new Sign[0];
    }

    @Override
    public Sign[] getPddSigns(int section, int number) {
        return new Sign[0];
    }
}
