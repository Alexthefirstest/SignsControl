package by.epam.orders.bean;

import by.epam.rolesOrganisationsUsersController.bean.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class WorkersCrew implements Serializable , FactoryType {

    private static final long serialVersionUID = -5085596337299968847L;

    int id;
    private ArrayList<User> workers;
    Date creationDate;
    Date removeDate;
    String info;

    public WorkersCrew() {
    }

    public WorkersCrew(boolean setNewWorkersArray) {
        if (setNewWorkersArray) {
            workers = new ArrayList<>();
        }
    }


    public WorkersCrew(int id, ArrayList<User> workers) {
        this.id = id;
        this.workers = workers;
    }

    public void setNewWorkersArr() {
        workers = new ArrayList<>();
    }

    public void addWorker(User worker) {
        workers.add(worker);
    }

    public User getWorker(int position) {
        return workers.get(position);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<User> getWorkers() {
        return (ArrayList<User>) workers.clone();
    }

    public void setWorkers(ArrayList<User> workers) {
        this.workers = workers;
    }

    public Date getCreationDate() {
        return (Date) creationDate.clone();
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getRemoveDate() {
        return (Date) removeDate.clone();
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
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

        WorkersCrew that = (WorkersCrew) o;

        if (id != that.id) return false;
        if (workers != null ? !workers.equals(that.workers) : that.workers != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (removeDate != null ? !removeDate.equals(that.removeDate) : that.removeDate != null) return false;
        return info != null ? info.equals(that.info) : that.info == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (workers != null ? workers.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (removeDate != null ? removeDate.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WorkersCrew{");
        sb.append("id=").append(id);
        sb.append(", workers=").append(workers);
        sb.append(", creationDate=").append(creationDate);
        sb.append(", removeDate=").append(removeDate);
        sb.append(", info='").append(info).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
