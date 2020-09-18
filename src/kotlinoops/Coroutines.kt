package kotlinoops

import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*


fun main() {
    //non-blocking delay
    coroutinesExample1()
    coroutinesWithRunBlocking()
    multipleCoroutines()
    //waiting for a job
    waitingForAJobToFinish()

}

fun waitingForAJobToFinish() {
    runBlocking {
        val job = GlobalScope.launch {
            printLoop()
            delay(2000)
        }
        job.join()

        println("Hello from Coroutines1  Thread completed....")
        if (!job.isActive) {
            GlobalScope.launch {
                printLoop()
                delay(2000)
            }

        }
        println("Hello from Coroutines2  Thread completed....")
    }

    println("Hello from Main Thread....")


}

fun multipleCoroutines() {
    //Here runBlocking<Unit> { ... } works as an adaptor that is used to start the top-level main coroutine.
    // We explicitly specify its Unit return type, because a well-formed main function in Kotlin has to return Unit.

    runBlocking {
        // start main coroutine
        println("The 1 coroutine is running...")

        // launch a new coroutine in background and continue
        GlobalScope.launch { printLoop() }
        //That is because delay is a special suspending function that does not block a thread,
        // but suspends the coroutine, and it can be only used from a coroutine.
        delay(2000)
        println("The 2 coroutine is running...")
        GlobalScope.launch {
            printLoop()
        }
        delay(2000)
    }

}

fun coroutinesWithRunBlocking() {
    GlobalScope.launch {
        delay(1000)
        printLoop()
    }
    println("Hello from main Thread")
    runBlocking {
        delay(2000)
    }
}

fun printLoop() {
    val onToTen: IntRange = 1..10
    for (i: Int in onToTen) {
        println("The value of $i from the coroutines....")
    }
}

//essentially, coroutines are light-weight threads.
// They are launched with launch coroutine builder in a context of some CoroutineScope.
// Here we are launching a new coroutine in the GlobalScope,
// meaning that the lifetime of the new coroutine is limited only by the lifetime of the whole application.
fun coroutinesExample1() {
    GlobalScope.launch {
        // launch a new coroutine in background and continue
        delay(1000)// non-blocking delay for 1 second (default time unit is ms)
        printLoop()
    }
    Thread.sleep(2000)
    println("Hello from main Thread")
}
