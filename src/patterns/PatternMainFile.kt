package patterns

fun main (){
    println("Patterns entry point.....")

    //Singleton Patterns.
    for (i in 1..10) {
        val singetion = SingletonExample.increment()
        println("The variable incremented $singetion")
    }

    //Static Factory Method
    println("The static method pattern ${NumberMaster.valueOf("123")}")

}