import java.net.*;

import com.mongodb.*;

public class Sample14 {

	static DBCollection getBookCollection(String host, int portNum)
			throws UnknownHostException {
		MongoClient mc = new MongoClient(host, portNum);
		DB db = mc.getDB("bookstore");
		return db.getCollection("books");
	}

	static void showCollection(DBCollection c) {
		DBCursor cursor = c.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			System.out.println(doc.get("title"));
		}
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			DBCollection pc = getBookCollection("localhost", 40000);
			DBCollection sc = getBookCollection("localhost", 40001);
			sc.slaveOk(); // 非推奨 (Secondary Nodeに対して実行）
			System.out.println("Before Primary Node (40000): ");
			showCollection(pc);
			System.out.println("Before Secondary Node (40001): ");
			showCollection(sc);
			System.out.println("Insert title: こころ into Primary Node (40000)");
			BasicDBObject doc = new BasicDBObject("title", "こころ");
			pc.insert(doc);
			// Thread.sleep(5000);
			System.out.println("After Primary Node (40000): ");
			showCollection(pc);
			System.out.println("After Secondary Node (40001): ");
			showCollection(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
