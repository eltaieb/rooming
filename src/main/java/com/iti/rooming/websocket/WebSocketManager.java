package com.iti.rooming.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.Session;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.dto.RoomingChatMessage;

@Singleton
public class WebSocketManager {
	private static Map<Long, Set<Session>> sockets;
	private static WebSocketManager webSocketManager;
	private static WebSocketThreadManager webSocketThreadManager;
	@EJB
	private RoomingManagment roomingManagment;

	@PostConstruct
	public void init() {
		sockets = new HashMap<>();
		initWebSocketThreadManager();

	}

	public synchronized static WebSocketManager createInstance()
			throws IOException {
		if (webSocketManager == null) {
			webSocketManager = new WebSocketManager();
		}
		return webSocketManager;
	}

	private void initWebSocketThreadManager() {
		webSocketThreadManager = WebSocketThreadManager.createInstance();
	}

	public void addSession(Long key, Session session) {
		checkSessionSet(key);
		Set<Session> sessions = sockets.get(key);
		sessions.add(session);
	}

	private void checkSessionSet(Long key) {
		Set<Session> sessions = sockets.get(key);
		if (sessions == null)
			sessions = new HashSet<>();
		sockets.put(key, sessions);
	}

	public void remove(Long key, Session session) {
		Set<Session> sessions = sockets.get(key);
	}

	public void notifySenderReciever(RoomingChatMessage roomingChatMessage) {
		presistInDB(roomingChatMessage);
		Long reciever = roomingChatMessage.getTo();
		Long sender = roomingChatMessage.getFrom();
		Set<Session> senderSessions = sockets.get(sender);
		Set<Session> recieverSessions = sockets.get(reciever);
		Notifier notifier = new Notifier(senderSessions, roomingChatMessage);
		webSocketThreadManager.execute(notifier);
		notifier = new Notifier(recieverSessions, roomingChatMessage);
		webSocketThreadManager.execute(notifier);
	}

	private void presistInDB(RoomingChatMessage roomingChatMessage) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		roomingManagment.saveChatMessage(roomingChatMessage);

	}

}
