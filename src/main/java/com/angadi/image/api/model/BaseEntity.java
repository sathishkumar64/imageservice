package com.angadi.image.api.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Document
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

	@Id
	private ObjectId id;

	

}
