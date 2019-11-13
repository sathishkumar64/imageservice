package com.angadi.image.api.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.angadi.image.api.model.ImageUpload;
import com.angadi.image.api.model.LogoUpload;
import com.angadi.image.api.service.ImageUploadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author satvasu
 *
 */

@Validated
@Api(value = "Image Service Controller")
@RestController
@RequestMapping("/api/angadi/v1/images")
@Slf4j
public class ImageUploadController {

	@Autowired
	private ImageUploadService imageUploadService;

	/**
	 * @param vendor
	 * @throws IOException
	 */
	@ApiOperation(value = "Adding a new Vendor Logo")
	@PostMapping(path = "/logo")
	@ResponseStatus(HttpStatus.CREATED)
	public void logoUpload(@RequestParam MultipartFile file, @Valid @RequestPart("logoUpload") LogoUpload logoUpload)
			throws IOException {
		log.info("Uploading Vendor Logo................");
		imageUploadService.logoUpload(logoUpload, file);
	}

	/**
	 * @param vendor
	 * @throws IOException
	 */
	@ApiOperation(value = "Adding a new Vendor Category Image")
	@PostMapping(path = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public void categoryUpload(@RequestParam MultipartFile file,
			@Valid @RequestPart("imageUpload") ImageUpload imageUpload) throws IOException {
		log.info("Uploading Vendor Categories................");
		imageUploadService.imageUpload(imageUpload, file);
	}

	/**
	 * @param vendor
	 * @throws IOException
	 */
	@ApiOperation(value = "Adding a new Vendor Category Image")
	@PostMapping(path = "/bulkupload")
	@ResponseStatus(HttpStatus.CREATED)
	public void categoryBulkUpload(@RequestParam MultipartFile[] files,
			@Valid @RequestPart("imageUpload") ImageUpload imageUpload) throws IOException {
		log.info("Uploading Vendor Bulk Categories................");
		Arrays.asList(files).forEach(file ->imageUploadService.imageBulkUpload(imageUpload, file));	
	}

}
