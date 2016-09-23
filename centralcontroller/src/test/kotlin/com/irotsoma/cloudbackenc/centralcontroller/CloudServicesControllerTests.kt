/*
 * Copyright (C) 2016  Irotsoma, LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.common.cloudservice.CloudServiceUser
import org.hamcrest.Matchers.containsString
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by irotsoma on 7/13/2016.
 *
 * Tests for services list controllers.  Assumes Google Drive extension is installed as noted in comments.
 */

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
open class CloudServicesControllerTests {
    @LocalServerPort
    private var port: Int = 0
    @Value("\${server.ssl.key-store}")
    private var useSSL: String? = null
    var protocol: String = "http"

    //test that listing cloud services returns an HttpStatus.OK
    @Test
    fun testGetCloudServicesList(){
        val restTemplate: TestRestTemplate
        if (useSSL!=null && useSSL!="") {
            protocol= "https"
            trustSelfSignedSSL()
            restTemplate = TestRestTemplate(TestRestTemplate.HttpClientOption.SSL)
        } else {
            protocol = "http"
            restTemplate = TestRestTemplate()
        }
        val testValue = restTemplate.getForEntity("$protocol://localhost:$port/cloudservices", String::class.java)
        assert(testValue.statusCode==HttpStatus.OK)
        //below is only valid when google drive plugin is installed in extensions folder
        assertThat(testValue.body, containsString("[{\"uuid\":\"1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375\",\"name\":\"Google Drive\",\"token\":\"\"}]"))
    }

    //below is only valid when google drive plugin is installed in extensions folder
    @Test
    fun testLoginGoogleDrive(){
        val restTemplate: TestRestTemplate
        if (useSSL!=null && useSSL!="") {
            protocol= "https"
            trustSelfSignedSSL()
            restTemplate = TestRestTemplate("test", "insecurepassword", TestRestTemplate.HttpClientOption.SSL)
        } else {
            protocol = "http"
            restTemplate = TestRestTemplate("test", "insecurepassword")
        }
        val requestHeaders = HttpHeaders()
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        val httpEntity = HttpEntity<CloudServiceUser>(CloudServiceUser("test","","1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375", CloudServiceUser.STATE.INITIALIZED,""), requestHeaders)
        // TODO: Fix test:  not seeing able to parse response to kotlin class CloudServiceUser.  Using map instead.  Kotlin class mapper is configured outside of test by ObjectMapperConfiguration, but doesn't seem to work here.
        val returnValue = restTemplate.postForEntity("$protocol://localhost:$port/cloudservice/login/1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375", httpEntity, Map::class.java)
        //assertThat(returnValue.body, `is`(CloudServiceUser("test", "", "1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375", "test login")))
        assert(returnValue.body["userId"]== "test")
        assert(returnValue.body["password"]== "")
        assert(returnValue.body["serviceUUID"]=="1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375")
        assert(returnValue.body["state"]== "LOGGED_IN")
    }

}