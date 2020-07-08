package xml.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import xml.model.Vehicle;
import xml.repository.VehicleRepository;

import javax.inject.Singleton;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class WSocket {
    @Autowired
    VehicleRepository vehicleRepository;

    static List<Session> sessions = new ArrayList<Session>();


    @OnOpen
    public void onOpen(Session session) {
        if (!sessions.contains(session)) {
            sessions.add(session);
        }
    }
    @OnMessage
    public void echoTextMessage(String msg) {
        try {
            for (Session s : sessions) {
                System.out.println("WSEndPoint: " + msg);
                s.getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void close(Session session) {
        sessions.remove(session);
    }

    @OnError
    public void error(Session session, Throwable t) {
        sessions.remove(session);
        t.printStackTrace();
    }

}
