package xws.SOAP.endpoints;

import com.baeldung.springsoap.gen.CommentRequestId;
import com.baeldung.springsoap.gen.GradeRequestId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.baeldung.springsoap.gen.Comment;
import xws.repository.CommentRepository;

import javax.transaction.Transactional;

@Endpoint
@Transactional
public class GetCommentEndpoint {
    @Autowired
    private CommentRepository commentRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "commentRequestId")
    @ResponsePayload
    public Comment getComment(@RequestPayload CommentRequestId request) {
        Comment response = new Comment();
        xws.model.Comment cg = commentRepository.getOne(new Long(request.getId()));
        response.setCarId(cg.getCarId());
        response.setId(cg.getId());
        response.setUserId(cg.getUserId());
        response.setText(cg.getText());
        return response;
    }
}
