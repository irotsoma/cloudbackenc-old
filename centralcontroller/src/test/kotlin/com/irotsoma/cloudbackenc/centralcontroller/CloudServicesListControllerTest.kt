package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceUser
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.containsString
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.TestRestTemplate
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.context.annotation.Import
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
@Import(ObjectMapperConfiguration::class)
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
        //below is only valid when google drive plugin is installed in extensions folder
        assertThat(testValue.body, containsString("[{\"uuid\":\"1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375\",\"name\":\"Google Drive\"}]"))
    }
// TODO: Fix test:  not seeing ObjectMapperConfiguration
    //below is only valid when google drive plugin is installed in extensions folder
    /*@Test
    fun testLoginGoogleDrive(){
        val requestHeaders = HttpHeaders()
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        val httpEntity = HttpEntity<CloudServiceUser>(CloudServiceUser("test","test",null), requestHeaders)
        val testValue = template.postForEntity("http://localhost:$port/cloudservice/login/1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375", httpEntity, CloudServiceUser::class.java)
        assertThat(testValue.body, `is`(CloudServiceUser("test", null, "test login")))
    }
*/
}