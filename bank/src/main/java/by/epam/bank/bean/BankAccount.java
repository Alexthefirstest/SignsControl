package by.epam.bank.bean;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;

import java.io.Serializable;

public class BankAccount implements Serializable {

    private static final long serialVersionUID = -759976780346500349L;

    public BankAccount() {
    }

    public BankAccount(Organisation organisation, double balance,
                       double minAllowedBalance, boolean blocked, String info) {
        this.organisation = organisation;
        this.balance = balance;
        this.minAllowedBalance = minAllowedBalance;
        this.blocked = blocked;
        this.info = info;
    }


    private Organisation organisation;
    private double minAllowedBalance;
    private double balance;
    private boolean blocked;
    private String info;

    public double getMinAllowedBalance() {
        return minAllowedBalance;
    }

    public void setMinAllowedBalance(double minAllowedBalance) {
        this.minAllowedBalance = minAllowedBalance;
    }

    public Organisation getOrganisation() {
        return (Organisation) organisation.clone();
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getInfo() {
        return info == null ? "" : info;
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
        if (Double.compare(that.balance, balance) != 0) return false;
        if (blocked != that.blocked) return false;
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
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (blocked ? 1 : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankAccount{");
        sb.append("organisation=").append(organisation);
        sb.append(", minAllowedBalance=").append(minAllowedBalance);
        sb.append(", balance=").append(balance);
        sb.append(", isBlocked=").append(blocked);
        sb.append(", info='").append(info).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
