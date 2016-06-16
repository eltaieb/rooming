package com.iti.rooming.common.utils;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.iti.rooming.common.dto.RoomingChatMessage;

public class MessageEncoder implements Encoder.Text<RoomingChatMessage> {

	public void init(EndpointConfig config) {

	}

	public void destroy() {

	}

	public String encode(RoomingChatMessage object) throws EncodeException {
		return JsonUtil.getJson(object);
	}

}
