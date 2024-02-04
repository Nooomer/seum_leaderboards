package com.nooomer.seum_leaderboards.db.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "leaderboard")
open class Leaderboard(
    @Column(name = "url")
    open var url: String,

    @Column(name = "lb_id", nullable = false)
    open var lbId: Int,

    @Column(name = "name")
    open var name: String,

    @Column(name = "display_name")
    open var displayName: String,

    @Column(name = "entries", nullable = false)
    open var entries: Int,

    @Column(name = "sort_method", nullable = false)
    open var sortMethod: Int,

    @Column(name = "display_type", nullable = false)
    open var displayType: Int,
) : Base() {

}