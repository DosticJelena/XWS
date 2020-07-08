package xws.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name="auth")
public interface UserServiceProxy {

    @RequestMapping(value = "/blocked/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    Boolean getBlocked(@PathVariable(value = "id") Long id);
}
