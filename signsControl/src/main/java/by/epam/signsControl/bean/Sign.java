package by.epam.signsControl.bean;

import java.io.Serializable;
import java.util.Arrays;

public class Sign implements Serializable, FactoryType, Cloneable {

    @Override
    protected Object clone() {
        try {
            Sign sign = (Sign) super.clone();
            sign.picture =getPicture();
            return sign;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    private static final long serialVersionUID = 750945877308718374L;

    public Sign() {
    }

    private int id;
    private int section;
    private int sign;
    private int kind;
    private byte[] picture;
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sign(int id, int section, int sign, int kind, byte[] picture, String name, String description) {
        this.id = id;
        this.section = section;
        this.sign = sign;
        this.kind = kind;
        this.picture = picture;
        this.name = name;
        this.description = description;
    }

    public byte[] getPicture() {
        return this.picture==null? null : picture.clone();
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

        if (id != sign1.id) return false;
        if (section != sign1.section) return false;
        if (sign != sign1.sign) return false;
        if (kind != sign1.kind) return false;
        if (!Arrays.equals(picture, sign1.picture)) return false;
        if (name != null ? !name.equals(sign1.name) : sign1.name != null) return false;
        return description != null ? description.equals(sign1.description) : sign1.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + section;
        result = 31 * result + sign;
        result = 31 * result + kind;
        result = 31 * result + Arrays.hashCode(picture);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sign{");
        sb.append("id=").append(id);
        sb.append(", section=").append(section);
        sb.append(", sign=").append(sign);
        sb.append(", kind=").append(kind);
        sb.append(", picture=").append(Arrays.toString(picture));
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
