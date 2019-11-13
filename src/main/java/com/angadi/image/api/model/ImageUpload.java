package com.angadi.image.api.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
public class ImageUpload {
	private String vendorID;
	private String vendorName;
	private Optional<BigDecimal> price;
	private String categoryId;
	private List<String> subCategoryId;
	///private ImageMetaData imageMetaData;
}
