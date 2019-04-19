import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class APIClientTest {

    private static final String NAME_CLIENT_1 = "Alex6731";
    private static final String LEGALNAME_CLIENT_1 = "Alexei6731";
    private static final String PHONE_CLIENT_1 = "+123456789";
    private static final String EMAIL_CLIENT_1 = "alex6731@alex6731.com";
    private static final String PERMISSIONS_CLIENT_1 = "user:user:create:general";
    private static final Double COORDINATE_1_CLIENT_1 = 51.50735;
    private static final Double COORDINATE_2_CLIENT_1 = -0.1277582;
    private static final String TYPE_LOCATION_CLIENT_1 = "Point";
    private static final String NAME_CLIENT_2 = "Alex7731";
    private static final String LEGALNAME_CLIENT_2 = "Alexei7731";
    private static final String PHONE_CLIENT_2 = "+987654321";
    private static final String EMAIL_CLIENT_2 = "alex7731@alex7731.com";
    private static final String PERMISSIONS_CLIENT_2 = "user:campaign:delete:general";
    private static final Double COORDINATE_1_CLIENT_2 = 51.5055;
    private static final Double COORDINATE_2_CLIENT_2 = -0.137777;
    private static final String TYPE_LOCATION_CLIENT_2 = "Point";

    public Client client1;
    public Client client2;
    public Client client;

    @BeforeTest
    public void initializeClient1() {

        DefaultLocation defaultLocation = new DefaultLocation.Builder()
                .setCoordinates(Arrays.asList(COORDINATE_1_CLIENT_1, COORDINATE_2_CLIENT_1))
                .setType(TYPE_LOCATION_CLIENT_1)
                .build();

        client1 = new Client.Builder()
                .setName(NAME_CLIENT_1)
                .setLegalName(LEGALNAME_CLIENT_1)
                .setPhone(Arrays.asList(PHONE_CLIENT_1))
                .setEmail(Arrays.asList(EMAIL_CLIENT_1))
                .setAllowedPermissions(Arrays.asList(PERMISSIONS_CLIENT_1))
                .setDefaultLocation(defaultLocation)
                .build();
    }

    @BeforeTest
    public void initializeClient2() {
        DefaultLocation defaultLocation = new DefaultLocation.Builder()
                .setCoordinates(Arrays.asList(COORDINATE_1_CLIENT_2, COORDINATE_2_CLIENT_2))
                .setType(TYPE_LOCATION_CLIENT_2)
                .build();

        client2 = new Client.Builder()
                .setName(NAME_CLIENT_2)
                .setLegalName(LEGALNAME_CLIENT_2)
                .setPhone(Arrays.asList(PHONE_CLIENT_2))
                .setEmail(Arrays.asList(EMAIL_CLIENT_2))
                .setAllowedPermissions(Arrays.asList(PERMISSIONS_CLIENT_2))
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
        Client clientFromDB = MongoConnector.getClientById(client.getId());
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
        Client clientFromDB = MongoConnector.getClientById(client.getId());
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
        Client clientFromDB = MongoConnector.getClientById(client.getId());
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
