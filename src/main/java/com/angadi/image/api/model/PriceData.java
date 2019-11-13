package com.angadi.image.api.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceData {
	
	String VendorId;
	String ImageId;
	BigDecimal price;

}
