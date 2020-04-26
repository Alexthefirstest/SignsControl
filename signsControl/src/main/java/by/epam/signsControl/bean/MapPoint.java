package by.epam.signsControl.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class MapPoint implements SignsStaff, Serializable {

    private static final long serialVersionUID = -2559001946431331274L;

    public MapPoint() {
    }

    public MapPoint(String coordinates, ArrayList<String> addresses, ArrayList<Integer> signsLists, ArrayList<String> annotations) {
        this.coordinates = coordinates;
        this.addresses = addresses;
        this.signsLists = signsLists;
        this.annotations = annotations;
    }

    String coordinates;
    ArrayList<String> addresses = new ArrayList<>();
    ArrayList<Integer> signsLists = new ArrayList<>();
    ArrayList<String> annotations = new ArrayList<>();

    public void addAddress(String addresses) {
        this.addresses.add(addresses);
    }

    public void addSignsList(int signsList) {
        this.signsLists.add(signsList);
    }

    public void addAnnotation(String annotation) {
        this.annotations.add(annotation);
    }

    public String getCoordinates() {
        return coordinates;
    }


    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<String> getAddresses() {
        return new ArrayList<String>(addresses);
    }

    public void setAddresses(ArrayList<String> addresses) {
        this.addresses = addresses;
    }

    public ArrayList<Integer> getSignsLists() {
        return new ArrayList<Integer>(signsLists);
    }

    public void setSignsLists(ArrayList<Integer> signsLists) {
        this.signsLists = signsLists;
    }

    public ArrayList<String> getAnnotations() {
        return new ArrayList<String>(annotations);
    }

    public void setAnnotations(ArrayList<String> annotations) {
        this.annotations = annotations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapPoint mapPoint = (MapPoint) o;

        if (coordinates != null ? !coordinates.equals(mapPoint.coordinates) : mapPoint.coordinates != null)
            return false;
        if (addresses != null ? !addresses.equals(mapPoint.addresses) : mapPoint.addresses != null) return false;
        if (signsLists != null ? !signsLists.equals(mapPoint.signsLists) : mapPoint.signsLists != null) return false;
        return annotations != null ? annotations.equals(mapPoint.annotations) : mapPoint.annotations == null;
    }

    @Override
    public int hashCode() {
        int result = coordinates != null ? coordinates.hashCode() : 0;
        result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
        result = 31 * result + (signsLists != null ? signsLists.hashCode() : 0);
        result = 31 * result + (annotations != null ? annotations.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MapPoint{");
        sb.append("coordinates='").append(coordinates).append('\'');
        sb.append(", addresses=").append(addresses);
        sb.append(", SignsLists=").append(signsLists);
        sb.append(", annotations=").append(annotations);
        sb.append('}');
        return sb.toString();
    }
}
