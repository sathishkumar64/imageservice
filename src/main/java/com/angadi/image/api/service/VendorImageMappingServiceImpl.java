package com.angadi.image.api.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angadi.image.api.model.ImageDBData;
import com.angadi.image.api.model.VendorCategoryMapping;
import com.angadi.image.api.repositories.ImageUploaddao;
import com.angadi.image.api.repositories.VendorCatMappingDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VendorImageMappingServiceImpl implements VendorImageMappingService {

	@Autowired
	private ImageUploaddao imageUploaddao;
	
	@Autowired
	private VendorCatMappingDao vendorCatMappingDao;
	
	@Override
	public void createImageMapping(ImageDBData imageDBData) {
		log.info("Saving New Vendor Logo  Mapping......");		
		imageUploaddao.save(imageDBData);
	}

	@Override
	public String createVendorCategoryMap(VendorCategoryMapping vendorCategoryMapping) {
		log.info("Saving New Vendor & Category Mapping......");		
		vendorCatMappingDao.save(vendorCategoryMapping);
		String objId =vendorCategoryMapping.getId().toString();
		log.info("some sting..................."+objId);
		return objId;
	}

}
