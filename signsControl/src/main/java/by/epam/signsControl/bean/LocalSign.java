package by.epam.signsControl.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * bean for jdbc
 * <p>
 * extends {@link Sign}
 *
 * @author Bulgak Alexander
 * @see FactoryType
 * @see Serializable
 */
public class LocalSign extends Sign implements Serializable, FactoryType, Cloneable {

    /*
     *created by Intellij IDEA
     */
    private static final long serialVersionUID = 4513518075537237951L;

    /**
     * jdbc local sign id field
     */
    private int localSignId;

    /**
     * jdbc sign list id field
     */
    private int signListId;

    /**
     * jdbc standard size field
     */
    private int standardSize;

    /**
     * date of remove field
     */
    private Date dateOfAdd;

    /**
     * date of add field
     */
    private Date dateOfRemove;

    /**
     * annotation of sign field
     */
    private String annotation;

    /**
     * direction field char
     */
    private char angle;

    /**
     * @return clone of this object
     */
    @Override
    public Object clone() {

        LocalSign localSign = (LocalSign) super.clone();
        localSign.dateOfAdd = this.getDateOfAdd();
        localSign.dateOfRemove = this.getDateOfRemove();
        return localSign;

    }

    /**
     * empty constructor
     */
    public LocalSign() {
    }

    /**
     * constructor with all parameters
     *
     * @param localSignId  {@link LocalSign#localSignId}
     * @param signListId   {@link LocalSign#signListId
     * @param pddSignId    {@link Sign#id}
     * @param section      {@link Sign#section}
     * @param sign         {@link Sign#sign}
     * @param kind         {@link Sign#kind}
     * @param picture      {@link Sign#picture}
     * @param standardSize {@link LocalSign#standardSize}
     * @param dateOfAdd    {@link LocalSign#dateOfAdd}
     * @param dateOfRemove {@link LocalSign#dateOfRemove}
     * @param annotation   {@link LocalSign#annotation}
     * @param angle        {@link LocalSign#angle}
     * @param name         {@link LocalSign#name}
     * @param description  {@link LocalSign#description}
     */
    public LocalSign(int localSignId, int signListId, int pddSignId, int section, int sign, int kind, byte[] picture,
                     int standardSize, Date dateOfAdd, Date dateOfRemove, String annotation, char angle, String name, String description) {
        super(pddSignId, section, sign, kind, picture, name, description);
        this.localSignId = localSignId;
        this.signListId = signListId;
        this.standardSize = standardSize;
        this.dateOfAdd = dateOfAdd;
        this.dateOfRemove = dateOfRemove;
        this.annotation = annotation;
        this.angle = angle;
    }

    /**
     * @return {@link LocalSign#angle}
     */
    public char getAngle() {
        return angle;
    }

    /**
     * @param angle {@link LocalSign#angle}
     */
    public void setAngle(char angle) {
        this.angle = angle;
    }

    /**
     * @return {@link LocalSign#localSignId}
     */
    public int getLocalSignId() {
        return localSignId;
    }

    /**
     * @param id {@link LocalSign#localSignId}
     */
    public void setLocalSignId(int id) {
        this.localSignId = id;
    }

    /**
     * @return {@link LocalSign#signListId}
     */
    public int getSignListId() {
        return signListId;
    }

    /**
     * @param signListId {@link LocalSign#signListId}
     */
    public void setSignListId(int signListId) {
        this.signListId = signListId;
    }

    /**
     * @return {@link LocalSign#standardSize}
     */
    public int getStandardSize() {
        return standardSize;
    }

    /**
     * @param standardSize {@link LocalSign#standardSize}
     */
    public void setStandardSize(int standardSize) {
        this.standardSize = standardSize;
    }

    /**
     * @return {@link LocalSign#dateOfAdd}
     */
    public Date getDateOfAdd() {
        return dateOfAdd == null ? null : (Date) dateOfAdd.clone();
    }

    /**
     * @param dateOfAdd {@link LocalSign#dateOfAdd}
     */
    public void setDateOfAdd(Date dateOfAdd) {
        this.dateOfAdd = dateOfAdd;
    }

    /**
     * @return {@link LocalSign#dateOfRemove}
     */
    public Date getDateOfRemove() {
        return dateOfRemove == null ? null : (Date) dateOfRemove.clone();
    }

    /**
     * @param dateOfRemove {@link LocalSign#dateOfRemove}
     */
    public void setDateOfRemove(Date dateOfRemove) {
        this.dateOfRemove = dateOfRemove;
    }

    /**
     * @return {@link LocalSign#annotation}
     */
    public String getAnnotation() {
        return annotation;
    }

    /**
     * @param annotation {@link LocalSign#annotation}
     */
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
        sb.append(", angle=").append(angle);
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
