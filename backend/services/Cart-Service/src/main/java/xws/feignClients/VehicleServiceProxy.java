package xws.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name="vehicle")
public interface VehicleServiceProxy {
    @RequestMapping(value = "/vehicles/test/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test(@PathVariable(value = "id") Long id);

}
