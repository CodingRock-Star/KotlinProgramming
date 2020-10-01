package kotlinoops

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main (){

  //  demoOfAsync()
  //  demoOfAsyncBlocking()
  //  demoOfSync()

    demoLazySyncDemo()
}
//Optionally, async can be made lazy by setting its start parameter to CoroutineStart.LAZY.
// In this mode it only starts the coroutine when its result is required by await, or if its Job's start function is invoked. Run the following example:
fun demoLazySyncDemo()= runBlocking() {
    val job =launch {
        val result =async(start = CoroutineStart.LAZY) {
            doWork("Work1")
        }
        println("The result of aysnc ${result.await()}")
        doWork("Work2")

    }

    job.join()

}

fun demoOfAsyncBlocking()= runBlocking() {
    val job =launch {
        val result =async(coroutineContext) {
            doWork("Work1")
        }
        println("The result of aysnc ${result.await()}")
        doWork("Work2")

    }

    job.join()
}


suspend fun doWork(msg:String):Int{
    log("$msg Work is in progress...")
    delay(500)
    log(" $msg Work done")
    return  23
}

fun log(msg:String){
    println("$msg in ${Thread.currentThread().name}")
}
//We use a normal sequential invocation, because the code in the coroutine, just like in the regular code, is sequential by default.
// The following example demonstrates it by measuring the total time it takes to execute both suspending functions:

fun demoOfSync() = runBlocking(){
    val job =launch {
        val time = measureTimeMillis {
            val r1 = doWorkOne()
            val r2 = doWorkTwo()
            println("The result of methods ${r1 + r2}")
        }
        println("The total time taken in Sync.....$time")
    }
    job.join()
}

 suspend fun doWorkTwo():Int {
     delay(100)
     return 13
}

suspend fun doWorkOne():Int {
    delay(100)
    return 1
}
//Conceptually, async is just like launch. It starts a separate coroutine which is a light-weight thread that works concurrently with all the other coroutines.
// The difference is that launch returns a Job and does not carry any resulting value, while async returns a Deferred — a light-weight non-blocking future that represents a promise to provide a result later.
// You can use .await() on a deferred value to get its eventual result, but Deferred is also a Job, so you can cancel it if needed.
fun demoOfAsync()= runBlocking {
    val job =launch {
        val time = measureTimeMillis {
            val r1= async {
                doWorkOne()
            }
            val r2=async {
                doWorkTwo()
            }
          println("The result of two aync tasks ${r1.await()+r2.await()}")
        }
        println("The total time taken in async $time")

    }

    job.join()
    println("The Job is completed async")

}

