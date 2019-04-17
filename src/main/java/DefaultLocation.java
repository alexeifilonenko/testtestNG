import org.mongodb.morphia.Datastore;

import java.util.List;

public class DefaultLocation {
    private String type;
    private List<Double> coordinates;

    public DefaultLocation(String type, List<Double> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public DefaultLocation() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}
