import java.net.*;

import com.mongodb.*;

public class Sample4 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("ex1");
            DBCollection shohinCollection = db.getCollection("shohin");
            BasicDBObject doc = new BasicDBObject("shohin_id", "0009").append("shohin_mei", "ハサミ")
                    .append("shohin_bunrui", "事務用品").append("hanbai_tanka", 200);
            shohinCollection.insert(doc);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
