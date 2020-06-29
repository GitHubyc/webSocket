package com.xakj.api;

import io.swagger.annotations.ApiOperation;

import java.io.IOException;

import javax.websocket.Session;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xakj.service.IRedisService;
import com.xakj.websocket.WebSocketServer;

@RestController
@RequestMapping("")
public class WebSocketController {
	@Autowired
	private WebSocketServer webSocketServer;
	@Autowired
	private IRedisService iRedisService;

	@ApiOperation(value = "发送所有", notes = "发送所有")
	@PostMapping("webSocketApi/sendAll")
	public Object sendAll(@RequestBody JSONObject jsonObject) {
		JSONObject obj = new JSONObject();
		obj.put("cmd", "topic");
		obj.put("msgId", "M0001");
		obj.put("msgTxt", jsonObject.toString());
		iRedisService.set("webSocket", jsonObject.toString());
		try {
			webSocketServer.sendAllMessage(obj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "群发！";
	}
}