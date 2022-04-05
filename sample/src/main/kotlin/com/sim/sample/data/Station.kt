package com.sim.sample.data

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "stations")
class Station(var name: String,@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = -1){
    private constructor() : this("")
}