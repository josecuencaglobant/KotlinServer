package com.sim.sample.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/station")
class StationsManager {

    @GetMapping
    fun findAll() = arrayListOf<String>("Station1","Station2")

    @GetMapping("/{id}")
    fun findById(@PathVariable id:String) = id

}