package com.angadi.image.api.service;

import org.bson.types.ObjectId;

import com.angadi.image.api.model.ImageDBData;
import com.angadi.image.api.model.VendorCategoryMapping;

public interface VendorImageMappingService {
	
	void createImageMapping(ImageDBData imageDBData);
	
	String createVendorCategoryMap(VendorCategoryMapping vendorCategoryMapping);
}
