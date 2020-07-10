package by.epam.bank.bean;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;

import java.io.Serializable;
import java.sql.Timestamp;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 2619428068434472353L;

    public Transaction() {
    }

    public Transaction(int id, Organisation from, Organisation to, double money, Timestamp date) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.money = money;
        this.date = date;
    }

    public Organisation getFrom() {
        return (Organisation) from.clone();
    }

    public void setFrom(Organisation from) {
        this.from = from;
    }

    private int id;
    private Organisation from;
    private Organisation to;
    private double money;
    private Timestamp date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Organisation getTo() {
        return (Organisation) to.clone();
    }

    public void setTo(Organisation to) {
        this.to = to;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (id != that.id) return false;
        if (Double.compare(that.money, money) != 0) return false;
        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transaction{");
        sb.append("id=").append(id);
        sb.append(", from=").append(from);
        sb.append(", to=").append(to);
        sb.append(", money=").append(money);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
