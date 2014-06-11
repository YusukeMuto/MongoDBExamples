import java.text.*;
import java.util.*;

import com.mongodb.*;

public class LoadShardingSampleData {
	private static final String[] names = { "Cerny", "Hajduk", "Stump",
			"Thistle", "Mallen", "Ascher", "Abbott", "Line", "Chickering",
			"Auxier", "Schlabach", "Laber", "Spelman", "Hage", "Axel", "Mcvea",
			"Wymer", "Abdul", "Payton", "Fitzhenry", "Minjares", "Wallace",
			"Dayton", "Islas", "Rardin", "Isreal", "Seth", "Redmon",
			"Heidrick", "Sarabia", "Bilby", "Cheadle", "Griffey", "Desiderio",
			"Kram", "Jehle", "Santillanes", "Caskey", "Ezell", "Pilson",
			"Fuselier", "Iversen", "Whitford", "Branner", "Emily", "Bundick",
			"Plant", "Krebs", "Scranton", "Rosso", "Tester", "Flatley",
			"Greenburg", "Vasconcellos", "Straub", "Beer", "Leach", "Feely",
			"Mallett", "Raleigh", "Ruther", "Womble", "Sullen", "Serio",
			"Richert", "Kleiman", "Haines", "Ruelas", "Basile", "Iniguez",
			"Charles", "Pieper", "Thornberry", "Hawthorn", "Silcox",
			"Blackman", "Gildea", "Person", "Uresti", "Michalak", "Cabrales",
			"Bucklew", "Ruffin", "Hice", "Mccarroll", "Donovan", "Heppner",
			"Velasques", "Vena", "Statler", "Higginbottom", "Royal", "Stone",
			"Kissel", "Edgley", "Pickford", "Maglio", "Maiorano", "Kunz",
			"Cass", "Wilmes", "Allgood", "Singley", "Mouton", "Bella", "Schor",
			"Sano", "Owen", "Cordoba", "Pagano", "Whelpley", "Digman", "Loden",
			"Harms", "Mcconnaughey", "Newburn", "Giffin", "Holgate",
			"Villalon", "Dimeo", "Heras", "Monahan", "Tugwell", "Mok",
			"Bridge", "Hund", "Dray", "Buettner", "Lenard", "Morena", "Rizzo",
			"Presler", "Pilling", "Aho", "Louviere", "Strothers", "Lafond",
			"Mori", "Albury", "Session", "Ybarbo", "Rideout", "Kellerman",
			"Sanon", "Busse", "Sealy", "Ansley", "Netherland", "Eberly", "Ye",
			"Tessman", "Berwick", "Deveau", "Jayne", "Keniston", "Snavely",
			"An", "Livsey", "Ung", "Chan", "Donnelly", "Ferretti", "Keels",
			"Mccluney", "Molitor", "Wollard", "Mendoza", "Maclin", "Whitesell",
			"Weidemann", "Holliday", "Presley", "Segovia", "Lorence", "Minner",
			"Stradford", "Neubauer", "Mannella", "Overman", "Heal", "Grimmett",
			"Homan", "Pears", "Fernando", "Doucette", "Judon", "Ohlson",
			"Schor", "Papas", "Ziegler", "Maddix", "Blan", "Hash", "Edgerton",
			"Buskey", "Templeman", "Honore", "Dragoo", "Steffensen", "Sparling" };

	private static String data;

	private static void writeUserDocs(int iterations, int nameCount) {
		try {
			MongoClient mongoClient = new MongoClient("localhost", 40000);
			DB db = mongoClient.getDB("cloud-docs");
			DBCollection spreadsheetsCollection = db
					.getCollection("spreadsheets");

			for (int i = 0; i < iterations; i++) {
				for (int j = 0; j < nameCount; j++) {
					BasicDBObject doc = new BasicDBObject("filename", "sheet-"
							+ j).append("updated_at", getUTCTimeStamp())
							.append("username", names[j]).append("data", data);
					spreadsheetsCollection.insert(doc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void makeData() {
		for (int i = 0; i < 1000; i++) {
			data += "abcde";
		}
	}

	private static String getUTCTimeStamp() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(cal.getTimeZone());
		String timestamp = df.format(cal.getTime());
		return timestamp;
	}

	public static void main(String[] args) {
		int iterations = 0;
		int nameCount = 200;
		if (args.length == 0 || !args[0].matches("\\d+")) {
			System.out
					.println("Usage: java LoadShardingSampleData [iterations] [name_count]");
		} else {
			iterations = Integer.parseInt(args[0]);
			if (args.length == 2 && args[1].matches("\\d+")) {
				nameCount = Integer.parseInt(args[1]);
				if (200 < nameCount) {
					nameCount = 200;
				}
			}
			makeData();
			writeUserDocs(iterations, nameCount);
		}
	}
}
