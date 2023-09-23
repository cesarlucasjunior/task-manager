package com.cesarlucasjunior.taskmanager.adapter.message

import com.cesarlucasjunior.taskmanager.adapter.conf.JmsConfig
import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.port.out.ProducerTaskOutputPort
import org.springframework.stereotype.Component

@Component
class ProducerTaskSqsAdapter(private val jmsConfig: JmsConfig): ProducerTaskOutputPort {

    override fun producer(task: Task): Task {
        jmsConfig.defaultJmsTemplate().convertAndSend(task.toString())
        println("Mensagem enviada com sucesso para a fila")
        return task
    }
}