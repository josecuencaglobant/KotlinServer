package com.sim.sample.models

import io.swagger.models.Response
import org.springframework.http.ResponseEntity

interface BasicCrud<OBJECT,ID> {

    fun findAll(): ResponseEntity<ArrayList<OBJECT>>
    fun findById(id: ID): ResponseEntity<OBJECT>
    fun save(element: OBJECT): ResponseEntity<OBJECT>
    fun update(element: OBJECT): ResponseEntity<OBJECT>
    fun delete(id: ID): ResponseEntity<OBJECT>
}