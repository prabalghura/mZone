package com.mzone.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mzone.model.Movie;
import com.mzone.repositories.MovieCustomRepository;

public class MovieRepositoryImpl implements MovieCustomRepository{

	@Value("${spring.data.mongodb.host}")
	private String hostName;
	
	@Value("${spring.data.mongodb.port}")
	private Integer hostPort;
	
	@Value("${spring.data.mongodb.database}")
	private String databaseName;
	
	@Override
	public List<String> getAllGenres() {
		/*UnwindOperation unwind = Aggregation.unwind("genres");
		ProjectionOperation project = Aggregation.project("genres");
		GroupOperation group = Aggregation.group().addToSet("genres").as("genres");
		Aggregation aggregation = Aggregation.newAggregation(unwind, project, group);
		System.out.println(aggregation);*/
		/*DBCollection col = mongoTemplate.getCollection("movies");
		col.aggregate(unwind);*/
		//sMovieGenre result = mongoTemplate.aggregate(aggregation, "movies", MovieGenre.class).getUniqueMappedResult();
		//return result.getGenres();
		MongoClient mongoClient = new MongoClient(hostName, hostPort);
    	MongoDatabase database = mongoClient.getDatabase(databaseName);
    	MongoCollection<Document> movies = database.getCollection(Movie.class.getAnnotation(org.springframework.data.mongodb.core.mapping.Document.class).collection());
    	
    	
    	BasicDBObject unwind = new BasicDBObject("$unwind", "$genres");
    	BasicDBObject project = new BasicDBObject("$project", new BasicDBObject("genres", 1));
    	BasicDBObject groupFields = new BasicDBObject("_id", 'N');
    	groupFields.put("genres", new BasicDBObject("$addToSet", "$genres"));
    	BasicDBObject group = new BasicDBObject("$group", groupFields);
    	List<BasicDBObject> aggregationList = new ArrayList<BasicDBObject>();
    	aggregationList.add(unwind);
    	aggregationList.add(project);
    	aggregationList.add(group);
    	
    	Document doc = movies.aggregate(aggregationList).first();
    	List<String> result = (List<String>) doc.get("genres");
    	mongoClient.close();
    	
		return result;
	}
}
