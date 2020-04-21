package by.epam.signsControl.dao;

import by.epam.signsControl.bean.StandardSize;

public interface IStandardSizesControl {

    boolean addStandardSize(int size);

    boolean removeStandardSize(int size);

    boolean setInfo(int id);

    String getInfo(int id);

    int[] getSizesInt();

    StandardSize[] getStandardSizes();

}
