package com.sim.sample.graphql

import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import com.sim.sample.controllers.stations.StationsFromPostgress
import com.sim.sample.data.Station
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

class StationGraphQl(val id: Int = -1, val name: String = ""){
    companion object{
        fun fromListStations(list: ArrayList<Station>): ArrayList<StationGraphQl>{
            val stations = ArrayList<StationGraphQl>()
            list.forEach {
                s -> stations.add(
                    StationGraphQl(s.id.toInt(),s.name)
                )
            }
            return stations
        }
        fun fromStation(station: Station?): StationGraphQl{
            station ?: return StationGraphQl()
            return StationGraphQl(station!!.id.toInt(), station!!.name)
        }
        fun toStation(stationGraphQl: StationGraphQl): Station{
            return Station(stationGraphQl.name,stationGraphQl.id.toLong())
        }
    }
}

//http://localhost:8080/graphql
/*
query Stations{
    findAllStations{
    id,name
  }
}
 */
@Component
class StationsQuery: Query {
    @Autowired
    lateinit var adapter: StationsFromPostgress
    fun findAllStations(): ArrayList<StationGraphQl> = StationGraphQl.fromListStations(adapter.findAll())
    fun findStationById(id: Int): StationGraphQl = StationGraphQl.fromStation(adapter.findById(id.toLong()))
}

@Component
class StationsMutator: Mutation{
    @Autowired
    lateinit var adapter: StationsFromPostgress
    fun saveStation(element: StationGraphQl): StationGraphQl
        = StationGraphQl.fromStation( adapter.save( StationGraphQl.toStation(element) ) )
    fun updateStation(element: StationGraphQl): StationGraphQl
            = StationGraphQl.fromStation( adapter.update( StationGraphQl.toStation(element) ) )
    fun deleteStation(id: Int): StationGraphQl = StationGraphQl.fromStation(adapter.delete(id.toLong()))
}