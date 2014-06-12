import java.net.*;

import com.mongodb.*;

/**
 * 
 * 空間インデックスを張るために，SmaphoProbeから送られたJSONデータにlocフィールドを追加するプログラム
 * {lat: ..., lng ...} -> {loc: {lat: ..., lng: ...}} 
 * @author t_morita
 *
 */
public class AddLocFields {
	public static void main(String[] args) {
		try {
			MongoClient mongoClient = new MongoClient("hostname", 27017);
			DB db = mongoClient.getDB("sp_data");
			DBCollection spdataCollection = db.getCollection("sample");
			DBCursor cursor = spdataCollection.find();
			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				BasicDBObject loc = new BasicDBObject("lat", doc.get("lat"))
						.append("lng", doc.get("lng"));
				BasicDBObject q = new BasicDBObject("_id", doc.get("_id"));
				spdataCollection.update(q, new BasicDBObject("$set",
						new BasicDBObject("loc", loc)));
				//System.out.println(doc.get("lat") + ", " + doc.get("lng")
				//		+ ", " + doc.get("loc"));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
