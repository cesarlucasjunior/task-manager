package com.cesarlucasjunior.taskmanager.adapter.web

import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.domain.TaskRequest
import com.cesarlucasjunior.taskmanager.core.port.`in`.RegisterTaskInputPort
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.http.ResponseEntity

class TaskControllerTest {

    private val registerTaskInputPort = mockk<RegisterTaskInputPort>()
    private val taskController = mockk<TaskController>()
    private val responseEntity = mockk<ResponseEntity<Task>>()
    private val requestedTask = TaskRequest("Criando nova mensagem")
    private val task = Task("Criando nova mensagem", false)

    @Test
    fun `should send a request to create a message in queue`() {
        every {  registerTaskInputPort.register(task)
        } returns task

        every {  taskController.saveTask(requestedTask)
        } returns responseEntity
        val result = taskController.saveTask(requestedTask)

        assertDoesNotThrow { registerTaskInputPort.register(task) }
    }
}