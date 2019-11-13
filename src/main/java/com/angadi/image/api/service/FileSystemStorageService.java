package com.angadi.image.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.angadi.image.api.exception.DataNotFoundException;
import com.angadi.image.api.helper.CloudStorageHelper;
import com.angadi.image.api.model.ImageDBData;
import com.angadi.image.api.model.ImageUpload;
import com.angadi.image.api.model.LogoUpload;
import com.angadi.image.api.model.VendorCategoryMapping;
import com.fasterxml.uuid.Generators;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileSystemStorageService implements ImageUploadService {

	@Autowired
	private CloudStorageHelper cloudStorageHelper;
	
	@Autowired
	private GRPCClientService grpcClientService;
	
	@Autowired
	private VendorImageMappingService vendorImageMappingService;

	@Value("${gcs-resource.storageName}")
	private String storageLocation;

	@Override
	public void logoUpload(LogoUpload logoUpload, MultipartFile file) throws IOException {
		StringBuffer stringBuffer = new StringBuffer();		
		String fileName = doValidation(file);
		String newFileName=genUUId(fileName);
		stringBuffer.append(logoUpload.getVendorID()).append("/").append(newFileName);
		InputStream is = file.getInputStream();
		String imageURI = cloudStorageHelper.uploadFile(is, storageLocation, stringBuffer.toString());
		vendorImageMappingService.createImageMapping(new ImageDBData(logoUpload.getVendorID(),imageURI));
		log.info("imageURL.............{}", imageURI);
	}

	@Override
	public void imageUpload(ImageUpload imageUpload, MultipartFile file) throws IOException {		
		String fileName = doValidation(file);
		String newFileName=genUUId(fileName);
		String buildURL=formUrl(imageUpload, newFileName);
		InputStream is = file.getInputStream();
		String imageURI = cloudStorageHelper.uploadFile(is, storageLocation, buildURL);
		
		String objId=createVendorCatImageMapping(imageUpload.getVendorID(), imageURI);
		
		log.info("objId objId................{}",objId);
		
		if(imageUpload.getPrice().isPresent()) {
			log.info("Inside price................");
			grpcClientService.callingGrpcServer(imageUpload.getVendorID(),objId.toString(),imageUpload.getPrice().get().floatValue());
		}else {
			log.error("No data found........");
		}
		log.info("imageURL.............{}", imageURI);

	}
	

	@Override
	public String imageBulkUpload(ImageUpload imageUpload, MultipartFile file) {
		String fileName = doValidation(file);
		String newFileName=genUUId(fileName);
		String buildURL=formUrl(imageUpload, newFileName);
		String imageURI = null;	
		try (InputStream is = file.getInputStream()){		
			imageURI = cloudStorageHelper.uploadFile(is, storageLocation, buildURL);
			createVendorCatImageMapping(imageUpload.getVendorID(), imageURI);
			log.info("imageURL.............{}", imageURI);
		} catch (IOException e) {
			log.error("Failed to store file " + imageURI, e);
		}
		return imageURI;
	}

	private String formUrl(ImageUpload imageUpload, String fileName) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(imageUpload.getVendorID()).append("/").append(imageUpload.getCategoryId());
		imageUpload.getSubCategoryId().forEach(i -> stringBuffer.append("/").append(i));
		stringBuffer.append("/").append(fileName);
		return stringBuffer.toString();
	}
	
	private String doValidation(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (file.isEmpty()) {
			throw new DataNotFoundException("Failed to store empty file " + fileName);
		}		
		return fileName;
	}
	
	private String genUUId(String oldFileName) {	
		UUID uuid=Generators.timeBasedGenerator().generate();
		String fileNameExt = FilenameUtils.getExtension(oldFileName);		
		String newFileName=uuid+"."+fileNameExt;
		log.info("fileNameWithExt wihout ext.............{}", newFileName);
		return newFileName;
	}
	
	private String createVendorCatImageMapping(String vendorId,String imageURI) {		
		return vendorImageMappingService.createVendorCategoryMap(new VendorCategoryMapping(vendorId,imageURI));		
	}

}
