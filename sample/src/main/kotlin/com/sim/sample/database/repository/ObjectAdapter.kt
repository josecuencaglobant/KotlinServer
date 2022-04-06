package com.sim.sample.database.repository

interface ObjectAdapter<ID,OBJECT> {

    fun findAll(): ArrayList<OBJECT>
    fun findById(id: ID): OBJECT?
    fun save(element: OBJECT): OBJECT
    fun update(element: OBJECT): OBJECT
    fun delete(id: ID): OBJECT?

}