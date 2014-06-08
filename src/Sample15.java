import java.util.*;

import com.mongodb.*;

public class Sample15 {
	public static void main(String[] args) throws InterruptedException {
		try {
			List<ServerAddress> seeds = new ArrayList<ServerAddress>();
			seeds.add(new ServerAddress("localhost", 40000));
			seeds.add(new ServerAddress("localhost", 40001));
			seeds.add(new ServerAddress("localhost", 40002));
			MongoClient mongoClient = new MongoClient(seeds);
			// WriteConcernとReadPreferenceについては以下を参照
			// http://docs.mongodb.org/ecosystem/drivers/java-replica-set-semantics/
			mongoClient.setWriteConcern(WriteConcern.UNACKNOWLEDGED); 
			System.out.println("Primary Node: " + mongoClient.getReplicaSetStatus().getMaster());
			mongoClient.setReadPreference(ReadPreference.primaryPreferred());	
			DB db = mongoClient.getDB("bookstore");
			DBCollection c = db.getCollection("books");
			DBCursor cursor = c.find();
			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				System.out.println("title: " + doc.get("title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
