package com.cesarlucasjunior.taskmanager.adapter.message

import com.cesarlucasjunior.taskmanager.adapter.conf.JmsConfig
import com.cesarlucasjunior.taskmanager.adapter.exception.ProducerMessageException
import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.port.out.ProducerTaskOutputPort
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.jms.JmsException
import org.springframework.stereotype.Component

@Component
class ProducerTaskSqsAdapter(private val jmsConfig: JmsConfig): ProducerTaskOutputPort {

    override fun producer(task: Task): Task {
        try {
            jmsConfig.defaultJmsTemplate().convertAndSend(ObjectMapper().writeValueAsString(task))
            println("Mensagem enviada com sucesso para a fila")
            return task
        } catch (error: JmsException) {
            throw ProducerMessageException(error.message)
        }
    }
}