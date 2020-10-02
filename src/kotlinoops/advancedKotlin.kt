package kotlinoops

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlin.system.measureTimeMillis

fun main (){

  //  demoOfAsync()
  //  demoOfAsyncBlocking()
  //  demoOfSync()

   // demoLazySyncDemo()
  //  demoBasicChannels()
   // demoProcuderAndConsumerWithChannel()

   // demoPipelining()

   // demoFanOut()
    demoFanIn()
}
//Multiple coroutines may send to the same channel.
// For example, let us have a channel of strings, and a suspending function that repeatedly
// sends a specified string to this channel with a specified delay:
//This demo is for Mutlple producer and single consumer
fun demoFanIn()=runBlocking {

    val channel=Channel<String>()
    launch {
        sendString(channel,"DHIRAJ",100L)
    }
    launch {
        sendString(channel,"Anumhea",100L)
    }

    repeat(5){
        println(channel.receive())
    }

    coroutineContext.cancelChildren()

}

suspend fun sendString(channed: Channel<String>,s:String,interval:Long ){
    while(true){
            delay(interval)
            channed.send(s)
        }
}

fun demoFanOut()= runBlocking {
    val producer = createProducerForFanOut()

    repeat(5) {
        //The benefit of FAN-OUT
        //Also, pay attention to how we explicitly iterate over channel with for loop to perform fan-out in launchProcessor code.
        // Unlike consumeEach, this for loop pattern is perfectly safe to use from multiple coroutines. If one of the processor coroutines fails,
        // then others would still be processing the channel,
        // while a processor that is written via consumeEach always consumes (cancels) the underlying channel on its normal or abnormal completion.
        createConsumerForFanOut(it, producer)
    }
    delay(900)
    producer.cancel()
}


@ExperimentalCoroutinesApi
fun CoroutineScope.createConsumerForFanOut(id: Int, producer: ReceiveChannel<Int>)=launch {
    producer.consumeEach {
        println("The Producer id  $id is being received on $it the with the Thread: ${Thread.currentThread().name}")
    }

}

fun CoroutineScope.createProducerForFanOut()=produce {
var x=1
    while(true){
        println("The Producer is procucing the values $x")
        send(x++)
        delay(100) //producing the item with a delay of 1 s
    }

}


fun demoPipelining()= runBlocking() {

    val producer=produceNumber()
    val consumer = nextLevelProducer(producer)

    for(i in 1..7){
        println(consumer.receive())
    }
    producer.cancel()
    consumer.cancel()

}
@ExperimentalCoroutinesApi
fun nextLevelProducer(i:ReceiveChannel<Int>)=GlobalScope.produce {
    for(x in i){
        println("The received to 2 level of production $x")
        send(x*x)
    }
}

@ExperimentalCoroutinesApi
fun produceNumber()= GlobalScope.produce {
    var x=1
    while (true){
        println("The Item send next Level production $x")
        send(x++)}
}

//There is a convenient coroutine builder named produce that makes it easy to do it right on producer side,
// and an extension function consumeEach, that replaces a for loop on the consumer side:
@ExperimentalCoroutinesApi
fun demoProcuderAndConsumerWithChannel()= runBlocking() {
    val producer=createChannelProcuder()
    producer.consumeEach {
        println("The Item recieved for consuption $it")
        println(it) }
    println("Consumption completed...")
}

@ExperimentalCoroutinesApi
fun createChannelProcuder()= GlobalScope.produce {
    for (x in 1..5){
        println("The Item send for production $x")
        send(x * x)}
    println("Production completed...")
}

//A Channel is conceptually very similar to BlockingQueue. One key difference is that instead of a blocking put operation it has a suspending send,
// and instead of a blocking take operation it has a suspending receive.
fun demoBasicChannels()= runBlocking() {
    val channel = Channel<Int>()
    val job =launch {
        for(i in 1.. 5){
            channel.send(i)
            println("The Send Value $i")
        }
        channel.close()
    }
  //below is one hardcoded received..
//repeat(4){
//    println("The received Value ${channel.receive()}")
//}

    //Below code will gracefull close the channel and return.
    for(y in channel){
        println("The received Value $y")
    }

    job.join()
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

