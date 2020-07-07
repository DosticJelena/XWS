package xws.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name="cart")
public interface CartServiceProxy {

    @RequestMapping(value = "/",method = RequestMethod.POST,produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> addCart(Long id);

}
