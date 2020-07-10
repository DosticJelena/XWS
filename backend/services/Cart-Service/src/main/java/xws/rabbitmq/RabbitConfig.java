package xws.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue queue() {
        return new Queue("tut.rpc.requests");
    }
    @Bean
    public Queue queue2() {
        return new Queue("tut.rpr.requests");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("tut.rpc");
    }

    @Bean
    public DirectExchange exchange2() {
        return new DirectExchange("tut.rpr");
    }

    @Bean
    public Binding binding(DirectExchange exchange,
                           Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("rpc");
    }
    @Bean
    public Binding binding2(DirectExchange exchange2,
                           Queue queue2) {
        return BindingBuilder.bind(queue2)
                .to(exchange2)
                .with("rpr");
    }

    @Bean
    public Reciver server() {
        return new Reciver();
    }
}
