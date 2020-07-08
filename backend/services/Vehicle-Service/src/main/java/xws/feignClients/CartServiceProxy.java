package xws.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xws.dto.request.NewVehicleDTO;

@FeignClient(name="cart")
public interface CartServiceProxy {
    @RequestMapping(method = RequestMethod.POST,value="vehicle/new")
    public ResponseEntity<?> createNewVehicle(@RequestBody NewVehicleDTO request);
}
