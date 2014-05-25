import java.io.*;
import java.net.*;

import com.mongodb.*;

public class Sample9 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("images");
            DBCollection thumbnailCollection = db.getCollection("thumbnails");
            File thumbnail = new File("thumbnail2.png");

            BasicDBObject qdoc = new BasicDBObject("filename", "thumbnail.png");
            DBCursor cursor = thumbnailCollection.find(qdoc);
            while (cursor.hasNext()) {
                DBObject doc = cursor.next();
                byte[] data = (byte[]) doc.get("image");
                FileOutputStream outputStream = new FileOutputStream(thumbnail);
                outputStream.write(data);
                outputStream.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
