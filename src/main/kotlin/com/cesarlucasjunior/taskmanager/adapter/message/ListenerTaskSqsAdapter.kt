package com.cesarlucasjunior.taskmanager.adapter.message

import com.cesarlucasjunior.taskmanager.core.port.out.ListenTaskOutputPort
import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.jms.TextMessage
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@Component
class ListenerTaskSqsAdapter(): ListenTaskOutputPort {
    @JmsListener(destination = "\${QUEUE_NAME}")
    override fun listen(messageInQueue: TextMessage){

//        val taskInJson = messageInQueue.text
        val task: Task = ObjectMapper().readValue(messageInQueue.text, Task::class.java)
        println("Description - ${task.description}")
        messageInQueue.acknowledge()

    }
}