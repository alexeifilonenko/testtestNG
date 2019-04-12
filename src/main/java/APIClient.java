import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;


public class APIClient {
    private String sessionToken;
    private int StatusCode;
    private String clientId;
    private final String BASE_URL = "https://test03-platform.kino-mo.com";
    private final String PATH_SESSION = "/api/admin/0/session";
    private final String PATH_CLIENT = "/api/admin/0/client/";
    private final String USERNAME = "a.filonenko@hypervsn.com";
    private final String PASSWORD = "filonen-ko.com";
    private Client client;

    public Client getClient() {
        return client;
    }

    public String getClientId() {
        return clientId;
    }

    public String putSessionToken() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", USERNAME);
        requestBody.put("password", PASSWORD);
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_SESSION)
                .header("Content-Type", "application/json")
                .body(requestBody.toString());
        Response response = request.put();
        sessionToken = response.jsonPath().getString("message.sessionToken");
        return sessionToken;
    }

    public String createClient(Client client) {
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_CLIENT)
                .header("Content-Type", "application/json")
                .header("km-auth", sessionToken)
                .body(client);
        Response response = request.put();
        //System.out.println(response.asString());
        //JSONObject jsonResponse = new JSONObject(response.asString());
        //System.out.println(jsonResponse);
        Client clientResponse = response.jsonPath().getObject("message", Client.class);
        clientId = clientResponse.get_id();
        //System.out.println("ID created client: ");
        //System.out.println(clientId);
        return clientId;

    }

    public Client getClientById() {
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_CLIENT + clientId)
                .header("Content-Type", "application/json")
                .header("km-auth", sessionToken);


        Response response = request.when().get();


        JSONObject jsonObject = new JSONObject(response.asString());
        String returnClient = jsonObject.getJSONObject("message").getString("_id");
        //System.out.println(returnClient);
        client = response.jsonPath().getObject("message", Client.class);
        //System.out.println("Client ID: " + client.get_id() + " Client name: " + client.getName());
        return client;
    }

    public Integer updateClient(Client client) {
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_CLIENT + clientId)
                .header("Content-Type", "application/json")
                .header("km-auth", sessionToken)
                .body(client);
        Response response = request.post();
        //System.out.println(response.asString());
        //JSONObject jsonResponse = new JSONObject(response.asString());
        //System.out.println(jsonResponse);
        if (response.getStatusCode() == 200) {
            System.out.println("Client was updated");
        } else {
            System.out.println("Something went wrong...");
        }
        return StatusCode;
    }
}
