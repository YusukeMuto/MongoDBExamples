import java.net.*;

import com.mongodb.*;
import com.mongodb.gridfs.*;

public class Sample13 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("images");
            GridFS gridFs = new GridFS(db);
            BasicDBObject qdoc = new BasicDBObject("filename", "canyon.jpg");
            gridFs.remove(qdoc);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
