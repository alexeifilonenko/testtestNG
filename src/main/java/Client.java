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

    public Client(String name, String legalName, List<String> phone, List<String> email, List<String> allowedPermissions, DefaultLocation defaultLocation) {
        this.name = name;
        this.legalName = legalName;
        this.phone = phone;
        this.email = email;
        this.defaultLocation = defaultLocation;
        this.allowedPermissions = allowedPermissions;
    }

    public Client() {
    }

    public String getId() {
        return id;
    }

    public Client setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public String getLegalName() {
        return legalName;
    }

    public Client setLegalName(String legalName) {
        this.legalName = legalName;
        return this;
    }

    public List<String> getPhone() {
        return phone;
    }

    public Client setPhone(List<String> phone) {
        this.phone = phone;
        return this;
    }

    public List<String> getEmail() {
        return email;
    }

    public Client setEmail(List<String> email) {
        this.email = email;
        return this;
    }

    public DefaultLocation getDefaultLocation() {
        return defaultLocation;
    }

    public Client setDefaultLocation(DefaultLocation defaultLocation) {
        this.defaultLocation = defaultLocation;
        return this;
    }

    public List<String> getAllowedPermissions() {
        return allowedPermissions;
    }

    public Client setAllowedPermissions(List<String> allowedPermissions) {
        this.allowedPermissions = allowedPermissions;
        return this;
    }
}


