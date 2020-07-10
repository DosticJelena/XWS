package xws.rabbitmq;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import xws.dto.request.NewCartDTO;
import xws.service.CartService;

import javax.transaction.Transactional;


public class Reciver {
    Logger logger = LoggerFactory.getLogger(Reciver.class);

    @Autowired
    private CartService cartService;

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
}
