import java.io.*;
import java.net.*;

import org.bson.types.*;

import com.mongodb.*;

public class Sample8 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("images");
            DBCollection thumbnailCollection = db.getCollection("thumbnails");
            File thumbnail = new File("thumbnail.png");
            byte[] data = new byte[(int) thumbnail.length()];
            FileInputStream inputStream = new FileInputStream(thumbnail);
            inputStream.read(data);
            inputStream.close();
            BasicDBObject doc = new BasicDBObject("image", new Binary(data)).append("filename", "thumbnail.png");
            thumbnailCollection.insert(doc);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
