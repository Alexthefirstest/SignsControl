package by.epam.signsControl.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class MapPoint$LocalSign implements Serializable, FactoryType, Cloneable {

    private static final long serialVersionUID = -8012668349900341256L;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new MapPoint$LocalSign((MapPoint) this.mapPoint.clone(), (ArrayList<ArrayList<LocalSign>>) this.listOfLocalSignsArrays.clone());
    }

    private MapPoint mapPoint;
    private ArrayList<ArrayList<LocalSign>> listOfLocalSignsArrays;

    public MapPoint$LocalSign() {
        listOfLocalSignsArrays= new ArrayList<>();
    }

    public MapPoint$LocalSign(MapPoint mapPoint, ArrayList<ArrayList<LocalSign>> listOfLocalSignsArrays) {
        this.mapPoint = mapPoint;
        this.listOfLocalSignsArrays = listOfLocalSignsArrays;
    }

    public MapPoint getMapPoint() {
        return (MapPoint) mapPoint.clone();
    }

    public void setMapPoint(MapPoint mapPoint) {
        this.mapPoint = mapPoint;
    }

    public ArrayList<ArrayList<LocalSign>> getListOfLocalSignsArrays() {
        return (ArrayList) listOfLocalSignsArrays.clone();
    }

    //throw index out of bound
    public LocalSign getLocalSignFromLastArr(int localSignPosition) {
        return (listOfLocalSignsArrays.get(listOfLocalSignsArrays.size()-1)).get(localSignPosition);
    }

    public void setListOfLocalSignsArrays(ArrayList<ArrayList<LocalSign>> listOfLocalSignsArrays) {
        this.listOfLocalSignsArrays = listOfLocalSignsArrays;
    }

    public void addLocalSignArr() {
        listOfLocalSignsArrays.add(new ArrayList<LocalSign>());
    }

    //throw an exception if list size = 0
    public void addLocalSignToLastArr(LocalSign localSign) {
        listOfLocalSignsArrays.get(listOfLocalSignsArrays.size() - 1).add(localSign);
    }

    //array list equal(Objects) working nice
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapPoint$LocalSign that = (MapPoint$LocalSign) o;

        if (mapPoint != null ? !mapPoint.equals(that.mapPoint) : that.mapPoint != null) return false;
        return listOfLocalSignsArrays != null ? listOfLocalSignsArrays.equals(that.listOfLocalSignsArrays) : that.listOfLocalSignsArrays == null;
    }

    @Override
    public int hashCode() {
        int result = mapPoint != null ? mapPoint.hashCode() : 0;
        result = 31 * result + (listOfLocalSignsArrays != null ? listOfLocalSignsArrays.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MapPoint$LocalSign{");
        sb.append("mapPoint={").append(mapPoint);
        sb.append("}, listOfLocalSignsArrays={").append(listOfLocalSignsArrays);
        sb.append("} }");
        return sb.toString();
    }
}


