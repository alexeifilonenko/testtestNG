import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import java.util.Arrays;


public class MongoConnector {
    private static final String DB_HOST_1 = "10.10.0.27";
    private static final String DB_HOST_2 = "10.10.0.27";
    private static final String DB_HOST_3 = "10.10.0.28";
    private static final int DB_PORT = 27017;
    private static final String DB_USER = "platform";
    private static final String DB_SOURCE = "test03-platform";
    private static final String DB_PASSWORD = "SBUhX8Kmpcr7T";
    private static Datastore datastore;

    public static Datastore connectToMongo() {

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

    public static Client getClientFromMongoById(String clientId) {
        Client clientFromMongo = datastore.find(Client.class)
                .field("_id")
                .equal(clientId)
                .get();
        return clientFromMongo;
    }

}

