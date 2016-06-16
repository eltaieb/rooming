package com.iti.rooming.websocket;

import java.util.Set;

import javax.websocket.Session;

import com.iti.rooming.common.dto.RoomingChatMessage;

public class Notifier extends Thread {
	private Set<Session> sessions;
	private RoomingChatMessage roomingChatMessage;

	public Notifier(Set<Session> sessions, RoomingChatMessage message) {
		this.sessions = sessions;
		this.roomingChatMessage = message;
	}

	@Override
	public void run() {
		for (final Session s : sessions) {
			final RoomingChatMessage thread = roomingChatMessage;
			new Thread() {
				public void run() {
					s.getAsyncRemote().sendObject(thread);
				}
			}.start();
		}
	}
}
