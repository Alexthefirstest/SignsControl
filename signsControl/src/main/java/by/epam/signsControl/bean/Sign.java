package by.epam.signsControl.bean;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;

public class Sign implements Serializable, SignsStaff {

    private static final long serialVersionUID = 750945877308718374L;

    public Sign() {
    }

    int id;
    int section;
    int sign;
    int kind;
    byte[] picture;

    public Sign(int id, int section, int sign, int kind, byte[] picture) {
        this.id = id;
        this.section = section;
        this.sign = sign;
        this.kind = kind;
        this.picture=picture;

    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sign sign1 = (Sign) o;

        if (section != sign1.section) return false;
        if (sign != sign1.sign) return false;
        return kind == sign1.kind;
    }

    @Override
    public int hashCode() {
        int result = section;
        result = 31 * result + sign;
        result = 31 * result + kind;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("{id=").append(id);
        sb.append(", section=").append(section);
        sb.append(", sign=").append(sign);
        sb.append(", kind=").append(kind);
        sb.append(", picture=").append(Arrays.toString(picture));
        sb.append('}');
        return sb.toString();
    }
}
