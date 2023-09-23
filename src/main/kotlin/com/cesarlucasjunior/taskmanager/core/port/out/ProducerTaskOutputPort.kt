package com.cesarlucasjunior.taskmanager.core.port.out

import com.cesarlucasjunior.taskmanager.core.domain.Task

interface ProducerTaskOutputPort {
    fun producer(task: Task): Task
}