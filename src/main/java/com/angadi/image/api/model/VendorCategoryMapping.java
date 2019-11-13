package com.angadi.image.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vendorcategorymap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorCategoryMapping extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String vendorID;
	private String imageURL;

}
