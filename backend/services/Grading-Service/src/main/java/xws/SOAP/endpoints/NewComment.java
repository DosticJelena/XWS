package xws.SOAP.endpoints;

import com.baeldung.springsoap.gen.Comment;
import com.baeldung.springsoap.gen.CommentRequestId;
import com.baeldung.springsoap.gen.Grade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xws.model.CarGrade;
import xws.repository.CarGradeRepository;
import xws.repository.CommentRepository;
import xws.service.CarGradeService;
import xws.service.CommentService;

import javax.transaction.Transactional;


@Endpoint
@Transactional
public class NewComment {
    @Autowired
    private CommentService service;

    private static ModelMapper modelMapper = new ModelMapper();

    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "comment")
    @ResponsePayload
    public Comment getComment(@RequestPayload Comment request) {
        Comment response = new Comment();
        xws.model.Comment cg = service.save(request);
        response.setCarId(cg.getCarId());
        response.setId(cg.getId());
        response.setUserId(cg.getUserId());
        response.setText(cg.getText());
        return response;
    }
}
