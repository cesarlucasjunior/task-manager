package com.cesarlucasjunior.taskmanager.core.port.out

import com.cesarlucasjunior.taskmanager.adapter.repository.model.TaskJpa
import com.cesarlucasjunior.taskmanager.core.domain.Task

interface SaveTaskOutputPort {
    fun save(task: Task): TaskJpa
}