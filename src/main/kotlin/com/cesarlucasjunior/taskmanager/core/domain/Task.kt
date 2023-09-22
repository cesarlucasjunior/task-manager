package com.cesarlucasjunior.taskmanager.core.domain

import kotlinx.datetime.*

data class Task(
    val description: String,
    val active: Boolean,
    val createdAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.of("America/Sao_Paulo"))
)
