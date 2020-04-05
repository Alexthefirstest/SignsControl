package by.epam.bank.bean;

import java.io.Serializable;

public class BankAccount implements Serializable {

    private static final long serialVersionUID = -759976780346500349L;

    public BankAccount() {
    }

    public BankAccount(int organisationID, long balance, boolean isBlocked, String info) {
        this.organisationID = organisationID;
        this.balance = balance;
        this.isBlocked = isBlocked;
        this.info = info;
    }

    private int organisationID;
    private long balance;
    private boolean isBlocked;
    private String info;


    public int getOrganisationID() {
        return organisationID;
    }

    public void setOrganisationID(int organisationID) {
        this.organisationID = organisationID;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
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

        BankAccount that = (BankAccount) o;

        if (organisationID != that.organisationID) return false;
        if (balance != that.balance) return false;
        if (isBlocked != that.isBlocked) return false;
        return info != null ? info.equals(that.info) : that.info == null;
    }

    @Override
    public int hashCode() {
        int result = organisationID;
        result = 31 * result + (int) (balance ^ (balance >>> 32));
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("{organisationID=").append(organisationID);
        sb.append(", balance=").append(balance);
        sb.append(", isBlocked=").append(isBlocked);
        sb.append(", info='").append(info).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
