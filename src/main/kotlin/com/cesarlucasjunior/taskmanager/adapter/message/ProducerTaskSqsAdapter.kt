package com.cesarlucasjunior.taskmanager.adapter.message

import com.cesarlucasjunior.taskmanager.adapter.conf.JmsConfig
import com.cesarlucasjunior.taskmanager.adapter.exception.ProducerMessageException
import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.port.out.ProducerTaskOutputPort
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.jms.JmsException
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class ProducerTaskSqsAdapter(private val jmsConfig: JmsConfig): ProducerTaskOutputPort {

    private val log = Logger.getLogger(this.javaClass.name)
    override fun producer(task: Task): Task {
        try {
            log.info("[TASK-MANAGER] - Sending a message to the queue...")
            jmsConfig.defaultJmsTemplate().convertAndSend(ObjectMapper().writeValueAsString(task))
            log.info("[TASK-MANAGER] - Message successfully sent to the queue - ${task.description}")
            return task
        } catch (error: JmsException) {
            throw ProducerMessageException(error.message)
        }
    }
}