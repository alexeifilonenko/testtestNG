import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;


public class APIClient {

    private static final String BASE_URL = "https://test03-platform.kino-mo.com";
    private static final String PATH_SESSION = "/api/admin/0/session";
    private static final String PATH_CLIENT = "/api/admin/0/client/";

    public static String putSessionToken(String USERNAME, String PASSWORD) {
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

    public static Client createClient(Client client, String sessionToken) {
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_CLIENT)
                .header("Content-Type", "application/json")
                .header("km-auth", sessionToken)
                .body(client);
        Response response = request.put();
        return response.jsonPath().getObject("message", Client.class);
    }

    public static Client getClientById(String clientId, String sessionToken) {
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_CLIENT + clientId)
                .header("Content-Type", "application/json")
                .header("km-auth", sessionToken);
        Response response = request.when().get();
        return response.jsonPath().getObject("message", Client.class);
    }

    public static Integer updateClient(Client client, String clientId, String sessionToken) {
        RequestSpecification request = given()
                .baseUri(BASE_URL)
                .basePath(PATH_CLIENT + clientId)
                .header("Content-Type", "application/json")
                .header("km-auth", sessionToken)
                .body(client);
        Response response = request.post();
        return response.getStatusCode();
    }
}
