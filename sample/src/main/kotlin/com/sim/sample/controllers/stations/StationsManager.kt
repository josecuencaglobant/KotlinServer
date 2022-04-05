package com.sim.sample.controllers.stations

import com.sim.sample.data.Station
import com.sim.sample.models.BasicController
import com.sim.sample.models.BasicCrud
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/station")
class StationsManager(private val stationCrud: StationService)
    : BasicController<Station,Long>(stationCrud){

}