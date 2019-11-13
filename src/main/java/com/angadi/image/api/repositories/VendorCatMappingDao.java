package com.angadi.image.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.angadi.image.api.model.VendorCategoryMapping;

/**
 * @author satvasu
 *
 */
@Repository
public interface VendorCatMappingDao extends MongoRepository<VendorCategoryMapping, String> {


}
