package com.angadi.image.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageMetaData  {
	private String price;
	private String description;
	private String weight ;
	private String widhtHight ;	
}
