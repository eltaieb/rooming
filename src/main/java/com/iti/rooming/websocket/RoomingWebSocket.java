package com.iti.rooming.websocket;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.iti.rooming.common.dto.RoomingChatMessage;
import com.iti.rooming.common.utils.MessageDecoder;
import com.iti.rooming.common.utils.MessageEncoder;

@ServerEndpoint(value = "/chat/{key}", decoders = { MessageDecoder.class, }, encoders = { MessageEncoder.class })
@Singleton
public class RoomingWebSocket {

	@EJB
	private WebSocketManager webSocketManager;

	@OnMessage
	public String onMessage(RoomingChatMessage roomingChatMessage,
			Session session) {
		System.out.println("Received : " + roomingChatMessage.getMessage());
		webSocketManager.notifySenderReciever(roomingChatMessage);
		return "";
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("key") Long key) {
		System.out.println(key);
		webSocketManager.addSession(key, session);
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Closing a WebSocket due to ");
		// webSocketManager.remove(key, session);
	}
}
