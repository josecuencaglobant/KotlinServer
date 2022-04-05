package com.sim.sample.controllers.stations

import com.sim.sample.data.Station
import com.sim.sample.database.repository.StationsRepository
import com.sim.sample.models.BasicCrud
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class StationService: BasicCrud<Station,Long> {

    @Autowired
    lateinit var repository: StationsRepository

    override fun findAll(): ArrayList<Station> {
        val stations = repository.findAll()
        val resp = ArrayList<Station>()
       stations.forEach {
            station ->  resp.add(station)
        }
        return resp
    }

    override fun findById(id: Long): Station {
        val station = repository.findByIdOrNull(id)
        return station ?: Station("",-1)
    }

    override fun save(element: Station): Station {
        return repository.save(Station(name = element.name))
    }

    override fun update(element: Station): Station {
        return repository.save(element)
    }

    override fun delete(id: Long): Station {
        val station = findById(id)
        if(station.id != -1L ){
            repository.deleteById(id)
            return Station("delete",-1)
        }else{
            return Station("noExist",-1)
        }
    }


}