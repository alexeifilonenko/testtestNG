import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class APIClientTest {

    public Client client1;
    public Client client2;
    public Client client;

    @BeforeTest
    public void initializeClient1() {

        DefaultLocation defaultLocation = new DefaultLocation.Builder()
                .setCoordinates(Arrays.asList(51.50735, -0.1277582))
                .setType("Point")
                .build();

        client1 = new Client.Builder()
                .setName("Alex662")
                .setLegalName("Alexei662")
                .setPhone(Arrays.asList("123456789"))
                .setEmail(Arrays.asList("alex662@alex662.com"))
                .setAllowedPermissions(Arrays.asList("user:user:create:general"))
                .setDefaultLocation(defaultLocation)
                .build();
    }

    @BeforeTest
    public void initializeClient2() {
        DefaultLocation defaultLocation = new DefaultLocation.Builder()
                .setCoordinates(Arrays.asList(51.5055, -0.137777))
                .setType("Point")
                .build();

        client2 = new Client.Builder()
                .setName("Alex762")
                .setLegalName("Alexei762")
                .setPhone(Arrays.asList("987654321"))
                .setEmail(Arrays.asList("alex762@alex762.com"))
                .setAllowedPermissions(Arrays.asList("user:campaign:delete:general"))
                .setDefaultLocation(defaultLocation)
                .build();
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
        client = APIClient.createClient(client1);
        Client clientFromDB = MongoConnector.getClientFromMongoById(client.getId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(client1.getName(), clientFromDB.getName());
        softAssert.assertEquals(client1.getLegalName(), clientFromDB.getLegalName());
        softAssert.assertEquals(client1.getAllowedPermissions().get(0), clientFromDB.getAllowedPermissions().get(0));
        softAssert.assertEquals(client1.getPhone().get(0), clientFromDB.getPhone().get(0));
        softAssert.assertEquals(client1.getEmail().get(0), clientFromDB.getEmail().get(0));
        softAssert.assertEquals(client1.getDefaultLocation().getType(), clientFromDB.getDefaultLocation().getType());
        softAssert.assertEquals(client1.getDefaultLocation().getCoordinates().get(0), clientFromDB.getDefaultLocation().getCoordinates().get(0));
        softAssert.assertEquals(client1.getDefaultLocation().getCoordinates().get(1), clientFromDB.getDefaultLocation().getCoordinates().get(1));
        softAssert.assertAll();
    }

    @Test
    public void getClientByIdTest() throws Exception {
        Client clientFromAPI = APIClient.getClientById(client.getId());
        Client clientFromDB = MongoConnector.getClientFromMongoById(client.getId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(clientFromAPI.getName(), clientFromDB.getName());
        softAssert.assertEquals(clientFromAPI.getLegalName(), clientFromDB.getLegalName());
        softAssert.assertEquals(clientFromAPI.getAllowedPermissions().get(0), clientFromDB.getAllowedPermissions().get(0));
        softAssert.assertEquals(clientFromAPI.getPhone().get(0), clientFromDB.getPhone().get(0));
        softAssert.assertEquals(clientFromAPI.getEmail().get(0), clientFromDB.getEmail().get(0));
        softAssert.assertEquals(clientFromAPI.getDefaultLocation().getType(), clientFromDB.getDefaultLocation().getType());
        softAssert.assertEquals(clientFromAPI.getDefaultLocation().getCoordinates().get(0), clientFromDB.getDefaultLocation().getCoordinates().get(0));
        softAssert.assertEquals(clientFromAPI.getDefaultLocation().getCoordinates().get(1), clientFromDB.getDefaultLocation().getCoordinates().get(1));
        softAssert.assertAll();
    }

    @Test
    public void updateClientTest() {
        APIClient.updateClient(client2, client.getId());
        Client clientFromDB = MongoConnector.getClientFromMongoById(client.getId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(client2.getName(), clientFromDB.getName());
        softAssert.assertEquals(client2.getLegalName(), clientFromDB.getLegalName());
        softAssert.assertEquals(client2.getAllowedPermissions().get(0), clientFromDB.getAllowedPermissions().get(0));
        softAssert.assertEquals(client2.getPhone().get(0), clientFromDB.getPhone().get(0));
        softAssert.assertEquals(client2.getEmail().get(0), clientFromDB.getEmail().get(0));
        softAssert.assertEquals(client2.getDefaultLocation().getType(), clientFromDB.getDefaultLocation().getType());
        softAssert.assertEquals(client2.getDefaultLocation().getCoordinates().get(0), clientFromDB.getDefaultLocation().getCoordinates().get(0));
        softAssert.assertEquals(client2.getDefaultLocation().getCoordinates().get(1), clientFromDB.getDefaultLocation().getCoordinates().get(1));
        softAssert.assertAll();
    }
}
