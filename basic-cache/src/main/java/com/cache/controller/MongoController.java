package com.cache.controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2020/4/4.
 */
@RestController
public class MongoController {

    @Autowired
    private MongoTemplate mongoTemplate;


    @GetMapping("/mongo")
    public Object mongo() {

        MongoCollection<Document> test_table = mongoTemplate.getCollection("test_table");
        Document document = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("by", "Fly");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        test_table.insertMany(documents);
        FindIterable<Document> documents1 = test_table.find();
        System.out.println(documents.get(0));
        return "ok";
    }
}
