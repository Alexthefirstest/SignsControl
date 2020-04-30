package rolesOrganisationsUsersController.bean;

import java.io.Serializable;

public class User implements FactoryType, Serializable {

    private static final long serialVersionUID = 6974354527403773473L;

    private int id;
    private String login;
    private int organisationID;
    private String organisation;
    private boolean block;
    private String name;
    private String surname;
    private String info;

    public User() {
    }


    public User(int id, String login, int organisationID, String organisation, boolean block, String name, String surname, String info) {
        this.id = id;
        this.login=login;
        this.organisationID = organisationID;
        this.organisation = organisation;
        this.block = block;
        this.name = name;
        this.surname = surname;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getOrganisationID() {
        return organisationID;
    }

    public void setOrganisationID(int organisationID) {
        this.organisationID = organisationID;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);

        if (login != null) {
            sb.append(", login='").append(login).append('\'');
        }

        sb.append(", organisationID=").append(organisationID);
        sb.append(", organisation='").append(organisation).append('\'');
        sb.append(", block=").append(block);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", info='").append(info).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
