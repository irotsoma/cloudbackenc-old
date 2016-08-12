package com.irotsoma.cloudbackenc.cloudbackencui

import org.junit.runner.RunWith
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by irotsoma on 8/12/16.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
class CloudServiceCallbackControllerTest {
    @LocalServerPort
    private var port: Int = 0
    //TODO: add SSL test once implemented in controller
    var protocol: String = "http"
    var restTemplate = TestRestTemplate()

    /*@Test
    fun testAuthenticateCallback(){
        val requestHeaders = HttpHeaders()
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        val httpEntity = HttpEntity<CloudServiceCallbackURL>(CloudServiceCallbackURL("1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375", "https://irotsoma.com"), requestHeaders)
        val returnValue = restTemplate.postForEntity("$protocol://localhost:$port/cloudservicecallback", httpEntity, Map::class.java)

    }*/

}