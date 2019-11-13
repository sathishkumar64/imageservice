package com.angadi.image.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.angadi.image.api.model.ImageDBData;

/**
 * @author satvasu
 *
 */
@Repository
public interface ImageUploaddao extends MongoRepository<ImageDBData, String> {

	//@Query("{ 'active' : ?0 }")
	//List<Vendor> findAllActive(boolean active);

	
	//@Aggregation(pipeline = { "{ $match : { active : true } }", "{ $count : total }" })
	//long countByActive();
}
