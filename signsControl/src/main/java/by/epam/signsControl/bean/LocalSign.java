package by.epam.signsControl.bean;

import java.io.Serializable;
import java.util.Date;

public class LocalSign extends Sign implements Serializable, FactoryType {

    public LocalSign() {
    }

    public LocalSign(int localSignId, int signListId, int pddSignId, int section, int sign, int kind, byte[] picture, int standardSize, Date dateOfAdd, Date dateOfRemove, String annotation) {
        super(pddSignId, section, sign, kind, picture);
        this.localSignId = localSignId;
        this.signListId = signListId;
        this.standardSize = standardSize;
        this.dateOfAdd = dateOfAdd;
        this.dateOfRemove = dateOfRemove;
        this.annotation = annotation;
    }

    private static final long serialVersionUID = 4513518075537237951L;

    private int localSignId;
    private int signListId;
    private int standardSize;
    private Date dateOfAdd;
    private Date dateOfRemove;

    private String annotation;


    public int getLocalSignId() {
        return localSignId;
    }


    public void setLocalSignId(int id) {
        this.localSignId = id;
    }

    public int getSignListId() {
        return signListId;
    }

    public void setSignListId(int signListId) {
        this.signListId = signListId;
    }

    public int getStandardSize() {
        return standardSize;
    }

    public void setStandardSize(int standardSize) {
        this.standardSize = standardSize;
    }

    public Date getDateOfAdd() {
        return dateOfAdd;
    }

    public void setDateOfAdd(Date dateOfAdd) {
        this.dateOfAdd = dateOfAdd;
    }

    public Date getDateOfRemove() {
        return dateOfRemove;
    }

    public void setDateOfRemove(Date dateOfRemove) {
        this.dateOfRemove = dateOfRemove;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LocalSign localSign = (LocalSign) o;

        if (localSignId != localSign.localSignId) return false;
        if (signListId != localSign.signListId) return false;
        if (standardSize != localSign.standardSize) return false;
        if (dateOfAdd != null ? !dateOfAdd.equals(localSign.dateOfAdd) : localSign.dateOfAdd != null) return false;
        if (dateOfRemove != null ? !dateOfRemove.equals(localSign.dateOfRemove) : localSign.dateOfRemove != null)
            return false;
        return annotation != null ? annotation.equals(localSign.annotation) : localSign.annotation == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + localSignId;
        result = 31 * result + signListId;
        result = 31 * result + standardSize;
        result = 31 * result + (dateOfAdd != null ? dateOfAdd.hashCode() : 0);
        result = 31 * result + (dateOfRemove != null ? dateOfRemove.hashCode() : 0);
        result = 31 * result + (annotation != null ? annotation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocalSign{");
        sb.append("id=").append(localSignId);
        sb.append(", signListId=").append(signListId);
        sb.append(", ").append(super.toString());
        sb.append(", standardSize=").append(standardSize);
        sb.append(", dateOfAdd='").append(dateOfAdd).append('\'');
        sb.append(", dateOfRemove='").append(dateOfRemove).append('\'');
        sb.append(", annotation='").append(annotation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
