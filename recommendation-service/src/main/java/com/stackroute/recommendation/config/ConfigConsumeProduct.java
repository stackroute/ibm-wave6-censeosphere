
package com.stackroute.recommendation.config;


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
public class ConfigConsumeProduct {

    //configuration to read product details from rabbitmq
    @Value("${stackroute.rabbitmq.queuesix}")
    String queueSix;

    @Value("${stackroute.rabbitmq.exchange}")
    String exchangeName;

    @Value("${stackroute.rabbitmq.routingkeysix}")
    private String routingkeySix;


    @Value("${stackroute.rabbitmq.queueseven}")
    String queueSeven;

    @Value("${stackroute.rabbitmq.routingkeyseven}")
    private String routingkeySeven;

    @Bean
    Queue queueMethod() {
        return new Queue(queueSix, true);

    }

    @Bean
    Queue queueMethod1() {
        return new Queue(queueSeven, true);

    }

    @Bean
    Exchange exchangeMethod() {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queueMethod()).to(exchangeMethod()).with(routingkeySix).noargs();
    }

    @Bean
    Binding binding1() {
        return BindingBuilder.bind(queueMethod1()).to(exchangeMethod()).with(routingkeySeven).noargs();
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


