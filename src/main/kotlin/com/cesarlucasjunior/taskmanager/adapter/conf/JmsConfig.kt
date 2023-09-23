package com.cesarlucasjunior.taskmanager.adapter.conf

import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import jakarta.annotation.PostConstruct
import jakarta.jms.Destination
import jakarta.jms.Session
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.destination.DestinationResolver
import org.springframework.jms.support.destination.DynamicDestinationResolver

@Configuration
@EnableJms
class JmsConfig (private var sqsConfiguration: SqsConfiguration) {

    private lateinit var connectionFactory: SQSConnectionFactory

    @Value("\${queue.name}")
    private lateinit var queueName: String


    @Bean
    fun init() {
        connectionFactory = SQSConnectionFactory(ProviderConfiguration(), sqsConfiguration.sqsClient())
    }

    @Bean
    fun jmsListenerContainerFactory(): DefaultJmsListenerContainerFactory {
        val jmsListener = DefaultJmsListenerContainerFactory()
        jmsListener.setConnectionFactory(this.connectionFactory)
        jmsListener.setDestinationResolver(DynamicDestinationResolver())
        jmsListener.setConcurrency("3-10")
        jmsListener.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE)
        return jmsListener
    }

    @Bean
    fun defaultJmsTemplate(): JmsTemplate {
        val jmsTemplate = JmsTemplate(this.connectionFactory)
        jmsTemplate.defaultDestinationName = queueName
        return jmsTemplate
    }
}