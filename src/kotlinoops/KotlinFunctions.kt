package kotlinoops

import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.ExecutorService

class Submarine {
    fun fire(): Unit {
        println("Firing the torpedoes")
    }

    fun submerge(): Unit {
        println("Submerged ...")
    }
}

fun Submarine.fire(): Unit {
    println("Fire on the board")
}

fun Submarine.submerged(depth: Int): Unit {
    println("Submerged to the depth $depth")
}

class Mappings {
    private val map = hashMapOf<Int, String>()
    private fun String.stringAdd(): Unit {
        map.put(hashCode(), this)
    }

    fun add(value: String): Unit = value.stringAdd()
}

class Extension {
    //Apply is a Kotlin standard library extension function declared on Any, so it could be
    //invoked on instances of all types. Apply accepts a lambda that is invoked with the receiver
    //being the instance where apply was called on. Apply then returns the original instance.
    //It's primary use is to make the code that needs to initialize an instance more readable by
    //allowing functions and properties to be called directly inside the function before returning
    //the value itself. Refer to the following code:
    // val task = Runnable { println("Running") }
    // Thread(task).apply { setDaemon(true) }.start()
    //Here we created a task, an instance of Runnable, and then created a new Thread instance
    //to run this task. Inside the closure, we configure the thread instance to be a daemon thread.
    //Notice that the closure code is operating on the thread instance directly. The instance that
    //apply was called on is the receiver for the closure. Further note that we can call start() on
    //the return value because the original instance is always returned from apply, regardless of
    //what the closure itself returns.

    fun applyDemo(): Unit {
        val task = Runnable { println("This is the demo for apply ext. function") }
        Thread(task).apply { setDaemon(true) }.start()
    }

    //Let is a Kotlin standard library extension function that is similar in vein to apply. The key
//difference is that it returns the value of the closure itself. It is useful when you wish to
//execute some code on an object before returning some different value and you don't need to
//keep a reference to the original:
    fun letDemo(): Unit {

    }

    fun withDemo(): Unit {

    }

    fun runDemo(): Unit {

    }

    //Lazy is another useful function that wraps an expensive function call to be invoked when
//first required:
// fun readStringFromDatabase(): String = ... // expensive operation
// val lazyString = lazy { readStringFromDatabase() }
//The first time we require the result, we can access the value on the lazy reference. Only then
//will the wrapped function actually be invoked:
// val string = lazyString.value
//This is a common pattern seen in many languages and frameworks. The advantage of using
//this built-in function over rolling your own is that synchronization is taken care of for you.
//That is, if the value is requested twice, Kotlin will safely handle any race conditions by only
//executing the underlying function once.
    fun lazyDemo(): Unit {
        val lazyString = lazy { printTheLoop() }
        lazyString.value
    }

    private fun printTheLoop(): Unit {
        for (i in 1..10) {
            println("lAZY PRINT $i")
        }
    }

    //Use is similar to the try-with-resources statement that exists in Java 7. Use is defined as an
//extension on an instance of closeable and accepts a function literal that operates on this
//closeable. Use will safely invoke the function, closing down the resource after the function
//has completed whether an exception was raised or not:
// val input = Files.newInputStream(Paths.get("input.txt"))
// val byte = input.use({ input.read() })
//Functions in Kotlin
//[ 128 ]
//Essentially, use is a more concise way of handling resources in simple cases, without
//needing the try/catch/finally block
    fun useDemo(): Unit {
        val input = Files.newInputStream(Paths.get("input.txt"))
        val byte = input.use({ input.read() })
      //  println("The Bytes$byte")
    }

    fun repeatDemo(){
        repeat(2,{printTheLoop()})
    }

}