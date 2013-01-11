package com.food.mongo;

import java.net.UnknownHostException;

import org.jongo.Jongo;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class JongoClient {
	
	private static Jongo jongo;

    private static final String SERVER_ADDRESS = "127.0.0.1";

    private static final int SERVER_PORT = 27017;

    private static final String DB_NAME = "mytest";

    static {
        try {
            Mongo mongo = new Mongo(SERVER_ADDRESS, SERVER_PORT);
            jongo = new Jongo(mongo.getDB(DB_NAME));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public static Jongo getInstance() {
        return jongo;
    }


}
