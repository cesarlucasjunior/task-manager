package com.cesarlucasjunior.taskmanager.adapter.repository

import com.cesarlucasjunior.taskmanager.adapter.repository.jpa.TaskJpaRepository
import com.cesarlucasjunior.taskmanager.adapter.repository.model.TaskJpa
import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.port.out.SaveTaskOutputPort
import org.springframework.stereotype.Repository
import java.util.logging.Logger

@Repository
class TaskRepository(private val taskJpaRepository: TaskJpaRepository): SaveTaskOutputPort{

    private val log = Logger.getLogger(this.javaClass.name)
    override fun save(task: Task): TaskJpa {
        log.info("[TASK-MANAGER] - Sending task to database...")
        val taskJpa = TaskJpa(null, task.description, task.active, task.createdAt)
        return taskJpaRepository.save(taskJpa)
    }
}