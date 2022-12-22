package com.codengine.reactive.infra

import com.codengine.reactive.service.BasicListener
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.MessageListenerContainer
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConsumerConfig(
    private val connectionFactory: ConnectionFactory,
    private val basicListener: BasicListener,
    private val simpleRabbitListenerConnectionFactory: SimpleRabbitListenerContainerFactory
) {

    @Bean
    fun listenerContainer(): MessageListenerContainer { //Implementação com basicListener
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames("SECOND-QUEUE-BASIC")
        container.setMessageListener(basicListener)
        simpleRabbitListenerConnectionFactory.adviceChain?.let {
            container.setAdviceChain(*it)
        }
        //container.start() //Se for usado fora de um Bean será necessário startar o container
        return container
    }
}