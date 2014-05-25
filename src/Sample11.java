import java.io.*;
import java.net.*;

import com.mongodb.*;
import com.mongodb.gridfs.*;

public class Sample11 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("images");
            File file = new File("canyon.jpg");
            GridFS gridFs = new GridFS(db);
            GridFSInputFile gridFSInputFile = gridFs.createFile(file);
            gridFSInputFile.setFilename(file.getName());
            DBObject metadata = new BasicDBObject("test", "テスト");
            gridFSInputFile.setMetaData(metadata);
            gridFSInputFile.save();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
