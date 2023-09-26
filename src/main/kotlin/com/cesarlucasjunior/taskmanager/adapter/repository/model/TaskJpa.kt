package com.cesarlucasjunior.taskmanager.adapter.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "TASKS")
class TaskJpa(
    @Id
    @GeneratedValue
    val id: Int? = null,
    val description: String,
    var active: Boolean = true,
    val createdAt: String
)