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
import java.util.logging.Logger

@Component
class ListenerTaskSqsAdapter(private val saveTaskOutputPort: SaveTaskOutputPort): ListenerTaskInputPort {

    private val log = Logger.getLogger(this.javaClass.name)
    @JmsListener(destination = "\${QUEUE_NAME}")
    override fun listen(messageInQueue: TextMessage) {
        try {
            log.info("[TASK-MANAGER] - Listening message in queue...")
            val task: Task = ObjectMapper().readValue(messageInQueue.text, Task::class.java)
            saveTaskOutputPort.save(task)
            log.info("[TASK-MANAGER] - Deleting message ${task.description}")
            messageInQueue.acknowledge()
            log.info("[TASK-MANAGER] - Listening completed.")
        } catch(exception: JmsException) {
            throw ListenerJmsException(exception.message)
        }
    }
}