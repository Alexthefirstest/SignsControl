package by.epam.signsControl.bean;

import java.io.Serializable;

public class Sign implements Serializable {

    private static final long serialVersionUID = 750945877308718374L;

    public Sign() {
    }

    int id;
    int section;
    int sign;
    int kind;

    public Sign(int id, int section, int sign, int kind) {
        this.id = id;
        this.section = section;
        this.sign = sign;
        this.kind = kind;
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

}
