package com.iti.rooming.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.gson.Gson;

public class Utils {

	public static <T> T jsonStringToObject(String jsonString, Class<T> returned) {
		Gson gson = new Gson();
		T objectResult = gson.fromJson(jsonString, returned);
		return objectResult;
	}

	public static boolean isEmpty(Object obj) {
		return obj == null;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str) && str.trim().length() > 0;
	}

	public static boolean isNumericValue(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static boolean isNull(String string) {
		return isEmpty(string)|| string.trim().length()==0;
	}
	

	public static void copyFile(String folderPath , String fileName, InputStream in) {
		try {

			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(folderPath+File.separator
					+ fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean isNull(Object obj)
	{
		return obj ==null;
	}
	
	public static boolean isNotNull(Object obj)
	{
		return !isNull(obj);
	}
	
}
