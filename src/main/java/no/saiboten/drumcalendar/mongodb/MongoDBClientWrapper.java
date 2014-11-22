package no.saiboten.drumcalendar.mongodb;

import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;

@Component
public class MongoDBClientWrapper {

	private MongoClient mongoClient;

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public MongoDBClientWrapper() {

		try {
			this.mongoClient = new MongoClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
