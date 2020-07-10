package xml;

import com.baeldung.springsoap.gen.Grade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import xml.SoapClients.GradeClient;
import xml.config.GradeConfig;

@SpringBootApplication
@EnableJpaRepositories
public class AgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(GradeConfig.class);
		GradeClient gc = annotationConfigApplicationContext.getBean(GradeClient.class);
		Grade g = gc.getGrade(1);
		System.out.println(g.getCarId() + " "+ g.getId()+" " + g.getUserId() + " " + g.getValue());
	}

}
