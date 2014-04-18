package br.edu.ifms.extensao.testemongodb.util;

import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class ConnectionUtil {

	private static final String HOST = "localhost";

	private static final int PORT = 27017;

	public static Mongo getConnection() throws UnknownHostException {
		return new MongoClient(HOST, PORT);
	}
}
