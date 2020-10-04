package kotlinoops

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() =run{
    //if we are computing the numbers with some CPU-consuming blocking code (each computation taking 100ms),
    // then we can represent the numbers using a Sequence:
    ///However, this computation blocks the main thread that is running the code
    simpleSyncCall().forEach { value->println(value) }
//Using the List<Int> result type, means we can only return all the values at once. To represent the stream of values that are being asynchronously computed,
// we can use a Flow<Int> type just like we would use the Sequence<Int> type for synchronously computed values:

    simplAsyncCallWithFlow()

}

fun simplAsyncCallWithFlow()= runBlocking{
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    //Notice the following differences in the code with the Flow from the earlier examples:
    //
    //    A builder function for Flow type is called flow.
    //    Code inside the flow { ... } builder block can suspend.
    //    The simple function is no longer marked with suspend modifier.
    //    Values are emitted from the flow using emit function.
    //    Values are collected from the flow using collect function.
    simpleFlow().collect{
        value->
        println(value)
    }
    //Flow doesnot return any value untill collect suspend is not called explictly.
    //This is a key reason the simple function (which returns a flow) is not marked with suspend modifier. By itself, simple() call returns quickly and does not wait for anything.
    // The flow starts every time it is collected, that is why we see "Flow started" when we call collect again.
    val flow= simpleFlow()
}
fun simpleFlow(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(100) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}
fun simpleSyncCall(): Sequence<Int> = sequence {
    // sequence builder
    for (i in 1..3) {
        Thread.sleep(1000) // pretend we are computing it
        yield(i) // yield next value
    }

}