package com.xakj.config;

import javax.websocket.server.ServerEndpointConfig;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 主要的配置类 本类必须要继承Configurator,因为@ServerEndpoint注解中的config属性只接收这个类型
 * 
 * @author 侯叶飞
 * 
 */
@Configuration
public class WebsocketConfig extends ServerEndpointConfig.Configurator {
	private static Logger log = Logger.getLogger(WebsocketConfig.class);

	/**
	 * ServerEndpointExporter 作用
	 * 
	 * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
	 * 
	 * @return
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}
