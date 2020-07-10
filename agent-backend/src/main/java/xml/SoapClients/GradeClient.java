package xml.SoapClients;

import com.baeldung.springsoap.gen.GetGradeRequest;
import com.baeldung.springsoap.gen.GetGradeResponse;
import com.baeldung.springsoap.gen.Grade;
import com.baeldung.springsoap.gen.GradeRequestId;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class GradeClient extends WebServiceGatewaySupport{

    public Grade getGrade(int cid) {
        GradeRequestId getArticleRequest = new GradeRequestId();
        getArticleRequest.setId(cid);
        Grade g = (Grade)getWebServiceTemplate().marshalSendAndReceive(getArticleRequest);
        return g;
    }

}