package kotlinoops

class Person constructor(var firstName: String, var lastName: String, var age: Int?) {

    fun getName(): String = this.firstName


    fun getLName(): String = this.lastName
}

class Person1(firstName: String, lastName: String, age: Int?) {
    private val name: String
    private val age: Int?

    init {
        this.name = "$firstName,$lastName"
        this.age = age
    }

    fun getName(): String = this.name
    fun getAge(): Int? = this.age

}

class BasicGraphWithInner(graphName: String) {
    private val name: String

    init {
        this.name = graphName
    }
// inner class will be accessed with the same java construct and can access the private variable
 // of the outer class also.
    inner class InnerLine(val x1: Int, val x2: Int) {

        fun draw(): Unit {
            println("Drawing Line from ($x1:$x2 from name $name")

        }
    }

    fun draw(): Unit {
        println("Draaing the line with the name :$name")
    }

}
