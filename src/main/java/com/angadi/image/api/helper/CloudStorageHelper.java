package com.angadi.image.api.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CloudStorageHelper {

	private static Storage storage = null;


	static {
		storage = StorageOptions.getDefaultInstance().getService();
	}
	
	 public String uploadFile(InputStream  is,String storageLocation,String file) throws IOException { 
			log.info("Processing to upload...{} {} ",storageLocation,file);
		    storage.create(BlobInfo
			                .newBuilder(storageLocation, file)
			                // Modify access list to allow all users with link to read file
			                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
			                .build(),
			                IOUtils.toByteArray(is));
			 return formGCSURI(storageLocation,file);
			 
		 }
	 
	 //gs://k8s-log/sat64/Clothes/500/600/700/34d661c3-02e6-11ea-a247-4934233dc157.jpg
	 private String formGCSURI(String storageLocation,String file) {
		 StringBuffer stringBuffer = new StringBuffer();
		 stringBuffer.append("gs://").append(storageLocation).append("/").append(file);
		 return stringBuffer.toString();
	 }
	 
	
	 
}
