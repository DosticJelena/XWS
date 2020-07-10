package xws.rabbitmq;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import xws.dto.request.NewCartDTO;
import xws.dto.request.NewVehicleSagaDTO;
import xws.service.CartService;
import xws.service.VehicleService;

import javax.transaction.Transactional;


public class Reciver {
    Logger logger = LoggerFactory.getLogger(Reciver.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private VehicleService vehicleService;

    @RabbitListener(queues = "tut.rpc.requests")
    public boolean receive(String in) {
        try {
            NewCartDTO newCart = new Gson().fromJson(in,NewCartDTO.class);
            System.out.println(" [x] Received '" + newCart.getUserId() + "'");
            cartService.save(newCart.getUserId());
            return true;
        }catch (Exception e) {
            logger.error("Cannont make NewCartDTO from message");
            return false;
        }
    }
    @RabbitListener(queues = "tut.rpr.requests")
    public boolean recieve2(String in) {
        try {
            NewVehicleSagaDTO newVehicle = new Gson().fromJson(in,NewVehicleSagaDTO.class);
            System.out.println(" [x] Received '" + newVehicle.getOwnerId() + "'");
            vehicleService.save(newVehicle);
            return true;

        }catch (Exception e){
            logger.error("Can not save Vehicle in cart");
            return false;
        }
    }
}
