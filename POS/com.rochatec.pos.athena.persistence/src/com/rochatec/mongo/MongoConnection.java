package com.rochatec.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.rochatec.mongo.file.FileUtils;

import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * Created by erick on 02/10/14.
 */
public class MongoConnection {

    private DB db;
    private MongoClient client;
    private DB activeDb;
    private static MongoConnection instance;

    public static synchronized MongoConnection getInstance(){
        ResourceBundle bundle = FileUtils.getMongoConfig();
        if (instance == null){
            String hostname = bundle.getString("hostname");
            String databaseName = bundle.getString("database");
            Integer port = Integer.parseInt(bundle.getObject("port").toString());
            instance = new MongoConnection(hostname,port.intValue());
            instance.setDb(databaseName);
        }
        return instance;
    }

    public static synchronized MongoConnection getInstance(ServerAddress serverAddress){
        if (instance == null){
            instance = new MongoConnection(serverAddress);
        }
        return instance;
    }

    public static synchronized MongoConnection getInstance(ServerAddress serverAddress,String databaseName){
        if (instance == null){
            instance = new MongoConnection(serverAddress);
            instance.setDb(databaseName);
        }
        return instance;
    }

    public static synchronized MongoConnection getInstance(String hostname,int port){
        if (instance == null){
            instance = new MongoConnection(hostname,port);
        }
        return instance;
    }

    public static synchronized MongoConnection getInstance(String hostname){
        return getInstance(hostname,27017);
    }

    public static synchronized MongoConnection getInstance(String hostname,int port,String databaseName){
        if (instance == null){
            instance = new MongoConnection(hostname,port);
            instance.setDb(databaseName);
        }
        return instance;
    }

    public static synchronized MongoConnection getInstance(String hostname,String databaseName){
        return getInstance(hostname,27017,databaseName);
    }


    private MongoConnection(ServerAddress serverAddress){
        client = new MongoClient(serverAddress);
    }

    private MongoConnection(String hostname,int port){
        try {
            ServerAddress serverAddress = new ServerAddress(hostname, port);
            client = new MongoClient(serverAddress);
        }catch (UnknownHostException ex){
            ex.printStackTrace();
        }
    }

    public DB getDb(){
        return this.activeDb;
    }

    public DB getDb(String name){
        this.db = this.activeDb;
        this.activeDb = client.getDB(name);
        return this.activeDb;
    }

    public void setDb(String databaseName){
        this.db = client.getDB(databaseName);
        this.activeDb = this.db;
    }

    public DB getLastDb(){
        return this.db;
    }



}
