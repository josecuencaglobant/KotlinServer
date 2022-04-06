package com.sim.sample.controllers.stations

import com.sim.sample.data.Station
import com.sim.sample.database.repository.ObjectAdapter
import com.sim.sample.database.repository.StationsRepositoryPostgress
import com.sim.sample.models.BasicCrud
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class StationService: BasicCrud<Station,Long> {

    @Autowired
    lateinit var adapter: StationsFromPostgress

    override fun findAll(): ResponseEntity<ArrayList<Station>> {
        val stations = adapter.findAll()
        return ResponseEntity.status(
            if(stations.size > 0) HttpStatus.OK else HttpStatus.NO_CONTENT
        ).body(stations)
    }

    override fun findById(id: Long): ResponseEntity<Station> {
        val station = adapter.findById(id)
        return ResponseEntity.status(
            if(station != null) HttpStatus.OK else HttpStatus.NO_CONTENT
        ).body(station ?: Station(""))
    }

    override fun save(element: Station): ResponseEntity<Station> {
        val station = adapter.save(element)
        return ResponseEntity.status(
            if(station != null) HttpStatus.CREATED else HttpStatus.CONFLICT
        ).body(station ?: Station(""))
    }

    override fun update(element: Station): ResponseEntity<Station> {
        val station = adapter.update(element)
        return ResponseEntity.status(
            if(station != null) HttpStatus.OK else HttpStatus.CONFLICT
        ).body(station ?: Station(""))
    }

    override fun delete(id: Long): ResponseEntity<Station> {
        var station = adapter.delete(id)
        var httpStatus = HttpStatus.OK
        if(station != null){
            httpStatus = HttpStatus.CONFLICT
        }
        return ResponseEntity.status(httpStatus)
            .body(station)
    }


}