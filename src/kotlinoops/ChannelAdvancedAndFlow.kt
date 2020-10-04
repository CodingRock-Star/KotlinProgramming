package kotlinoops

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
//Advanced concepts of Courtines.
fun main(){

 selectChannelDemo()
}

fun selectChannelDemo()= runBlocking() {
    val p1=producer1()
    val p2=producer2()
    repeat(5){
        selector(p1,p2)
    }
    coroutineContext.cancelChildren()
}

suspend  fun selector(m1:ReceiveChannel<String>,m2:ReceiveChannel<String>){
    select<Unit>{
        m1.onReceive{
            value->println(value)
        }
        m2.onReceive{
                value->println(value)
        }
    }
}

fun CoroutineScope.producer1()=produce(coroutineContext){
    while(true){
        delay(500)
        send("From Producer 1")
    }
}

fun CoroutineScope.producer2()=produce(coroutineContext){
    while(true){
      delay(200)
        send("From Producer 2")
    }
}