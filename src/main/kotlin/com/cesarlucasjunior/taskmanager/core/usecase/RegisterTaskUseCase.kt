package com.cesarlucasjunior.taskmanager.core.usecase

import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.port.`in`.RegisterTaskInputPort
import com.cesarlucasjunior.taskmanager.core.port.out.ProducerTaskOutputPort
import org.springframework.stereotype.Component

@Component
class RegisterTaskUseCase(private val producerTaskOutputPort: ProducerTaskOutputPort): RegisterTaskInputPort {
    override fun register(task: Task): Task {
        return producerTaskOutputPort.producer(task)
    }
}