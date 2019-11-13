package com.angadi.image.api.exception;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomHttpErrorResponse {

	 private Integer status;
	    private String path;
	    private String errorMessage;
	    private String timeStamp;
	    private String trace;

	    public CustomHttpErrorResponse(int status, Map<String, Object> errorAttributes) {
	        this.setStatus(status);
	        this.setPath((String) errorAttributes.get("path"));
	        this.setErrorMessage((String) errorAttributes.get("message"));
	        this.setTimeStamp(errorAttributes.get("timestamp").toString());
	        this.setTrace((String) errorAttributes.get("trace"));
	    }

}
