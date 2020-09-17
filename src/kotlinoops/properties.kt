package kotlinoops

class Student(name: String, age: Int) {
     var name1 = ""
        // Noticed the usage of the field keyword inside the set block? It is an
        //alias for the backing field generated for us. If you use the Kotlin code from Java, you will
        //end up with the typical pattern of calling get*** and set***:
        set(value) {
            field = value
        }
     var age1 = 20
       private set(value) {
            field = value
        }


}
interface Shape{
    //property...
    var area:Double
    get;

}

class Rectange(val width:Double,val height:Double):Shape{
    override var area: Double = 0.0
        get() =width*height
     set(value) {
         field=value
     }

    val isSquare:Boolean
     get() {
         return height==width
     }

}

class LengthCounter{
    var counter:Int=0
    public set

    fun addWord(word:String){
        counter+=word.length
    }

}

//property as an interface
interface User{
    val userName:String

}
class FaceBookUser(val accountId:Int):User{
    override val userName: String
        get() = "Hi this is my new Facebook user name"+accountId
}

class TwitterUser(val accountId: Int):User{
    override val userName: String
        get() = "Hi this is my new Twitter user name"+accountId

}

//1. The lazy {...} delegate can only be used for val properties; lateinit can
//only be used for var properties.
//2. A lateinit var property can't be compiled into a final field, hence you can't
//achieve immutability.
//3. A lateinit var property has a backing field to store the value, whereas lazy
//{...} creates a delegate object that acts as a container for the value once created
//and provides a getter for the property. If you need the backing field to be present
//in the class, you will have to use lateinit.
//4. The lateinit property cannot be used for nullable properties or Java primitive
//types. This is a restriction imposed by the usage of null for uninitialized values.
//Properties
//[ 197 ]
//5. The lateinit var property is more flexible when it comes to where it can be
//initialized. You can set it up anywhere the object is visible from. For lazy{}, it
//defines the only initializer for the property, which can be altered only by
//overriding. The instantiation is thus known in advance, unlike a lateinit var
//property, where if you use a dependency injection, for example, it can end up
//providing different instances of derived classes.

//late initialization

class MyClass {
    //This cannot be val, cannnot be immutable
    //Late init has the backing feild
    lateinit var lateInitVariable:String
    private set
     get

    fun initilizationLogic(){
        println(this::lateInitVariable.isInitialized)
        lateInitVariable="value"
        this::lateInitVariable.set("Dhiraj")
        println(this::lateInitVariable.get())
        println(this::lateInitVariable.isInitialized)

    }
}

//lazy propery
class WithLazyProperty {
    val foo: Int by lazy {
        println("Initializing foo")
        2
    }
}

