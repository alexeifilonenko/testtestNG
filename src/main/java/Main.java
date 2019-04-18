
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        APIClient apiClient = new APIClient();
        System.out.println("SessionToken:");
        System.out.println(apiClient.putSessionToken());

        List<String> phone1 = Arrays.asList("123456789");
        List<String> email1 = Arrays.asList("alex574@alex574.com");
        List<String> phone2 = Arrays.asList("987654321");
        List<String> email2 = Arrays.asList("alex701@alex701.com");
        List<String> allowedPermissions = Arrays.asList("user:user:create:general");
        List<Double> coordinates = Arrays.asList(51.5073, -0.127758);
        DefaultLocation defaultLocation = new DefaultLocation("Point", coordinates);

       // Client client1 = new Client("Alex574", "Alexei574", phone1, email1, allowedPermissions, defaultLocation);
       // Client client2 = new Client("Alex701", "Alexei701", phone2, email2, allowedPermissions, defaultLocation);

        //apiClient.createClient(client1);

        //System.out.println(apiClient.getClientById().get_id() + apiClient.getClientById().getName());
        //apiClient.updateClient(client2);

        MongoConnector mongoConnector = new MongoConnector();
        mongoConnector.connectToMongo();
        //mongoConnector.getClientFromMongoById();
    }
}
