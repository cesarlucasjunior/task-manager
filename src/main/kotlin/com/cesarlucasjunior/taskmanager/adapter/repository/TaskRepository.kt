package com.cesarlucasjunior.taskmanager.adapter.repository

import com.cesarlucasjunior.taskmanager.adapter.repository.jpa.TaskJpaRepository
import com.cesarlucasjunior.taskmanager.adapter.repository.model.TaskJpa
import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.port.out.SaveTaskOutputPort
import org.springframework.stereotype.Repository

@Repository
class TaskRepository(private val taskJpaRepository: TaskJpaRepository): SaveTaskOutputPort{
    override fun save(task: Task): TaskJpa {
        val taskJpa = TaskJpa(null, task.description, task.active, task.createdAt)
        return taskJpaRepository.save(taskJpa)
    }
}