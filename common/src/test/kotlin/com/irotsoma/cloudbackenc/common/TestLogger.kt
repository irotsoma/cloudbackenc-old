package com.irotsoma.cloudbackenc.common

import org.junit.Test

/**
 * Created by irotsoma on 8/3/2016.
 *
 *
 */
class TestLogger {
    companion object{
        val testValueWithUnwrapper: String? = unwrapCompanionClass(this.javaClass).name
        val testValueWithoutUnwrapper: String? = this.javaClass.name
        val LOG by logger()
    }
    val expectedWithUnwrapper = "com.irotsoma.cloudbackenc.common.TestLogger"
    val expectedWithouUnwrapper = "com.irotsoma.cloudbackenc.common.TestLogger\$Companion"
    @Test
    fun TestCompanionUnwrapper(){
        //test that unwrapper returns class without the companion object
        assert(testValueWithUnwrapper == expectedWithUnwrapper)
        //negative test that without wrapper you do get the companion
        assert(testValueWithoutUnwrapper == expectedWithouUnwrapper)
        //test that logger object is created without errors and has the correct class name
        assert(LOG.name == expectedWithUnwrapper)
    }
}