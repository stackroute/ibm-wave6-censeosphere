
package com.stackroute.NLPservice.config;


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
public class Configconsumedetail {


    @Value("${stackroute.rabbitmq.queuethree}")
    String queueThree;

    @Value("${stackroute.rabbitmq.exchange}")
    String exchangeName;

    @Value("${stackroute.rabbitmq.routingkeythree}")
    private String routingkeyThree;


    @Bean
    org.springframework.amqp.core.Queue queueMethod() {
        return new org.springframework.amqp.core.Queue(queueThree, true);

    }

    @Bean
    Exchange exchangeMethod() {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    org.springframework.amqp.core.Binding binding() {
        return BindingBuilder.bind(queueMethod()).to(exchangeMethod()).with(routingkeyThree).noargs();
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory(){
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


