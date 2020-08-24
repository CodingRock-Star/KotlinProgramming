package basickotlin

fun weather(degree: Int): String {
    val (description, color) = when {
        degree < 10 -> "cold" to "Blue"
        degree < 25 -> "mild" to "orange"
        else -> "hot" to "Red"
    }
    return description;
}


fun main() {
    val s = weather(26)
    println("Hello World!!!")
    println("Weather :$s")
    println("Weather1${weather(26)},Weather2 ${weather(11)}")
    //Concatnation
    val d = "Dhiraj"
    val m = "hello $d.. The size of character is ${d.length}."
    print("New String:$m")

    //booleans
    val x = 1
    val y = 4
    val z = 9


    val isTrue = x < y && x > z
    print("The Statment is $isTrue")

    //Arrays
    val arrays = arrayOf(1, 2, 3)
    for (i: Int in arrays) {
        println("The Value of int is :$i")
    }
    val arraysString = arrayOf("Dhiraj", "Anumeha", "Kavya")
    for (i: String in arraysString) {
        println("The Value of String is:$i")
    }

    //Ranges
    val aToZ = "a".."z"
    val isContain = "c" in aToZ
    println("The value is contained in Alphabet:$isContain")

    //Loops

    val onToTen = 1..10
    for (i: Int in onToTen) {
        println("The Value of Int $i")
    }

    for (i: Int in onToTen) {
        val down = 1..5
        for (j: Int in down) {
            println("The Value of Int ${i * j}")
        }
    }
    /**
     *  For example, in the standard String class, Kotlin provides an
     *  iterator extension function that adheres to the required contract and so
     *  strings can be used in a for loop to iterate over the individual characters.

     */
    val string = "print my character"
    for (char in string) {
        println("The characters printed:$char")
    }


    /***********************Referential equality and structural equality*************************************************************************/
    //When working with a language that supports object-oriented programming, there are two concepts of equality.
    // The first is when two separate references point to the exact same instance in memory.
    // The second is when two objects are separate instances in memory but have the same value.
    // What same value means is specified by the developer of the class.
    // For example, for two square instances to be the same we might just require they have the same length and width regardless of co-ordinate.
    //The former is called referential equality. To test whether two references point to the same instance, we use the === operator (triple equals) or !== for negation:
    //    val a = File("/mobydick.doc")    val b = File("/mobydick.doc")    val sameRef = a === b The value of the test a === b is false because, although a and b reference the same file on disk,
    //    they are two distinct instances of the File object. The latter is called structural equality. To test whether two objects have the same value, we use the == operator or != for negation.
    //    These function calls are translated into the use of the equals function that all classes must define. Note that this differs from how the == operator is used in Java – in Java the == operator is for referential equality and is usually avoided.
    //    val a = File("/mobydick.doc")    val b = File("/mobydick.doc")    val structural = a == b Note that, in the double equals check, the value was true. This is because the File object defines equality to be the value of the path. It is up to the creator of a class to determine what structural equality means for that class.

    /****************************************************************************************************************************************/


    //if ..else and try ..catch blocks are expression

    var date = 2010
    val today = if (date == 2010) true else false
    println("The Result of if..else expression $today")

    //function invokation
    println("The Message 1 ${display(6, 7)},The Message ${display1(8,6)} ")

}

fun display(a: Int, b: Int): Int = if (a > b) a else b

fun display1(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else
        return b
}