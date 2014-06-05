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
			DBCollection c1 = getBookCollection("localhost", 40000);
			c1.slaveOk(); // 非推奨
			DBCollection c2 = getBookCollection("localhost", 40001);
			System.out.println("Before S40000: ");
			showCollection(c1);
			System.out.println("Before P40001: ");
			showCollection(c2);
			System.out.println("Insert title: こころ into P40001");
			BasicDBObject doc = new BasicDBObject("title", "こころ");
			c2.insert(doc);
			Thread.sleep(5000);
			System.out.println("After S40000: ");
			showCollection(c1);
			System.out.println("After P40001: ");
			showCollection(c2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
