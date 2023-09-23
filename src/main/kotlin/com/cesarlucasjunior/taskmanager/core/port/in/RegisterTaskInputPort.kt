package com.cesarlucasjunior.taskmanager.core.port.`in`

import com.cesarlucasjunior.taskmanager.core.domain.Task

interface RegisterTaskInputPort {

    fun register(task: Task): Task
}