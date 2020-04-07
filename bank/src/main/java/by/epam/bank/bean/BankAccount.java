package by.epam.bank.bean;

import java.io.Serializable;

public class BankAccount implements Serializable {

    private static final long serialVersionUID = -759976780346500349L;

    public BankAccount() {
    }

    public BankAccount(String organisation, int organisationID, double balance,
                       double minAllowedBalance, boolean isBlocked, String info) {
        this.organisation=organisation;
        this.organisationID = organisationID;
        this.balance = balance;
        this.minAllowedBalance=minAllowedBalance;
        this.isBlocked = isBlocked;
        this.info = info;
    }

    private String organisation;
    private double minAllowedBalance;
    private int organisationID;
    private double balance;
    private boolean isBlocked;
    private String info;

    public double getMinAllowedBalance() {
        return minAllowedBalance;
    }

    public void setMinAllowedBalance(double minAllowedBalance) {
        this.minAllowedBalance = minAllowedBalance;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public int getOrganisationID() {
        return organisationID;
    }

    public void setOrganisationID(int organisationID) {
        this.organisationID = organisationID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
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

        if (Double.compare(that.minAllowedBalance, minAllowedBalance) != 0) return false;
        if (organisationID != that.organisationID) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (isBlocked != that.isBlocked) return false;
        if (organisation != null ? !organisation.equals(that.organisation) : that.organisation != null) return false;
        return info != null ? info.equals(that.info) : that.info == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = organisation != null ? organisation.hashCode() : 0;
        temp = Double.doubleToLongBits(minAllowedBalance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + organisationID;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getClass().getName());
        sb.append("{organisation='").append(organisation).append('\'');
        sb.append(", organisationID=").append(organisationID);
        sb.append(", balance=").append(balance);
        sb.append(", minAllowedBalance=").append(minAllowedBalance);
        sb.append(", isBlocked=").append(isBlocked);
        sb.append(", info='").append(info).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
