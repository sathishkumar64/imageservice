package com.angadi.image.api.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.angadi.image.api.model.ImageUpload;
import com.angadi.image.api.model.LogoUpload;

public interface ImageUploadService {
	
	void logoUpload(LogoUpload logoUpload,MultipartFile file) throws IOException;

	void imageUpload(ImageUpload imageUpload,MultipartFile file) throws IOException;
	
	String imageBulkUpload(ImageUpload imageUpload,MultipartFile file);
	
	// Resource loadAsResource(String filename);

	//void createImageUpload(ImageUpload imageUpload);

//	void updateImageUpload(ImageUpload imageUpload);

	//void deleteImageUpload(String imageUploadId);

}
