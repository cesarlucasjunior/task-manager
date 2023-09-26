package com.cesarlucasjunior.taskmanager.core.usecase


import com.cesarlucasjunior.taskmanager.core.port.`in`.ListenerTaskInputPort
import com.cesarlucasjunior.taskmanager.core.port.out.ListenTaskOutputPort
import jakarta.jms.TextMessage

import org.springframework.stereotype.Component

@Component
class ListenerTaskUseCase(private val listenTaskOutputPort: ListenTaskOutputPort): ListenerTaskInputPort {

    override fun listen(messageInQueue: TextMessage) {
        return listenTaskOutputPort.listen(messageInQueue)
    }
}