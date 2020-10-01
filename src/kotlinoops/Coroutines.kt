package kotlinoops


import kotlinx.coroutines.*
//Provide an aysnc mech.
//Light Weight Thread.
//When u launch a coroutines they are schedules on a thread.

//Some of the benefits coroutines offer within your app are callback-free, sequential code;
// structured concurrency (for example, run async operations in a scope); and built-in cancellation support.

fun main() {
    //non-blocking delay
    // coroutinesExample1()
    // coroutinesWithRunBlocking()
    //  multipleCoroutines()
    //waiting for a job
    //  waitingForAJobToFinish()
    //  runBlockingExample()

    //   var s: Int = runBlockingExample2(5)

    //  repeatCoroutinesBuilerExample()

    //computation Job
    // runCoroutinesOneAfterAnother()


  //  cancelSuspendedFuntionDemo1()

    //cooperativeSuspendFunction()

   // exceptionWithCancellableSuspendedFunction()

 //   exceptionWithCancellabeWithTimeOut()

    //coroutinesContext()
 //   unConfinedContextDemo()

   // accessingJobInsideCoroutine()

    //childParentCoroutines()
    combiningCoroutines()

}

fun combiningCoroutines() = runBlocking(){
   println("The coroutines context:${Thread.currentThread().name}")
    launch(Dispatchers.Default + CoroutineName("test")) {
        println("I'm working in thread ${Thread.currentThread().name}")
    }
}

fun childParentCoroutines() = runBlocking{

    val outer=launch {

        launch(coroutineContext) {
            repeat(10){
                println("Hi")
                delay(1)
            }
        }
    }
    delay(2000)
    outer.cancelAndJoin()
    println("I am completed")
}

fun accessingJobInsideCoroutine() = runBlocking(){
    val job =launch {
        if (coroutineContext[Job().key]!!.isActive)
         println("The Coroutine is active....")
    }

    job.join()
    println("The Coroutine is completed..")
}

fun unConfinedContextDemo() = runBlocking(){
    val jobs = arrayListOf<Job>()
    jobs+=launch(Dispatchers.Unconfined) {
        //this will run in the main thread
        println(" context will run in the mainThread:In thread ${Thread.currentThread().name}")
        delay(1000)
        println(" context :In thread seperate: ${Thread.currentThread().name}")

    }
    jobs+=launch() {
        //this will run in the main thread
        println(" context will run in the mainThread:In thread ${Thread.currentThread().name}")
        delay(1000)
        println(" context :in the mainThread:In thread: ${Thread.currentThread().name}")
    }
    jobs.forEach{it->it.join()}
}

fun coroutinesContext() = runBlocking{
    val jobs = arrayListOf<Job>()
    jobs+= launch(coroutineContext){
        //When launch { ... } is used without parameters, it inherits the context (and thus dispatcher)
        // from the CoroutineScope it is being launched from. In this case,
        // it inherits the context of the main runBlocking coroutine which runs in the main thread.
        //defualt contecxt... context of the parent, main runBlocking coroutine
        println("Default context :In thread ${Thread.currentThread().name}")
        printLoop()

    }
    //The default dispatcher that is used when coroutines are launched in GlobalScope is represented by Dispatchers.
    // Default and uses a shared background pool of threads, so launch(Dispatchers.Default) { ... } uses the same dispatcher as GlobalScope.launch { ... }.
    jobs+=launch(Dispatchers.Default) {
        println("Default dispatchers :I am working in the Pool Threads of working . ${Thread.currentThread().name}")
        printLoop()
    }

    jobs+=launch(newSingleThreadContext("The new SingleThread")) {
      //  newSingleThreadContext creates a thread for the coroutine to run. A dedicated thread is a very expensive resource.
        //  In a real application it must be either released, when no longer needed, using the close function,
        //  or stored in a top-level variable and reused throughout the application.
        println("This will be running in the seperate thread: ${Thread.currentThread().name}")
        printLoop()
    }
    jobs.forEach{it->it.join()}
}


fun exceptionWithCancellabeWithTimeOut()= runBlocking {
    try {
        val job = withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(100)
            }
        }

    }catch(e:TimeoutCancellationException){
        println("The TimeoutException is received ${e.message}")
    }finally {
        println("The Demo is completed in Finally. ")
    }
}

fun exceptionWithCancellableSuspendedFunction() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        }catch(ex:CancellationException){
          println("The Coroutine is cancelled ${ex.message}")
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(2300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel(CancellationException("Too many jobs I have cancelled it"))
    job.join()
    println("main: Now I can quit.")

}

fun cooperativeSuspendFunction() = runBlocking {
    //Through isActive ...
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // cancellable computation loop
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${System.currentTimeMillis().div(100000)} ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(100L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")

}

fun cancelSuspendedFuntionDemo1() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            yield()
            println("$i")
        }
    }
    delay(100)
    job.cancelAndJoin()
    println("demo")

}

//For cancelling the routines we have 2 option (build in suspeded funtion
//1-Through the yeild
//2-Cancel
fun runCoroutinesOneAfterAnother() = runBlocking {
    val job = GlobalScope.launch {
        printLoop()
    }
    delay(10)
    job.cancelAndJoin()
    // yield()
    println("The first job complted...")
    val job1 = GlobalScope.launch {
        printLoop()
    }
    delay(10)
    yield()
    println("The second job complted...")
}

fun cancelCooperativeJobs() = runBlocking() {
    val startTime = System.currentTimeMillis()

    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { //computation takes more time
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
//        while (isActive) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++} ...")
//                nextPrintTime += 500L
//            }
//        }
    }
    delay(1000L)

    println("Please cancel the task...")
    if (isActive) {
        job.cancelAndJoin()
    }

    println("I am done now..")


}


fun repeatCoroutinesBuilerExample() = runBlocking() {
    repeat(3) {
        delay(1000)
        launch { printLoop() }
    }
    delay(2000)
}

fun runBlockingExample2(s: Int): Int = runBlocking {
    var s1: Int = 0
    s1 = printTheVaalue(s)
    return@runBlocking s1
}

fun printTheVaalue(s: Int): Int {
    return s * s
}

fun runBlockingExample() = runBlocking {
    launch {
        println("The Loop is printing....")
        printLoop()
    }
    delay(2000)
    println("The Loop is printed.!!!")

}

fun waitingForAJobToFinish() {
    runBlocking {
        val job = launch {
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

