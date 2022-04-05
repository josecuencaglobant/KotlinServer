package com.sim.sample.controllers.stations

import com.sim.sample.data.Station
import com.sim.sample.database.repository.StationsRepository
import com.sim.sample.models.BasicCrud
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class StationService: BasicCrud<Station,Long> {

    @Autowired
    lateinit var repository: StationsRepository

    override fun findAll(): ResponseEntity<ArrayList<Station>> {
        val stations = repository.findAll()
        val resp = ArrayList<Station>()
       stations.let {
           it.forEach {
                   station ->  resp.add(station)
           }
           resp.sortBy {
                   station -> station.name
           }
       }
        return ResponseEntity.status(
            if(stations != null) HttpStatus.OK else HttpStatus.CONFLICT
        ).body(resp)
    }

    override fun findById(id: Long): ResponseEntity<Station> {
        val station = repository.findByIdOrNull(id)
        return ResponseEntity.status(
            if(station != null) HttpStatus.OK else HttpStatus.NO_CONTENT
        ).body(station ?: Station(""))
    }

    override fun save(element: Station): ResponseEntity<Station> {
        val station = repository.save(Station(name = element.name))
        return ResponseEntity.status(
            if(station != null) HttpStatus.CREATED else HttpStatus.CONFLICT
        ).body(station ?: Station(""))
    }

    override fun update(element: Station): ResponseEntity<Station> {
        val station = repository.save(element)
        return ResponseEntity.status(
            if(station != null) HttpStatus.OK else HttpStatus.CONFLICT
        ).body(station ?: Station(""))
    }

    override fun delete(id: Long): ResponseEntity<Station> {
        var station = findById(id).body
        var httpStatus = HttpStatus.OK
        if(station?.id != -1L ){
            repository.deleteById(id)
        }else{
            httpStatus = HttpStatus.CONFLICT
        }
        return ResponseEntity.status(httpStatus)
            .body(station)
    }


}