package com.stackroute.productOwnerservice.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configproductconsume {

    @Value("${stackroute.rabbitmq.queuefour}")
    String queueFour;

    @Value("${stackroute.rabbitmq.exchange}")
    String exchangeName;

    @Value("${stackroute.rabbitmq.routingkeyfour}")
    private String routingkeyFour;

    @Value("${stackroute.rabbitmq.queuenine}")
    String queueNine;

    @Value("${stackroute.rabbitmq.routingkeynine}")
    private String routingkeyNine;

    @Bean
    org.springframework.amqp.core.Queue queueMethod() {
        return new org.springframework.amqp.core.Queue(queueFour, true);
    }

    @Bean
    org.springframework.amqp.core.Queue queueMethodTwo() {
        return new org.springframework.amqp.core.Queue(queueNine, true);
    }

    @Bean
    Exchange exchangeMethod() {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    org.springframework.amqp.core.Binding binding()
    {
        return BindingBuilder.bind(queueMethod()).to(exchangeMethod()).with(routingkeyFour).noargs();
    }

    @Bean
    org.springframework.amqp.core.Binding binding2()
    {
        return BindingBuilder.bind(queueMethodTwo()).to(exchangeMethod()).with(routingkeyNine).noargs();
    }
}
