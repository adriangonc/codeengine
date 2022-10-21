package com.codengine.reactive.controller

import com.codengine.reactive.ControllerTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


internal class PocControllerTest : ControllerTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var pocController : PocController

    @Test
    fun `flux API test`(){
        mockMvc.get("/flux/numbers")
            .andDo { print() }
            .andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `flux prime numbers test`(){
        mockMvc.get("/flux/numbers/primes/100")
            .andDo { print() }
            .andExpect { content { json("""
                [2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97]
            """.trimIndent()) } }
    }

}