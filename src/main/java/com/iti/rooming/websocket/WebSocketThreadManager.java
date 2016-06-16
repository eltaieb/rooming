package com.iti.rooming.websocket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.iti.rooming.common.config.Configurations;

public class WebSocketThreadManager {
	private static WebSocketThreadManager webSocketThreadManager;
	private final String MAX_POOL_SIZE = "max_pool_websocket_size";
	private ExecutorService excutorService;

	private WebSocketThreadManager()  {
		Integer maxSize = getMaxPoolSize();
		excutorService = Executors.newFixedThreadPool(maxSize);
	}

	public Integer getMaxPoolSize()  {
		Integer maxSize =Integer.parseInt(Configurations.getProperty(Configurations.MAX_WEBSOCKET_THREAD_POOL));
		return maxSize;
	}

	public void execute(Runnable runnable) {
		excutorService.execute(runnable);
	}

	public synchronized static WebSocketThreadManager createInstance()
			{
		if (webSocketThreadManager == null)
			webSocketThreadManager = new WebSocketThreadManager();
		return webSocketThreadManager;
	}
}
