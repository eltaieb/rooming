package com.iti.rooming.portal.managedbean;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownloadManagedBean {
    
   private StreamedContent file;
    
   @PostConstruct
   public void init() {  
	   try {
			InputStream stream;
			stream = new FileInputStream(new File("C:/Users/oracle/Desktop/images.jpg"));
			file = new DefaultStreamedContent(stream, "image/jpg", "thatsme.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

   public StreamedContent getFile() {
       return file;
   }
}