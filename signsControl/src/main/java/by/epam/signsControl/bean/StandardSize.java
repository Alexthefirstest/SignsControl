package by.epam.signsControl.bean;

import java.io.Serializable;

public class StandardSize implements Serializable, SignsStaff {

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StandardSize that = (StandardSize) o;

        if (size != that.size) return false;
        return info != null ? info.equals(that.info) : that.info == null;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("{size=").append(size);
        sb.append(", info='").append(info).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
