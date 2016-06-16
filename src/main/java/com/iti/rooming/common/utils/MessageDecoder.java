package com.iti.rooming.common.utils;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;
import com.iti.rooming.common.dto.RoomingChatMessage;

public class MessageDecoder implements Decoder.Text<RoomingChatMessage> {

	private Gson gson = new Gson();

	
	public void init(EndpointConfig config) {

	}

	public void destroy() {

	}

	public RoomingChatMessage decode(String s) throws DecodeException {
		return JsonUtil.jsonToObject(s, RoomingChatMessage.class);
	}

	public boolean willDecode(String s) {
		return (s != null);
	}

}
