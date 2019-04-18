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

    public static class Builder {
        private DefaultLocation newDefaultLocation;

        public Builder() {
            newDefaultLocation = new DefaultLocation();
        }

        public Builder setType(String type) {
            newDefaultLocation.type = type;
            return this;
        }

        public Builder setCoordinates(List<Double> coordinates) {
            newDefaultLocation.coordinates = coordinates;
            return this;
        }

        public DefaultLocation build() {
            return newDefaultLocation;
        }
    }

    public String getType() {
        return type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

}
