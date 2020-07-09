package xws.SOAP.endpoints;


import com.baeldung.springsoap.gen.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xws.service.VehicleService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import javax.transaction.Transactional;

@Endpoint
@Transactional
public class NewVehicle {

    @Autowired
    private VehicleService vehicleService;

    private static ModelMapper modelMapper = new ModelMapper();

    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "vehicle")
    @ResponsePayload
    public Vehicle newVehicle(@RequestPayload Vehicle v){
        Vehicle response = new Vehicle();
        xws.model.Vehicle newV = vehicleService.save(v);
        response.setBrand(v.getBrand());
        return response;
    }
}
