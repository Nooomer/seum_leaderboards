package com.nooomer.seum_leaderboards.db.models

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
open class Base {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Column(name = "created_time")
    open var createdDate: LocalDateTime? = null

    @Column(name = "updated_time")
    open var updatedDate: LocalDateTime? = null

    @PreUpdate
    open fun onUpdate() {
        updatedDate = LocalDateTime.now()
    }

    @PrePersist
    open fun onCreate() {
        createdDate = LocalDateTime.now()
    }


}