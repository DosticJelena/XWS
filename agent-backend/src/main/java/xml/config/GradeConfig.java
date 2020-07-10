package xml.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import xml.SoapClients.GradeClient;

@Configuration
public class GradeConfig {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.baeldung.springsoap.gen");
        return jaxb2Marshaller;
    }

    @Bean
    public GradeClient articleClient(Jaxb2Marshaller jaxb2Marshaller) {
        GradeClient gc = new GradeClient();
        gc.setDefaultUri("http://localhost:8080/grading/ws/");
        gc.setMarshaller(jaxb2Marshaller);
        gc.setUnmarshaller(jaxb2Marshaller);
        return gc;
    }
}