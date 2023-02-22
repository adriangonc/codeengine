package com.codengine.reactive.config

import com.codengine.reactive.service.PersonListener
import org.aopalliance.aop.Advice
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.MessageListenerContainer
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConsumerConfig(
    private val connectionFactory: ConnectionFactory,
    //private val basicListener: BasicListener,
    private val personListener: PersonListener,
    private val simpleRabbitListenerConnectionFactory: SimpleRabbitListenerContainerFactory
) {

    @Bean
    fun listenerContainer(): MessageListenerContainer { //Implementacao com basicListener
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames("SECOND-QUEUE-ADVANCED")
        container.setMessageListener(personListener)
        simpleRabbitListenerConnectionFactory.adviceChain?.let {
            container.setAdviceChain(*it, retryPolicy())
        }
        //container.start() //Se for usado fora de um Bean sera necessario startar o container
        return container
    }

    private fun retryPolicy(): Advice {
        return RetryInterceptorBuilder
            .stateless()
            .maxAttempts(5)
            .backOffOptions(
                1000, //Initial interval
                2.0, // Multiplier
                10000 // Max interval
            )
            .recoverer(RejectAndDontRequeueRecoverer()) // Após as tetativas move mensagens com erro para a DLQ
            .build()
    }

}