package com.cesarlucasjunior.taskmanager.core.usecase

import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.port.out.ProducerTaskOutputPort
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

class RegisterTaskUseCaseTest {

    private val producerTaskOutputPort = mockk<ProducerTaskOutputPort>()
    private val registerTaskUseCase = mockk<RegisterTaskUseCase>()
    private val requestedTask = Task("Criar um novo teste")
    private val task = Task("Criar um novo teste", false)

    @Test
    fun `should register a task with success`() {

        every { registerTaskUseCase.register(requestedTask)
        } returns task

        every { producerTaskOutputPort.producer(requestedTask)
        } returns task

        val result = registerTaskUseCase.register(requestedTask)

        assertDoesNotThrow { registerTaskUseCase.register(requestedTask) }
        assert(task==result)
    }
}