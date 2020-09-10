package patterns

fun main (){
    println("Patterns enrty pointsss.....")

    //Singleton Patterns.
    for (i in 1..10) {
        val singetion = SingletonExample.increment()
        println("The variable incremented $singetion")
    }
}