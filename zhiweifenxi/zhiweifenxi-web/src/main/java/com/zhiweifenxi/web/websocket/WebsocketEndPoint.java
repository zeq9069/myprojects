package com.zhiweifenxi.web.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * websocket处理类，保存所有连接用户，对其发送消息
 * @author kyrin
 *
 */
public class WebsocketEndPoint extends TextWebSocketHandler {

	// 保存所有连接的用户
	private List<WebSocketSession> users = new ArrayList<WebSocketSession>();

	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		TextMessage returnMessage = new TextMessage(message.getPayload());
 		session.sendMessage(returnMessage);
		 
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		users.add(session);
		for(int i=0;i<=10000;i++){
			Thread.sleep(1000);
			for(WebSocketSession s:users){
				s.sendMessage(new TextMessage("服务器像所有已经连接的客户端推送的消息！"+ " to client:"+s.getId()));
			}
		}
	}

	@Override
	protected void handlePongMessage(WebSocketSession session,
			PongMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handlePongMessage(session, message);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}

}