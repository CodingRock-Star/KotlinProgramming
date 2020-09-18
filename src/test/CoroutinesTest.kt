package test

import kotlinoops.coroutinesExample1
import kotlinoops.runBlockingExample2
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CoroutinesTest {

    @Test
    fun testCoroutines()= runBlocking(){
        Assert.assertEquals(25,runBlockingExample2(5))
    }

}