package xws.SOAP.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }
    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }

    @Bean XsdSchema gradesSchema() {return new SimpleXsdSchema(new ClassPathResource("grades.xsd"));}

    @Bean XsdSchema commentSchema() {return new SimpleXsdSchema(new ClassPathResource("comments.xsd"));}



    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition1(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.baeldung.com/springsoap/gen");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean(name = "grades")
    public DefaultWsdl11Definition defaultWsdl11Definition2(XsdSchema gradesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/grades");
        wsdl11Definition.setTargetNamespace("http://www.baeldung.com/springsoap/gen");
        wsdl11Definition.setSchema(gradesSchema);
        return wsdl11Definition;
    }
    @Bean(name = "comments")
    public DefaultWsdl11Definition defaultWsdl11Definition4(XsdSchema commentSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/comments");
        wsdl11Definition.setTargetNamespace("http://www.baeldung.com/springsoap/gen");
        wsdl11Definition.setSchema(commentSchema);
        return wsdl11Definition;
    }

    @Bean(name = "CreateGrades")
    public DefaultWsdl11Definition defaultWsdl11Definition3(XsdSchema gradesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/create");
        wsdl11Definition.setTargetNamespace("http://www.baeldung.com/springsoap/gen");
        wsdl11Definition.setSchema(gradesSchema);
        return wsdl11Definition;
    }

}
