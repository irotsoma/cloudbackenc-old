package com.irotsoma.cloudbackenc.centralcontroller

import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.containsString
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.TestRestTemplate
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.web.client.RestTemplate

/**
 * Created by irotsoma on 7/13/2016.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(CentralController::class)
@WebIntegrationTest
class CloudServicesListControllerTest {
    @Value("\${local.server.port}")
    private var port: Int = 0
    var template: RestTemplate = TestRestTemplate()
    var jsonUserRequest = "{\"userId\": \"testUser\", \"password\":\"testPassword\",\"token\":\"\"}"

    //test that listing cloud services returns an HttpStatus.OK
    @Test
    fun testGetCloudServicesList(){
        val testValue = template.getForEntity("http://localhost:$port/cloudservices",String::class.java)
        assertThat(testValue.statusCode, `is`(HttpStatus.OK))
        //below is only valid when google drive plugin is installed
        assertThat(testValue.body, containsString("[{\"uuid\":\"1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375\",\"serviceName\":\"Google Drive\"}]"))
    }

}