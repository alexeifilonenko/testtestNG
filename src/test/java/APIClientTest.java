import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class APIClientTest {

    public Client client1;
    public Client client2;

    @BeforeTest
    public void initializeClient1() {
        List<String> phone1 = Arrays.asList("123456789");
        List<String> email1 = Arrays.asList("alex648@alex648.com");
        List<String> allowedPermissions = Arrays.asList("user:user:create:general");
        List<Double> coordinates = Arrays.asList(51.50735, -0.1277582);
        DefaultLocation defaultLocation = new DefaultLocation("Point", coordinates);
        client1 = new Client("Alex648", "Alexei648", phone1, email1, allowedPermissions, defaultLocation);
    }

    @BeforeTest
    public void initializeClient2() {
        List<String> phone2 = Arrays.asList("987654321");
        List<String> email2 = Arrays.asList("alex748@alex748.com");
        List<String> allowedPermissions = Arrays.asList("user:campaign:delete:general");
        List<Double> coordinates = Arrays.asList(51.5055, -0.137777);
        DefaultLocation defaultLocation = new DefaultLocation("Point", coordinates);
        client2 = new Client("Alex748", "Alexei748", phone2, email2, allowedPermissions, defaultLocation);
    }

    @BeforeTest
    public void getToken() {
        APIClient.putSessionToken();
    }

    @BeforeTest
    public void connectToMongo() {
        MongoConnector.connectToMongo();
    }


    @Test
    public void createClientTest() throws Exception {
        APIClient.createClient(client1);
        Client clientFromDB = MongoConnector.getClientFromMongoById(APIClient.getClientId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(client1.getName(), clientFromDB.getName());
        softAssert.assertEquals(client1.getLegalName(), clientFromDB.getLegalName());
        softAssert.assertEquals(client1.getAllowedPermissions().get(0), clientFromDB.getAllowedPermissions().get(0));
        softAssert.assertEquals(client1.getEmail().get(0), clientFromDB.getEmail().get(0));
        softAssert.assertEquals(client1.getDefaultLocation().getType(), clientFromDB.getDefaultLocation().getType());
        softAssert.assertEquals(client1.getDefaultLocation().getCoordinates().get(0), clientFromDB.getDefaultLocation().getCoordinates().get(0));
        softAssert.assertEquals(client1.getDefaultLocation().getCoordinates().get(1), clientFromDB.getDefaultLocation().getCoordinates().get(1));
        softAssert.assertAll();
    }

    @Test
    public void getClientByIdTest() throws Exception {
        APIClient.getClientId();
        Client clientFromAPI = APIClient.getClientById();
        Client clientFromDB = MongoConnector.getClientFromMongoById(APIClient.getClientId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(clientFromAPI.getName(), clientFromDB.getName());
        softAssert.assertEquals(clientFromAPI.getLegalName(), clientFromDB.getLegalName());
        softAssert.assertEquals(clientFromAPI.getAllowedPermissions().get(0), clientFromDB.getAllowedPermissions().get(0));
        softAssert.assertEquals(clientFromAPI.getEmail().get(0), clientFromDB.getEmail().get(0));
        softAssert.assertEquals(clientFromAPI.getDefaultLocation().getType(), clientFromDB.getDefaultLocation().getType());
        softAssert.assertEquals(clientFromAPI.getDefaultLocation().getCoordinates().get(0), clientFromDB.getDefaultLocation().getCoordinates().get(0));
        softAssert.assertEquals(clientFromAPI.getDefaultLocation().getCoordinates().get(1), clientFromDB.getDefaultLocation().getCoordinates().get(1));
        softAssert.assertAll();
    }

    @Test
    public void updateClientTest() {
        APIClient.updateClient(client2);
        Client clientFromDB =  MongoConnector.getClientFromMongoById(APIClient.getClientId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(client2.getName(), clientFromDB.getName());
        softAssert.assertEquals(client2.getLegalName(), clientFromDB.getLegalName());
        softAssert.assertEquals(client2.getAllowedPermissions().get(0), clientFromDB.getAllowedPermissions().get(0));
        softAssert.assertEquals(client2.getEmail().get(0), clientFromDB.getEmail().get(0));
        softAssert.assertEquals(client2.getDefaultLocation().getType(), clientFromDB.getDefaultLocation().getType());
        softAssert.assertEquals(client2.getDefaultLocation().getCoordinates().get(0), clientFromDB.getDefaultLocation().getCoordinates().get(0));
        softAssert.assertEquals(client2.getDefaultLocation().getCoordinates().get(1), clientFromDB.getDefaultLocation().getCoordinates().get(1));
        softAssert.assertAll();
    }
}
