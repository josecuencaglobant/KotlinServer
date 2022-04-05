package com.sim.sample.database.repository

import com.sim.sample.data.Station
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StationsRepository: CrudRepository<Station,Long> {
}