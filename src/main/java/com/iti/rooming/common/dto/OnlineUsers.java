package com.iti.rooming.common.dto;

import java.io.Serializable;

public class OnlineUsers implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Long nOfNumberUsers = 0L;

	public static void incrementNOfOnlineUsers() {
		nOfNumberUsers++;
	}

	public static void decrementNOfOnlineUsers() {
		nOfNumberUsers--;
	}

	public static Long getNOfOnlineUsers() {
		return nOfNumberUsers;
	}

}
