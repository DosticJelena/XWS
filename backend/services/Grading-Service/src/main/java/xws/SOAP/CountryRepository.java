package xws.SOAP;


import org.springframework.stereotype.Component;
import com.baeldung.springsoap.gen.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country c = new Country();
        c.setCapital("bg");
        c.setCurrency(Currency.EUR);
        c.setName("SPAIN");
        c.setPopulation(9000);
        countries.put(c.getName(),c);
    }

    public Country findCountry(String name) {
        return countries.get(name);
    }
}
