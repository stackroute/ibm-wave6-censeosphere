package com.stackroute.userloginservice.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigConsumer {

    @Value("${stackroute.rabbitmq.queueone}")
    String queueOne;

    @Value("${stackroute.rabbitmq.exchange}")
    String exchangeName;

    @Value("${stackroute.rabbitmq.routingkeyone}")
    private String routingkeyOne;

    @Value("${stackroute.rabbitmq.queuetwo}")
    String queueTwo;

    @Value("${stackroute.rabbitmq.routingkeytwo}")
    private String routingkeyTwo;

    @Bean
    Queue queueMethodone() {
        return new Queue(queueOne, true);

    }
    @Bean
    Queue queueMethodtwo() {
        return new Queue(queueTwo, true);

    }

    @Bean
    Exchange exchangeMethod() {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queueMethodone()).to(exchangeMethod()).with(routingkeyOne).noargs();
    }


    @Bean
    Binding bindingTwo() {
        return BindingBuilder.bind(queueMethodtwo()).to(exchangeMethod()).with(routingkeyTwo).noargs();
    }




    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    ConnectionFactory connectionFactory(){
        //we want connection to be stable,so that we needn't close or open connection
        CachingConnectionFactory cachingConnectionFactory =new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
