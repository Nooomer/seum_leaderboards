package com.nooomer.seum_leaderboards.db.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "player")
open class Player(
    @Column(name = "steamid")
    open var steamid: String? = null,

    @Column(name = "communityvisibilitystate")
    open var communityvisibilitystate: Int? = null,

    @Column(name = "profilestate")
    open var profilestate: Int? = null,

    @Column(name = "personaname")
    open var personaname: String? = null,

    @Column(name = "profileurl")
    open var profileurl: String? = null,

    @Column(name = "avatar")
    open var avatar: String? = null,

    @Column(name = "avatarmedium")
    open var avatarmedium: String? = null,

    @Column(name = "avatarfull")
    open var avatarfull: String? = null,

    @Column(name = "avatarhash")
    open var avatarhash: String? = null,

    @Column(name = "personastate")
    open var personastate: Int? = null,

    @Column(name = "realname")
    open var realname: String? = null,

    @Column(name = "primaryclanid")
    open var primaryclanid: String? = null,

    @Column(name = "timecreated")
    open var timecreated: Int? = null,

    @Column(name = "personastateflags")
    open var personastateflags: Int? = null,

    @Column(name = "loccountrycode")
    open var loccountrycode: String? = null,

    @Column(name = "locstatecode")
    open var locstatecode: String? = null,

    @Column(name = "loccityid")
    open var loccityid: Int? = null,
): Base()