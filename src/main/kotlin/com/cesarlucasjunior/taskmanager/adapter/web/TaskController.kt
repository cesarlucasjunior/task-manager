package com.cesarlucasjunior.taskmanager.adapter.web

import com.cesarlucasjunior.taskmanager.core.domain.Task
import com.cesarlucasjunior.taskmanager.core.domain.TaskRequest
import com.cesarlucasjunior.taskmanager.core.port.`in`.RegisterTaskInputPort
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/task")
class TaskController(private val registerTaskInputPort: RegisterTaskInputPort) {

    @PostMapping
    fun saveTask(@RequestBody taskRequest: TaskRequest):ResponseEntity<Task>{
        val task = Task(taskRequest.description)
        registerTaskInputPort.register(task)
        return ResponseEntity.ok(task)
    }
}