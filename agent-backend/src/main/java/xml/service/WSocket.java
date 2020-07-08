package xml.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import xml.model.Vehicle;
import xml.repository.VehicleRepository;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint("/ws")
public class WSocket {
    @Autowired
    VehicleRepository vehicleRepository;

    static List<Session> sessions = new ArrayList<Session>();

    @Scheduled(fixedRate = 1000)
    public void changeLocation() {
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        List<Vehicle> trackableVehicles = new ArrayList<>();
        for(Vehicle v : allVehicles){
            /*if(v.getTrackable()==1){
                String loc = v.getCoord();
                String loc1 = loc.split(",")[0];
                String loc2 = loc.split(",")[1];
                double loc1F = Float.parseFloat(loc1);
                double loc2F = Float.parseFloat(loc2);
                int min = -1;
                int max = 1;
                double random_double = Math.random() * (max - min + 1) + min;
                loc1F += random_double;
                loc2F += random_double;
                loc = String.valueOf(loc1F) + "," + String.valueOf(loc2F);
                v.setCoord(loc);
                trackableVehicles.add(v);
            }*/
        }
        ObjectMapper mapper = new ObjectMapper();
        String msg = null;
        try {
            msg = mapper.writeValueAsString(trackableVehicles);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.echoTextMessage(msg);
    }
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
