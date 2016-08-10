package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceUser
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.test.context.junit4.SpringRunner
import java.io.IOException
import java.net.HttpURLConnection
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection

/**
 * Created by irotsoma on 7/13/2016.
 *
 * Tests for services list controllers.  Assumes Google Drive extension is installed as noted in comments.
 */

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
open class CloudServicesListControllerTest {
    //@Value("\${local.server.port}")
    //private var port: Int = 0
    @Value("\${server.ssl.key-store}")
    private var useSSL: String? = null
    var protocol: String = "http"
    @Autowired
    var restTemplate = TestRestTemplate()

    //determine if we're using ssl and if so trust self signed certificates for testing.
    fun configureProtocol(){
        if (useSSL!=null && useSSL!="") {
            protocol= "https"
            trustSelfSignedSSL()
        } else {
            protocol = "http"
        }
    }
    //test that listing cloud services returns an HttpStatus.OK
    @Test
    fun testGetCloudServicesList(){
        //configureProtocol()
        val testValue = restTemplate.getForEntity("/cloudservices", String::class.java)
        assert(testValue.statusCode==HttpStatus.OK)
        //below is only valid when google drive plugin is installed in extensions folder
        //assertThat(testValue.body, containsString("[{\"uuid\":\"1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375\",\"name\":\"Google Drive\",\"token\":\"\"}]"))
    }

    //below is only valid when google drive plugin is installed in extensions folder
    @Test
    fun testLoginGoogleDrive(){
        configureProtocol()
        val requestHeaders = HttpHeaders()
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        val httpEntity = HttpEntity<CloudServiceUser>(CloudServiceUser("irotsoma","","1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375",CloudServiceUser.STATE.INITIALIZED,""), requestHeaders)
        // TODO: Fix test:  not seeing able to parse response to kotlin class CloudServiceUser.  Using map instead.  Kotlin class mapper is configured outside of test by ObjectMapperConfiguration, but doesn't seem to work here.
        val returnValue = restTemplate.postForEntity("$protocol://localhost/cloudservice/login/1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375", httpEntity, Map::class.java)
        //assertThat(returnValue.body, `is`(CloudServiceUser("test", "", "1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375", "test login")))
        assert(returnValue.body["userId"]== "test")
        assert(returnValue.body["password"]== "")
        assert(returnValue.body["serviceUUID"]=="1d3cb21f-5b88-4b3c-8cb8-1afddf1ff375")
        //assert(returnValue.body["token"]== "test login")
    }

    /**
     * Http Request Factory for ignoring SSL hostname errors. Not for production use!
     */
    class MySimpleClientHttpRequestFactory(private val verifier: HostnameVerifier) : SimpleClientHttpRequestFactory() {

        @Throws(IOException::class)
        override fun prepareConnection(connection: HttpURLConnection,
                                        httpMethod: String) {
            if (connection is HttpsURLConnection) {
                (connection).hostnameVerifier = this.verifier
            }
            super.prepareConnection(connection, httpMethod)
        }
    }
}