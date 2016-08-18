package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceCallbackURL
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
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
    val testUUID = "f8bed9c2-c68b-4ab4-a66a-f16a6b46b768"

    @Test
    fun testAuthenticateCallback(){
        val requestHeaders = HttpHeaders()
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        val httpEntity = HttpEntity<CloudServiceCallbackURL>(CloudServiceCallbackURL(testUUID, "https://irotsoma.com"), requestHeaders)
        val returnValue = restTemplate.postForEntity("$protocol://localhost:$port/cloudservicecallback", httpEntity, Map::class.java)
        assert(returnValue.statusCode == HttpStatus.ACCEPTED)


}}