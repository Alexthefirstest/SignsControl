package by.epam.bank.bean;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;

import java.io.Serializable;

/**
 * bean for sql bank accounts table
 */
public class BankAccount implements Serializable {

    /**
     * generated by IDEA
     */
    private static final long serialVersionUID = -759976780346500349L;

    /**
     * {@link Organisation} - bank account user
     */
    private Organisation organisation;

    /**
     * minimum balance allowed on bank account
     */
    private double minAllowedBalance;

    /**
     * current balance
     */
    private double balance;

    /**
     * block condition
     */
    private boolean blocked;

    /**
     * account info
     */
    private String info;

    /**
     * empty constructor
     */
    public BankAccount() {
    }

    /**
     * constructor with all parameters
     *
     * @param organisation      {@link BankAccount#organisation}
     * @param balance           {@link BankAccount#balance}
     * @param minAllowedBalance {@link BankAccount#minAllowedBalance}
     * @param blocked           {@link BankAccount#blocked}
     * @param info              {@link BankAccount#info}
     */
    public BankAccount(Organisation organisation, double balance,
                       double minAllowedBalance, boolean blocked, String info) {
        this.organisation = organisation;
        this.balance = balance;
        this.minAllowedBalance = minAllowedBalance;
        this.blocked = blocked;
        this.info = info;
    }

    /**
     * @return {@link BankAccount#minAllowedBalance}
     */
    public double getMinAllowedBalance() {
        return minAllowedBalance;
    }

    /**
     * @param minAllowedBalance {@link BankAccount#minAllowedBalance}
     */
    public void setMinAllowedBalance(double minAllowedBalance) {
        this.minAllowedBalance = minAllowedBalance;
    }

    /**
     * @return {@link BankAccount#organisation} clone
     */
    public Organisation getOrganisation() {
        return (Organisation) organisation.clone();
    }

    /**
     * @param organisation {@link BankAccount#organisation}
     */
    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    /**
     * @return {@link BankAccount#balance}
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance {@link BankAccount#balance}
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return {@link BankAccount#blocked}
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * @param blocked {@link BankAccount#blocked}
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * @return {@link BankAccount#info}
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info {@link BankAccount#info}
     */
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
