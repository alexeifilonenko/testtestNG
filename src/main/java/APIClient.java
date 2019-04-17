import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;


public class APIClient {

    private static String clientId;
    private static final String BASE_URL = "https://test03-platform.kino-mo.com";
    private static final String PATH_SESSION = "/api/admin/0/session";
    private static final String PATH_CLIENT = "/api/admin/0/client/";
    private static final String USERNAME = "a.filonenko@hypervsn.com";
    private static final String PASSWORD = "filonen-ko.com";

    public static String getClientId() {
        return clientId;
    }

    public static String putSessionToken() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", USERNAME);
        requestBody.put("password", PASSWORD);
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_SESSION)
                .header("Content-Type", "application/json")
                .body(requestBody.toString());
        Response response = request.put();
        return response.jsonPath().getString("message.sessionToken");
    }

    public static String createClient(Client client) {
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_CLIENT)
                .header("Content-Type", "application/json")
                .header("km-auth", APIClient.putSessionToken())
                .body(client);
        Response response = request.put();
        Client clientResponse = response.jsonPath().getObject("message", Client.class);
        clientId = clientResponse.getId();
        return clientId;
    }


    public static Client getClientById() {
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_CLIENT + clientId)
                .header("Content-Type", "application/json")
                .header("km-auth", APIClient.putSessionToken());
        Response response = request.when().get();
        //client = response.jsonPath().getObject("message", Client.class);
        return response.jsonPath().getObject("message", Client.class);
    }

    public static Integer updateClient(Client client) {
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_CLIENT + clientId)
                .header("Content-Type", "application/json")
                .header("km-auth", APIClient.putSessionToken())
                .body(client);
        Response response = request.post();
        return response.getStatusCode();
    }
}
