package com.sim.sample

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.sample.controllers.stations.StationService
import com.sim.sample.data.Station
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@TestMethodOrder(
	MethodOrderer.MethodName::class)
@SpringBootTest
class SampleApplicationTests {

	@Autowired
	private lateinit var webApplicationContext: WebApplicationContext

	private val mockMvc: MockMvc by lazy {
		MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print()).build()
	}

	private val station = Station("ZTest Created: ${Math.random()}")

	@Autowired
	private lateinit var objectMapper: ObjectMapper
	@Autowired
	private lateinit var stationService: StationService

	private val productEndPoint = "/api/v1/station"

	@Test
	fun a_findAll() {
		mockMvc.perform(MockMvcRequestBuilders.get(productEndPoint))
			.andExpect(status().isOk)
	}


	@Test
	fun b_findById(){
		val station = stationService.findAll().body?.first()
		mockMvc.perform(MockMvcRequestBuilders.get("$productEndPoint/${station?.id}"))
			.andExpect(status().isOk)
	}

	@Test
	fun c_notFindById(){
		mockMvc.perform(MockMvcRequestBuilders.get("$productEndPoint/${station?.id}"))
			.andExpect(status().isNoContent)
	}

	@Test
	fun d_save(){
     mockMvc.perform(
			MockMvcRequestBuilders.post(productEndPoint)
				.content(objectMapper.writeValueAsBytes(station))
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isCreated)
	}

	@Test
	fun e_update(){
		val station = stationService.findAll().body?.last()
		station?.name = "ZTest Updated: ${Math.random()}"
		mockMvc.perform(
			MockMvcRequestBuilders.put(productEndPoint)
				.content(objectMapper.writeValueAsBytes(station))
				.contentType(MediaType.APPLICATION_JSON)
		)
			.andExpect(status().isOk)
	}

	@Test
	fun f_delete(){
		val station = stationService.findAll().body?.last()
		mockMvc.perform(MockMvcRequestBuilders.delete("$productEndPoint/${station?.id}"))
			.andExpect(status().isOk)
	}

}
