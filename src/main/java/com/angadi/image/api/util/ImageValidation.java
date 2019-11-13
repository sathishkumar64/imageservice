package com.angadi.image.api.util;

import javax.servlet.ServletException;

public class ImageValidation {

	
	 public void checkFileExtension(String fileName) throws ServletException {
		 
		 
         if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
             String[] allowedExt = {".jpg", ".jpeg", ".png"};
             for (String ext : allowedExt) {
                 if (fileName.endsWith(ext)) {
                     return;
                 }
             }
             throw new ServletException("file must be an image");
         }
     }
}
