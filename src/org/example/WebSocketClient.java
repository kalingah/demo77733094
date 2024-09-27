

package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;

@Component
public class WebSocketClient extends TextWebSocketHandler {

    //added this comment and edited updated
    //added one more comment
    @PostConstruct
    public void connect() throws Exception {
        // Create the WebSocket client
        StandardWebSocketClient client = new StandardWebSocketClient();

        // Connect to the WebSocket server
        client.doHandshake(this, "ws://localhost:8080/ws");

        System.out.println("WebSocket client connected to ws://localhost:8080/ws");
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Client received: " + message.getPayload());
    }

    public void sendMessage(String message) throws Exception {
        // Connect to the WebSocket server
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketSession session = client.doHandshake(this, "ws://localhost:8080/ws").get();

        // Send message to the server
        session.sendMessage(new TextMessage(message));

        // Close session after sending the message
        session.close();
    }
}
