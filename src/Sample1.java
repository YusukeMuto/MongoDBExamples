import java.net.*;
import java.util.*;

import com.mongodb.*;

public class Sample1 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            List<String> dbNames = mongoClient.getDatabaseNames();
            for (String dbName : dbNames) {
                System.out.println(dbName);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
