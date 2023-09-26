package com.cesarlucasjunior.taskmanager.core.port.out

import jakarta.jms.TextMessage

interface ListenTaskOutputPort {
    fun listen(messageInQueue: TextMessage)
}