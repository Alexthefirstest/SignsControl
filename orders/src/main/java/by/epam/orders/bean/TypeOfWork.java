package by.epam.orders.bean;

import java.io.Serializable;

public class TypeOfWork implements Serializable, FactoryType, Cloneable {

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    private static final long serialVersionUID = 1493709820799992287L;

    private int id;
    private String typeOfWork;
    private double price;
    private boolean block;

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public TypeOfWork() {
    }

    public TypeOfWork(int id, String typeOfWork, double price, boolean block) {
        this.id = id;
        this.typeOfWork = typeOfWork;
        this.price=price;
        this.block=block;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

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
