package by.epam.orders.bean;

import java.io.Serializable;

/**
 * class for sql type of work field
 */
public class TypeOfWork implements Serializable, FactoryType, Cloneable {

    /**
     * generated by IDEA
     */
    private static final long serialVersionUID = 1493709820799992287L;

    /**
     * type of work id
     */
    private int id;

    /**
     * type of work name
     */
    private String typeOfWork;

    /**
     * type of work price
     */
    private double price;

    /**
     * block condition
     */
    private boolean block;

    /**
     * empty constructor
     */
    public TypeOfWork() {
    }

    /**
     * constructor with all parameters
     *
     * @param id         {@link TypeOfWork#id}
     * @param typeOfWork {@link TypeOfWork#typeOfWork}
     * @param price      {@link TypeOfWork#price}
     * @param block      {@link TypeOfWork#block}
     */
    public TypeOfWork(int id, String typeOfWork, double price, boolean block) {
        this.id = id;
        this.typeOfWork = typeOfWork;
        this.price = price;
        this.block = block;
    }

    /**
     * @return this object clone
     */
    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * @return {@link TypeOfWork#block}
     */
    public boolean isBlock() {
        return block;
    }

    /**
     * @param block {@link TypeOfWork#block}
     */
    public void setBlock(boolean block) {
        this.block = block;
    }


    /**
     * @return {@link TypeOfWork#price}
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price {@link TypeOfWork#price}
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return {@link TypeOfWork#id}
     */
    public int getId() {
        return id;
    }

    /**
     * @param id {@link TypeOfWork#id}
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return {@link TypeOfWork#typeOfWork}
     */
    public String getTypeOfWork() {
        return typeOfWork;
    }

    /**
     * @param typeOfWork {@link TypeOfWork#typeOfWork}
     */
    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeOfWork that = (TypeOfWork) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        return typeOfWork != null ? typeOfWork.equals(that.typeOfWork) : that.typeOfWork == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (typeOfWork != null ? typeOfWork.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TypeOfWork{");
        sb.append("id=").append(id);
        sb.append(", typeOfWork='").append(typeOfWork).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
