package com.sim.sample.models

interface BasicCrud<OBJECT,ID> {

    fun findAll(): ArrayList<OBJECT>
    fun findById(id: ID): OBJECT
    fun save(element: OBJECT): OBJECT
    fun update(element: OBJECT): OBJECT
    fun delete(id: ID): OBJECT
}