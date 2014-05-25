import java.net.*;

import com.mongodb.*;

public class Sample3 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("ex1");
            DBCollection shohinCollection = db.getCollection("shohin");
            DBCursor cursor = shohinCollection.find();
            while (cursor.hasNext()) {
                DBObject doc = cursor.next();
                System.out.println(doc.get("shohin_mei") + ": " + doc.get("shohin_bunrui"));
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
