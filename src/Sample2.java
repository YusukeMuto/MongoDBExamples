import java.net.*;

import com.mongodb.*;

public class Sample2 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("ex1");
            for (String cName : db.getCollectionNames()) {
                System.out.println(cName);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
