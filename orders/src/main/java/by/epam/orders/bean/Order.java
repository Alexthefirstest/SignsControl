package by.epam.orders.bean;

import by.epam.bank.bean.Transaction;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.bean.StandardSize;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable, FactoryType {

    private static final long serialVersionUID = -2927331317579971691L;

    private int id;
    private int signList;
    private Sign sign;
    private int standardSize;
    private Organisation customerOrganisation;
    private int transactionID;
    private double transactionMoney;
    private TypeOfWork typeOFWork;
    private int workersCrew;
    private Timestamp dateOfOrder;
    private Timestamp dateOfExecution;
    private String info;

    public Order() {
    }


    public Order(int id, int signList, Sign sign, int standardSize, Organisation customerOrganisation,
                 int transactionID, double transactionMoney, TypeOfWork typeOFWork, int workersCrew, Timestamp dateOfOrder,
                 Timestamp dateOfExecution, String info) {
        this.id = id;
        this.signList = signList;
        this.sign = sign;
        this.standardSize = standardSize;
        this.customerOrganisation = customerOrganisation;
        this.transactionID=transactionID;
        this.transactionMoney=transactionMoney;
        this.typeOFWork = typeOFWork;
        this.workersCrew = workersCrew;
        this.dateOfOrder = dateOfOrder;
        this.dateOfExecution = dateOfExecution;
        this.info = info;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public int getStandardSize() {
        return standardSize;
    }

    public void setStandardSize(int standardSize) {
        this.standardSize = standardSize;
    }

    public Organisation getCustomerOrganisation() {
        return customerOrganisation;
    }

    public void setCustomerOrganisation(Organisation customerOrganisation) {
        this.customerOrganisation = customerOrganisation;
    }

    public int getSignList() {
        return signList;
    }

    public void setSignList(int signList) {
        this.signList = signList;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public double getTransactionMoney() {
        return transactionMoney;
    }

    public void setTransactionMoney(double transactionMoney) {
        this.transactionMoney = transactionMoney;
    }

    public TypeOfWork getTypeOFWork() {
        return typeOFWork;
    }

    public void setTypeOFWork(TypeOfWork typeOFWork) {
        this.typeOFWork = typeOFWork;
    }

    public int getWorkersCrew() {
        return workersCrew;
    }

    public void setWorkersCrew(int workersCrew) {
        this.workersCrew = workersCrew;
    }

    public Timestamp getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Timestamp dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Timestamp getDateOfExecution() {
        return dateOfExecution;
    }

    public void setDateOfExecution(Timestamp dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
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

        Order order = (Order) o;

        if (id != order.id) return false;
        if (signList != order.signList) return false;
        if (standardSize != order.standardSize) return false;
        if (transactionID != order.transactionID) return false;
        if (Double.compare(order.transactionMoney, transactionMoney) != 0) return false;
        if (workersCrew != order.workersCrew) return false;
        if (sign != null ? !sign.equals(order.sign) : order.sign != null) return false;
        if (customerOrganisation != null ? !customerOrganisation.equals(order.customerOrganisation) : order.customerOrganisation != null)
            return false;
        if (typeOFWork != null ? !typeOFWork.equals(order.typeOFWork) : order.typeOFWork != null) return false;
        if (dateOfOrder != null ? !dateOfOrder.equals(order.dateOfOrder) : order.dateOfOrder != null) return false;
        if (dateOfExecution != null ? !dateOfExecution.equals(order.dateOfExecution) : order.dateOfExecution != null)
            return false;
        return info != null ? info.equals(order.info) : order.info == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + signList;
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        result = 31 * result + standardSize;
        result = 31 * result + (customerOrganisation != null ? customerOrganisation.hashCode() : 0);
        result = 31 * result + transactionID;
        temp = Double.doubleToLongBits(transactionMoney);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (typeOFWork != null ? typeOFWork.hashCode() : 0);
        result = 31 * result + workersCrew;
        result = 31 * result + (dateOfOrder != null ? dateOfOrder.hashCode() : 0);
        result = 31 * result + (dateOfExecution != null ? dateOfExecution.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", signList=").append(signList);
        sb.append(", sign=").append(sign);
        sb.append(", standardSize=").append(standardSize);
        sb.append(", customerOrganisation=").append(customerOrganisation);
        sb.append(", transactionID=").append(transactionID);
        sb.append(", transactionMoney=").append(transactionMoney);
        sb.append(", typeOFWork=").append(typeOFWork);
        sb.append(", workersCrew=").append(workersCrew);
        sb.append(", dateOfOrder=").append(dateOfOrder);
        sb.append(", dateOfExecution=").append(dateOfExecution);
        sb.append(", info='").append(info).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
