package by.epam.bank.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Transaction implements Serializable {


    private static final long serialVersionUID = 2619428068434472353L;

    public Transaction() {
    }

    public Transaction(int id, int idFrom, String from, int idTo, String to, double money, Timestamp date) {
        this.id = id;
        this.idFrom = idFrom;
        From = from;
        this.idTo = idTo;
        this.to = to;
        this.money = money;
        this.date = date;
    }

    private int id;
    private int idFrom;
    private String From;
    private int idTo;
    private String to;
    private double money;
    private Timestamp date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public int getIdTo() {
        return idTo;
    }

    public void setIdTo(int idTo) {
        this.idTo = idTo;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
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
        if (idFrom != that.idFrom) return false;
        if (idTo != that.idTo) return false;
        if (Double.compare(that.money, money) != 0) return false;
        if (From != null ? !From.equals(that.From) : that.From != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + idFrom;
        result = 31 * result + (From != null ? From.hashCode() : 0);
        result = 31 * result + idTo;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("{id=").append(id);
        sb.append(", idFrom=").append(idFrom);
        sb.append(", From='").append(From).append('\'');
        sb.append(", idTo=").append(idTo);
        sb.append(", to='").append(to).append('\'');
        sb.append(", money=").append(money);
        sb.append(", date=").append(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date));
        sb.append('}');
        return sb.toString();
    }
}
