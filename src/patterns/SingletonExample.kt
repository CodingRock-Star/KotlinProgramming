package patterns

import java.util.concurrent.atomic.AtomicInteger


object SingletonExample{
    init {
        println("I will be initilized once...")
    }
    private val counter=AtomicInteger(4)
    fun increment()= counter.getAndIncrement()
}