package com.cesarlucasjunior.taskmanager

import com.cesarlucasjunior.taskmanager.adapter.conf.JmsConfig
import com.cesarlucasjunior.taskmanager.adapter.conf.SqsConfiguration
import com.cesarlucasjunior.taskmanager.adapter.message.ProducerTaskSqsAdapter
import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.port.`in`.RegisterTaskInputPort
import com.cesarlucasjunior.taskmanager.core.port.out.ProducerTaskOutputPort
import com.cesarlucasjunior.taskmanager.core.usecase.RegisterTaskUseCase
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskManagerApplication

fun main(args: Array<String>) {
	runApplication<TaskManagerApplication>(*args)
}
