package com.cesarlucasjunior.taskmanager.core.port.`in`

import jakarta.jms.TextMessage

interface ListenerTaskInputPort {
    fun listen(messageInQueue: TextMessage)
}