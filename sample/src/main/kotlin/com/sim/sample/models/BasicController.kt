package com.sim.sample.models

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

abstract class BasicController<OBJECT,ID>(
    private val basicCrud: BasicCrud<OBJECT,ID>
    ) {

    @ApiOperation("Get All Data")
    @GetMapping()
    fun findAll() = basicCrud.findAll()

    @ApiOperation("Get Data By Id")
    @GetMapping("/{id}")
    fun findById(@PathVariable id: ID) = basicCrud.findById(id)

    @ApiOperation("Save Element")
    @PostMapping
    fun save(@RequestBody element: OBJECT) = basicCrud.save(element)

    @ApiOperation("Updated Element")
    @PutMapping
    fun update(@RequestBody element: OBJECT) = basicCrud.update(element)

    @ApiOperation("Delete Element")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: ID) = basicCrud.delete(id)

}