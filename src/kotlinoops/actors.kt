package kotlinoops


import javafx.application.Application.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import java.util.Collections.list
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis
import kotlin.String as String1

//An actor is an entity made up of a combination of a coroutine, the state that is confined and encapsulated into this coroutine,
// and a channel to communicate with other coroutines.
// A simple actor can be written as a function, but an actor with a complex state is better suited for a class
fun main() {
    actorsDemo()
}

fun actorsDemo()= runBlocking() {
val jobs=1000
val count =1000
    val counter=counterActor()
   withContext(Dispatchers.Default){
       massiveRun{
           counter.send(InitCounter)
       }
   }

    val reponse = CompletableDeferred<Int>()
    withContext(Dispatchers.Default){
        massiveRun{
            counter.send(IncCounter)
        }
    }
    val time =measureTimeMillis {
        withContext(Dispatchers.Default) {
            massiveRun {
                counter.send(GetCounter(reponse))
            }
        }
    }
    println("The result is ${reponse.await()} in time $time ")


}

sealed class CounterMsg

object InitCounter :CounterMsg()

object IncCounter:CounterMsg()

class GetCounter(val reponse:CompletableDeferred<Int>):CounterMsg()


fun CoroutineScope.counterActor()=actor<CounterMsg> {
    var counter =0;
    for(msg in channel){
        when(msg){
            is InitCounter->counter=0
            is IncCounter->counter++
            is GetCounter->msg.reponse.complete(counter)
        }
    }

}



fun log(m: String) {
    println("Task is running on $[{Thread.currentThread().name}] with $m")
}

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100  // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine
    val time = measureTimeMillis {
        coroutineScope { // scope for coroutines
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
}


