package by.epam.orders.bean;

import by.epam.signsControl.bean.MapPoint;

import java.io.Serializable;
import java.util.ArrayList;

public class MapPoint$Orders implements Serializable, FactoryType, Cloneable {


    private static final long serialVersionUID = -712450621689847038L;

    @Override
    protected Object clone() {
        return new MapPoint$Orders((this.mapPoint == null ? null : (MapPoint) this.mapPoint.clone()),
                (this.listOfOrders == null ? null : (ArrayList<Order>) this.listOfOrders.clone()));
    }

    private MapPoint mapPoint;
    private ArrayList<Order> listOfOrders;

    public MapPoint$Orders() {
        listOfOrders = new ArrayList<>();
    }

    public MapPoint$Orders(MapPoint mapPoint, ArrayList<Order> listOfOrders) {
        this.mapPoint = mapPoint;
        this.listOfOrders = listOfOrders;
    }

    public MapPoint getMapPoint() {
        return (this.mapPoint == null ? null : (MapPoint) this.mapPoint.clone());
    }

    public void setMapPoint(MapPoint mapPoint) {
        this.mapPoint = mapPoint;
    }

    public ArrayList<Order> getListOfOrders() {
        return (this.listOfOrders == null ? null : (ArrayList<Order>) this.listOfOrders.clone());
    }


    public void setListOfOrders(ArrayList<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    //array list equal(Objects) working nice
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapPoint$Orders that = (MapPoint$Orders) o;

        if (mapPoint != null ? !mapPoint.equals(that.mapPoint) : that.mapPoint != null) return false;
        return listOfOrders != null ? listOfOrders.equals(that.listOfOrders) : that.listOfOrders == null;
    }

    @Override
    public int hashCode() {
        int result = mapPoint != null ? mapPoint.hashCode() : 0;
        result = 31 * result + (listOfOrders != null ? listOfOrders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MapPoint$Orders{");
        sb.append("mapPoint={").append(mapPoint);
        sb.append("}, listOfOrders={").append(listOfOrders);
        sb.append("} }");
        return sb.toString();
    }
}


