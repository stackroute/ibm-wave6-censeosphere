package com.stackroute.reviewerprofile.config;


import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigReviewConsume {

    @Value("${stackroute.rabbitmq.queuefive}")
    String queueFive;

    @Value("${stackroute.rabbitmq.exchange}")
    String exchangeName;

    @Value("${stackroute.rabbitmq.routingkeyfive}")
    private String routingkeyFive;


    @Bean
    org.springframework.amqp.core.Queue queueMethod() {
        return new org.springframework.amqp.core.Queue(queueFive, true);

    }

    @Bean
    Exchange exchangeMethod() {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    org.springframework.amqp.core.Binding binding() {
        return BindingBuilder.bind(queueMethod()).to(exchangeMethod()).with(routingkeyFive).noargs();
    }


}
