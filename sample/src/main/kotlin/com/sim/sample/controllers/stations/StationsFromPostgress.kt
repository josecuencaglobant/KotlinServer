package com.sim.sample.controllers.stations

import com.sim.sample.data.Station
import com.sim.sample.database.repository.ObjectAdapter
import com.sim.sample.database.repository.StationsRepositoryPostgress
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StationsFromPostgress: ObjectAdapter<Long, Station> {

    @Autowired
    private lateinit var repository: StationsRepositoryPostgress

    override fun findAll(): ArrayList<Station> {
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
        return resp
    }

    override fun findById(id: Long): Station? {
        return repository.findByIdOrNull(id)
    }

    override fun save(element: Station): Station {
        return repository.save(Station(name = element.name))
    }

    override fun update(element: Station): Station {
        return repository.save(element)
    }

    override fun delete(id: Long): Station? {
        var station = findById(id)
        if(station != null ){
            repository.deleteById(id)
        }
        return station
    }

}