import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;


@Entity("clients")
    public class Client {
    @Id
        private String _id;
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
        public Client() {}

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLegalName() {
            return legalName;
        }

        public void setLegalName(String legalName) {
            this.legalName = legalName;
        }

        public List<String> getPhone() {
            return phone;
        }

        public void setPhone(List<String> phone) {
            this.phone = phone;
        }

        public List<String> getEmail() {
            return email;
        }

        public void setEmail(List<String> email) {
            this.email = email;
        }

        public DefaultLocation getDefaultLocation() {
            return defaultLocation;
        }

        public void setDefaultLocation(DefaultLocation defaultLocation) {
            this.defaultLocation = defaultLocation;
        }

        public List<String> getAllowedPermissions() {
            return allowedPermissions;
        }

        public void setAllowedPermissions(List<String> allowedPermissions) {
            this.allowedPermissions = allowedPermissions;
        }
    }


