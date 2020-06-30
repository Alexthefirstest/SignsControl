package by.epam.signsControl.bean;

import java.io.Serializable;

public class Direction implements FactoryType, Serializable {
    private static final long serialVersionUID = -5364144967319472433L;

    private int id;
    private char direction;

    public Direction() {
    }

    public Direction(int id, char direction) {
        this.id = id;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        if (id != direction1.id) return false;
        return direction == direction1.direction;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) direction;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Direction{");
        sb.append("id=").append(id);
        sb.append(", direction=").append(direction);
        sb.append('}');
        return sb.toString();
    }
}
