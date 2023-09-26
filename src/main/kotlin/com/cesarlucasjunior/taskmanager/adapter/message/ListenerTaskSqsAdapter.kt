package com.cesarlucasjunior.taskmanager.adapter.message

import com.cesarlucasjunior.taskmanager.adapter.exception.ListenerJmsException
import com.cesarlucasjunior.taskmanager.core.port.out.SaveTaskOutputPort
import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.port.`in`.ListenerTaskInputPort
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.jms.TextMessage
import org.springframework.jms.JmsException
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@Component
class ListenerTaskSqsAdapter(private val saveTaskOutputPort: SaveTaskOutputPort): ListenerTaskInputPort {
    @JmsListener(destination = "\${QUEUE_NAME}")
    override fun listen(messageInQueue: TextMessage) {
        try {
            val task: Task = ObjectMapper().readValue(messageInQueue.text, Task::class.java)
            println("Description - ${task.description}")
            saveTaskOutputPort.save(task)
            messageInQueue.acknowledge()
        } catch(exception: JmsException) {
            throw ListenerJmsException(exception.message)
        }
    }
}