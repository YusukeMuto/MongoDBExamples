import java.net.*;
import java.util.*;

import com.mongodb.*;

/**
 * 
 * 1秒ごとに保存された位置データについて，日吉駅から近い順に６００個のデータを取得し，
 * 60秒に1回位置データを出力するプログラム
 * 
 * @author t_morita
 *
 */
public class GeospatialQueryExample {
	public static void main(String[] args) {
		try {
			MongoClient mongoClient = new MongoClient("hostname", 27017);
			DB db = mongoClient.getDB("sp_data");
			DBCollection spdataCollection = db.getCollection("sample");
			List<Double> center = new ArrayList<Double>();
			center.add(35.553064);
			center.add(139.646783);
			DBCursor cursor = spdataCollection.find(new BasicDBObject("loc",
					new BasicDBObject("$near", center))).limit(600);
			int cnt = 0;
			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				DBObject loc = (DBObject) doc.get("loc");
				cnt += 1;
				if (cnt % 60 == 0) {
					System.out.println(loc.get("lat") + "," + loc.get("lng"));
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
