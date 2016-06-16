package com.iti.rooming.portal.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iti.rooming.common.config.Configurations;

public class DynamicImageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			String type = request.getParameter("type");
			String path = "", file = "";
			if (type == null)
				return;
			// 2->facility, 3-> onwer, 4->room

			if (type.equals("1")) {
				file = request.getParameter("file");
				path = Configurations
						.getProperty(Configurations.VERIFICATION_PATH);

			}else if(type.equals("2")){
				file = request.getParameter("file");
				path = Configurations.getProperty(Configurations.ROOM_PATH);
				
			}
			else if (type.equals("3")) {
				file = request.getParameter("file");
				path = Configurations
						.getProperty(Configurations.FACILITY_IMAGES_PATH);

			} else if (type.equals("4")) {
				file = request.getParameter("file");
				path = Configurations
						.getProperty(Configurations.OWNER_IMAGES_PATH);

			} else if (type.equals("5")) {
				file = request.getParameter("file");
				path = Configurations
						.getProperty(Configurations.PROFILE_IMAGE);

			} else if(type.equals("6")){
				file = request.getParameter("file");
				path = Configurations
						.getProperty(Configurations.UPLOADED_FACILITY_IMAGES_PATH);
			} else if(type.equals("7")){
				file = request.getParameter("file");
				path = Configurations
						.getProperty(Configurations.UPLOADED_ROOM_IMAGES_PATH);
			}
			
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(path + "\\" + file));
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			in.close();
			response.getOutputStream().write(bytes);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}