package com.iti.rooming.common.exception.handler;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.log4j.Logger;

public class RoomingExceptionHandlerWrapper extends ExceptionHandlerWrapper {
	private static final Logger logger = Logger
			.getLogger(RoomingExceptionHandlerWrapper.class);
	private ExceptionHandler wrapped;

	public RoomingExceptionHandlerWrapper(ExceptionHandler excpetion) {
		this.wrapped = excpetion;
	}

	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents()
				.iterator();
		while (iterator.hasNext()) {
			final FacesContext facesContext = FacesContext.getCurrentInstance();
			final Map<String, Object> requestMap = facesContext
					.getExternalContext().getRequestMap();
			final ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) facesContext
					.getApplication().getNavigationHandler();

			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();
			Throwable throwable = context.getException();
			try {
				throwable.printStackTrace();
				Throwable root = 	getRootCause(throwable);
				//TODO
				// check if room cause of rooming exception
//				extract message from it
//				get its value from resource bundle
//				display it
			} finally {
				iterator.remove();
			}
			getWrapped().handle();
		}
	};

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}
}