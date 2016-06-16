package com.iti.rooming.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUploader {
	
	public static String upload(String fileName,String destination , InputStream in) {
		try {

			String destinationOnServer = destination + fileName ;
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(destinationOnServer));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();
			
			System.out.println("COPY FILE FN >> NEW FILE CREATED");
			return destinationOnServer;
		} catch (IOException e) {
			return null;
		}
	}

}
