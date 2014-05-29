import java.io.*;
import java.net.*;

import com.mongodb.*;

public class ImportMovieLens {

	private static void importUsers(DB db) {
		try {
			DBCollection userCollection = db.getCollection("users");
			BufferedReader reader = new BufferedReader(new FileReader(
					"ml-1m/users.dat"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] dataSet = line.split("::");
				int id = Integer.parseInt(dataSet[0]);
				String gender = dataSet[1];
				int age = Integer.parseInt(dataSet[2]);
				int occupation = Integer.parseInt(dataSet[3]);
				String zipCode = dataSet[4];
				// System.out.println(id + "," + gender + "," + age + ","
				// + occupation + "," + zipCode);
				BasicDBObject doc = new BasicDBObject("_id", id)
						.append("gender", gender).append("age", age)
						.append("occupation", occupation)
						.append("zip_code", zipCode);
				userCollection.insert(doc);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void importRatings(DB db) {
		try {
			DBCollection ratingCollection = db.getCollection("ratings");
			BufferedReader reader = new BufferedReader(new FileReader(
					"ml-1m/ratings.dat"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] dataSet = line.split("::");
				int userId = Integer.parseInt(dataSet[0]);
				int movieId = Integer.parseInt(dataSet[1]);
				int rating = Integer.parseInt(dataSet[2]);
				int timeStamp = Integer.parseInt(dataSet[3]);

				// System.out.println(userId + "," + movieId + "," + rating +
				// ","
				// + timeStamp);
				BasicDBObject doc = new BasicDBObject("user_id", userId)
						.append("movie_id", movieId).append("rating", rating)
						.append("time_stamp", timeStamp);
				ratingCollection.insert(doc);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void importMovies(DB db) {
		try {
			DBCollection movieCollection = db.getCollection("movies");
			BufferedReader reader = new BufferedReader(new FileReader(
					"ml-1m/movies.dat"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] dataSet = line.split("::");
				int id = Integer.parseInt(dataSet[0]);
				String title = dataSet[1];
				String[] genres = dataSet[2].split("\\|");

				// System.out.println(id + "," + title);
				BasicDBObject doc = new BasicDBObject("_id", id).append(
						"title", title).append("genres", genres);
				movieCollection.insert(doc);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("movieLens");
			importUsers(db);
			importRatings(db);
			importMovies(db);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
