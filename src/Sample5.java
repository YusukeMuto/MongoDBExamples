import java.net.*;

import com.mongodb.*;

public class Sample5 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("ex1");
            DBCollection shohinCollection = db.getCollection("shohin");
            BasicDBObject qdoc = new BasicDBObject("shohin_id", "0009");
            BasicDBObject udoc = new BasicDBObject("$set", new BasicDBObject("hanbai_tanka", 300));
            shohinCollection.update(qdoc, udoc);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
