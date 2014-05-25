import java.io.*;
import java.net.*;

import com.mongodb.*;

public class JEx1_1_Hint {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("jex1");
            DBCollection shohinCollection = db.getCollection("shohin");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("shohin.csv"), "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] attrs = line.split(",");
                String shohin_id = attrs[0];
                String shohin_mei = attrs[1];
                String shohin_bunrui = attrs[2];
                int hanbai_tanka = Integer.parseInt(attrs[3]);
                // ドキュメントを生成してデータベースに追加
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
