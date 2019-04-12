import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.logging.Logger;

public class MongoConnector {
    private final String DB_HOST_1 = "10.10.0.26";
    private final String DB_HOST_2 = "10.10.0.27";
    private final String DB_HOST_3 = "10.10.0.28";
    private final int DB_PORT = 27017;
    private final String DB_USER = "platform";
    private final String DB_SOURCE = "test03-platform";
    private final String DB_PASSWORD = "";
    private Datastore datastore;

    private Client  mongoClient;



    public Datastore connectToMongo() {

        MongoCredential credential = MongoCredential.createScramSha1Credential(DB_USER, DB_SOURCE, DB_PASSWORD.toCharArray());
        Morphia morphia = new Morphia();
        MongoClient mongoClient = new MongoClient(Arrays.asList(
                new ServerAddress(DB_HOST_1, DB_PORT),
                new ServerAddress(DB_HOST_2, DB_PORT),
                new ServerAddress(DB_HOST_3, DB_PORT)),
                Arrays.asList(credential));


        datastore = morphia
                .mapPackage("main.java.client.java")
                .map(Client.class)
                .createDatastore(mongoClient, DB_SOURCE);
        return datastore;

    }
        public Client getClientFromMongoById(String clientId) {
            APIClient apiClient = new APIClient();

            //System.out.println(datastore.getCount(Client.class));
            //Client client = datastore.find(Client.class).field("_id").equal("5866fd1c29be752ef7808fc3").get();
            Client clientFromMongo = datastore.find(Client.class).field("_id").equal(clientId).get();

            //System.out.println(clientFromMongo.getName());
            //Assert.assertEquals(client.getName(), "Kino-mo Minsk");
            //Assert.assertEquals(client.getLegalName(), "Kino-mo Ltd.");

            return clientFromMongo;
        }

    }

