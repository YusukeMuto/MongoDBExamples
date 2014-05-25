import java.io.*;
import java.net.*;
import java.security.*;

import org.bson.types.*;

import com.mongodb.*;

public class Sample10 {
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

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data);
            byte[] digest = md.digest();
            BasicDBObject qdoc = new BasicDBObject("filename", "thumbnail.png");
            BasicDBObject doc = new BasicDBObject("$set", new BasicDBObject("md5", new Binary(digest)));
            thumbnailCollection.update(qdoc, doc);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
