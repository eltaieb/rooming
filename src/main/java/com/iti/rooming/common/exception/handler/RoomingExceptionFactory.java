package com.iti.rooming.common.exception.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class RoomingExceptionFactory extends ExceptionHandlerFactory{
	
	private ExceptionHandlerFactory parent;
	
	public RoomingExceptionFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}
	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler handler = new RoomingExceptionHandlerWrapper(parent.getExceptionHandler());
		return handler;
	}

}
