package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceUser
import org.hamcrest.Matchers.containsString
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.TestRestTemplate
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.web.client.RestTemplate

/**
 * Created by irotsoma on 7/13/2016.
 */

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(CentralController::class)
@WebIntegrationTest
open class CloudServicesListControllerTest {
    @Value("\${local.server.port}")
    private var port: Int = 0
    var template: RestTemplate = TestRestTemplate()

    //test that listing cloud services returns an HttpStatus.OK
    @Test
    fun testGetCloudServicesList(){
        val testValue = template.getForEntity("http://localhost:$port/cloudservices",String::class.java)
        assert(testValue.statusCode==HttpStatus.OK)
        //below is only valid when google drive plugin is installed in extensions folder
        assertThat(testValue.body, containsString("[{\"uuid\":\"1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375\",\"name\":\"Google Drive\",\"token\":\"\"}]"))
    }

    //below is only valid when google drive plugin is installed in extensions folder
    @Test
    fun testLoginGoogleDrive(){
        val requestHeaders = HttpHeaders()
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        val httpEntity = HttpEntity<CloudServiceUser>(CloudServiceUser("test","test",""), requestHeaders)
        // TODO: Fix test:  not seeing able to parse response to kotlin class CloudServiceUser.  Using map instead.  Kotlin class mapper is configured outside of test by ObjectMapperConfiguration, but doesn't seem to work here.
        val returnValue = template.postForEntity("http://localhost:$port/cloudservice/login/1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375", httpEntity, Map::class.java)
        //assertThat(returnValue.body, `is`(CloudServiceUser("test", "", "test login")))
        assert(returnValue.body["userId"]== "test")
        assert(returnValue.body["password"]== "")
        assert(returnValue.body["token"]== "test login")
    }

}