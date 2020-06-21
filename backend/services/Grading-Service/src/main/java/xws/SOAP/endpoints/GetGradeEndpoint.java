package xws.SOAP.endpoints;


import com.baeldung.springsoap.gen.Grade;
import com.baeldung.springsoap.gen.GradeRequestId;
import org.modelmapper.ModelMapper;
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
public class GetGradeEndpoint {
    @Autowired
    private CarGradeService carGradeService;

    @Autowired
    private CarGradeRepository carGradeRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "gradeRequestId")
    @ResponsePayload
    public Grade getGrades(@RequestPayload GradeRequestId request) {
        Grade response = new Grade();
        CarGrade cg = carGradeRepository.getOne(new Long(request.getId()));
        response.setCarId(cg.getCarId());
        response.setId(cg.getId());
        response.setUserId(cg.getUserId());
        response.setValue(cg.getValue());
        return response;
    }
}
