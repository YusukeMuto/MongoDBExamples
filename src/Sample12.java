import java.io.*;
import java.net.*;

import com.mongodb.*;
import com.mongodb.gridfs.*;

public class Sample12 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("images");
            GridFS gridFs = new GridFS(db);
            BasicDBObject qdoc = new BasicDBObject("filename", "canyon.jpg");
            GridFSDBFile gfsFile = gridFs.findOne(qdoc);
            System.out.println("MD5: " + gfsFile.getMD5());
            System.out.println("メタデータ：" + gfsFile.getMetaData().get("test"));
            gfsFile.writeTo(new File("canyon-copy.jpg"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
