package by.epam.signsControl.bean;

import java.io.Serializable;

public class StandardSize implements Serializable {

    public StandardSize() {
    }

    public StandardSize(int size, String info) {
        this.size = size;
        this.info = info;
    }

    private static final long serialVersionUID = -6583064798614414700L;

    int size;
    String info;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
