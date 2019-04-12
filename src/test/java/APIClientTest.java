import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class APIClientTest {
   private APIClient apiClient = new APIClient();
   private MongoConnector mongoConnector = new MongoConnector();


    @Test
    public void createClientTest() throws Exception {
        List<String> phone1 = Arrays.asList("123456789");
        List<String> email1 = Arrays.asList("alex594@alex594.com");
        List<String> allowedPermissions = Arrays.asList("user:user:create:general");
        List<Double> coordinates = Arrays.asList(51.50735, -0.1277582);
        DefaultLocation defaultLocation = new DefaultLocation("Point", coordinates);
        Client client1 = new Client("Alex594", "Alexei594", phone1, email1, allowedPermissions, defaultLocation);
        apiClient.putSessionToken();
        apiClient.createClient(client1);
        mongoConnector.connectToMongo();
        mongoConnector.getClientFromMongoById(apiClient.getClientId());
        Assert.assertEquals("Alex594", mongoConnector.getClientFromMongoById(apiClient.getClientId()).getName());
        Assert.assertEquals("Alexei594", mongoConnector.getClientFromMongoById(apiClient.getClientId()).getLegalName());
        Assert.assertEquals("user:user:create:general", mongoConnector.getClientFromMongoById(apiClient.getClientId()).getAllowedPermissions().get(0));
        Assert.assertEquals("alex594@alex594.com", mongoConnector.getClientFromMongoById(apiClient.getClientId()).getEmail().get(0));
        Assert.assertEquals("Point", mongoConnector.getClientFromMongoById(apiClient.getClientId()).getDefaultLocation().getType());
        Assert.assertEquals(51.50735, mongoConnector.getClientFromMongoById(apiClient.getClientId()).getDefaultLocation().getCoordinates().get(0));
        Assert.assertEquals(-0.1277582, mongoConnector.getClientFromMongoById(apiClient.getClientId()).getDefaultLocation().getCoordinates().get(1));
    }
    @Test
    public void getClientByIdTest() throws Exception {
        //apiClient.putSessionToken();
        apiClient.getClientId();
        //mongoConnector.connectToMongo();
        mongoConnector.getClientFromMongoById(apiClient.getClientId());
        Assert.assertEquals(apiClient.getClientById().getName(), mongoConnector.getClientFromMongoById(apiClient.getClientId()).getName());
        Assert.assertEquals(apiClient.getClientById().getLegalName(), mongoConnector.getClientFromMongoById(apiClient.getClientId()).getLegalName());
        Assert.assertEquals(apiClient.getClientById().getAllowedPermissions().get(0),mongoConnector.getClientFromMongoById(apiClient.getClientId()).getAllowedPermissions().get(0));
        Assert.assertEquals(apiClient.getClientById().getEmail().get(0), mongoConnector.getClientFromMongoById(apiClient.getClientId()).getEmail().get(0));
        Assert.assertEquals(apiClient.getClientById().getDefaultLocation().getType(), mongoConnector.getClientFromMongoById(apiClient.getClientId()).getDefaultLocation().getType());
        Assert.assertEquals(apiClient.getClientById().getDefaultLocation().getCoordinates().get(0), mongoConnector.getClientFromMongoById(apiClient.getClientId()).getDefaultLocation().getCoordinates().get(0));
        Assert.assertEquals(apiClient.getClientById().getDefaultLocation().getCoordinates().get(1), mongoConnector.getClientFromMongoById(apiClient.getClientId()).getDefaultLocation().getCoordinates().get(1));
    }
    @Test
    public void updateClientTest() {
        List<String> phone2 = Arrays.asList("987654321");
        List<String> email2 = Arrays.asList("alex704@alex704.com");
        List<String> allowedPermissions = Arrays.asList("user:campaign:delete:general");
        List<Double> coordinates = Arrays.asList(55.5555, -0.177777);
        DefaultLocation defaultLocation = new DefaultLocation("Point", coordinates);
        Client client2 = new Client("Alex704", "Alexei704", phone2, email2, allowedPermissions, defaultLocation);
        apiClient.updateClient(client2);
        mongoConnector.getClientFromMongoById(apiClient.getClientId());
        Assert.assertEquals("Alex704", mongoConnector.getClientFromMongoById(apiClient.getClientId()).getName());
        Assert.assertEquals("Alexei704", mongoConnector.getClientFromMongoById(apiClient.getClientId()).getLegalName());
        Assert.assertEquals("user:campaign:delete:general", mongoConnector.getClientFromMongoById(apiClient.getClientId()).getAllowedPermissions().get(0));
        Assert.assertEquals("alex704@alex704.com", mongoConnector.getClientFromMongoById(apiClient.getClientId()).getEmail().get(0));
        Assert.assertEquals("Point", mongoConnector.getClientFromMongoById(apiClient.getClientId()).getDefaultLocation().getType());
        Assert.assertEquals(55.5555, mongoConnector.getClientFromMongoById(apiClient.getClientId()).getDefaultLocation().getCoordinates().get(0));
        Assert.assertEquals(-0.177777, mongoConnector.getClientFromMongoById(apiClient.getClientId()).getDefaultLocation().getCoordinates().get(1));
    }
}
