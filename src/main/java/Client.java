import com.google.gson.annotations.SerializedName;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;


@Entity("clients")
public class Client {
    @Id
    @SerializedName("_id")
    private String id;
    private String name;
    private String legalName;
    private List<String> phone;
    private List<String> email;
    private List<String> allowedPermissions;
    private DefaultLocation defaultLocation;

    public Client() {
    }

    public static class Builder {
        private Client newClient;

        public Builder() {
            newClient = new Client();
        }

        public Builder setId(String id) {
            newClient.id = id;
            return this;
        }

        public Builder setName(String name) {
            newClient.name = name;
            return this;
        }

        public Builder setLegalName(String legalName) {
            newClient.legalName = legalName;
            return this;
        }

        public Builder setPhone(List<String> phone) {
            newClient.phone = phone;
            return this;
        }

        public Builder setEmail(List<String> email) {
            newClient.email = email;
            return this;
        }

        public Builder setDefaultLocation(DefaultLocation defaultLocation) {
            newClient.defaultLocation = defaultLocation;
            return this;
        }

        public Builder setAllowedPermissions(List<String> allowedPermissions) {
            newClient.allowedPermissions = allowedPermissions;
            return this;
        }

        public Client build() {
            return newClient;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLegalName() {
        return legalName;
    }

    public List<String> getPhone() {
        return phone;
    }

    public List<String> getEmail() {
        return email;
    }

    public DefaultLocation getDefaultLocation() {
        return defaultLocation;
    }

    public List<String> getAllowedPermissions() {
        return allowedPermissions;
    }

}


