package xws.SOAP.endpoints;

import com.baeldung.springsoap.gen.Grade;
import com.baeldung.springsoap.gen.GradeRequestId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xws.model.CarGrade;
import xws.repository.CarGradeRepository;
import xws.service.CarGradeService;

import javax.transaction.Transactional;

@Endpoint
@Transactional
public class NewCarGrade {
    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";
    @Autowired
    private CarGradeService carGradeService;
    @Autowired
    private CarGradeRepository carGradeRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "grade")
    @ResponsePayload
    public Grade getGrades(@RequestPayload Grade request) {
        Grade response = new Grade();
        CarGrade cg = carGradeService.save(request);
        response.setCarId(cg.getCarId());
        response.setId(cg.getId());
        response.setUserId(cg.getUserId());
        response.setValue(cg.getValue());
        return response;
    }
}
