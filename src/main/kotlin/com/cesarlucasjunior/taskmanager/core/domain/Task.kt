package com.cesarlucasjunior.taskmanager.core.domain

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.datetime.*
data class Task(
    @JsonProperty("description") val description: String,
    @JsonProperty("active") val active: Boolean = true,
    @JsonProperty("createdAt") val createdAt: String  = Clock.System.now().toLocalDateTime(TimeZone.of("America/Sao_Paulo")).date.toString()
)