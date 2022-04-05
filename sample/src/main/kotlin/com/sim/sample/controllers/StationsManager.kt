package com.sim.sample.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/station")
class StationsManager {

    @ApiOperation(value = "Get All Stations")
    @GetMapping()
    fun findAll() = arrayListOf<String>("Station1","Station2")

    @ApiOperation(value = "Get a Station By Id")
    @GetMapping("/{id}")
    fun findById(@PathVariable id:String) = id

}