package com.cesarlucasjunior.taskmanager.adapter.repository.jpa

import com.cesarlucasjunior.taskmanager.adapter.repository.model.TaskJpa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskJpaRepository: JpaRepository<TaskJpa, Int> {
}