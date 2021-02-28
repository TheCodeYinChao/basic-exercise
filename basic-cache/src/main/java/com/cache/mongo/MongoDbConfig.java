package com.cache.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * dsc: MongoDbConfig
 * date: 2021/2/25 17:10
 * author: zyc
 */
public class MongoDbConfig {
    @Test
    public void connect(){
        try {
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
            //ServerAddress()两个参数分别为 服务器地址 和 端口
//            ServerAddress serverAddress = new ServerAddress("192.168.11.11",27017);
            ServerAddress serverAddress = new ServerAddress("192.168.196.3",27017);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress);

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称（admin） 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential("admin", "admin", "123456".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);

            //通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs,credentials);


            //连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test_database");
            MongoCollection<Document> collection = mongoDatabase.getCollection("test_table");

            Document document = new Document();
            document.append("aa","b");
//            document.append("_id","1");
            collection.insertOne(document);
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * 注意 账号的创建 与数据库连接的问题，
     *
     * 下面 我直接mongodb://admin:123456@192.168.196.3:27017/test_database 会显示认证失败
     * 因为我admin账号是在admin库的超级管理员账号
     */
    @Test
    public void connect1(){
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://admin:123456@192.168.196.3:27017");
            //通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(uri);

            //连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test_database");
            MongoCollection<Document> collection = mongoDatabase.getCollection("test_table");

            Document document = new Document();
            document.append("aa","b");
//            document.append("_id","1");
            collection.insertOne(document);
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
