package com.sim.sample

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@SpringBootTest
class SampleApplicationTests {

	@Autowired
	private lateinit var mockMvc: MockMvc
	@Autowired
	private lateinit var objectMapper: ObjectMapper

	private val productEndPoint = "/api/v1/station"

	@Test
	fun findAll() {
		//mockMvc.perform(MockMvcRequestBuilders)
	}

}
