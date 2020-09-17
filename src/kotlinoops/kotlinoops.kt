package kotlinoops

import jdk.nashorn.internal.ir.RuntimeNode
import java.io.File
import java.io.OutputStream
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger


fun main() {

    val person = Person("Dhiraj", "Anumeha", 100)
    println("The Person FirstName:${person.getName()}")
    println("The Person FirstName:${person.getLName()}")


    val person1 = Person1("Dhiraj", "Anumeha", 100)
    println("The Person FirstName:${person1.getName()}")
    println("The Person Age:${person1.getAge()}")

    //inner and outer class

    val basicGraphWithInner = BasicGraphWithInner("Graph")
    val innerBasicGraphWithInner = basicGraphWithInner.InnerLine(2, 3)
    innerBasicGraphWithInner.draw()
    basicGraphWithInner.draw()

    //interface implementation..
    val document: Document = DocumentImpl()
    document.save("save to the file")
    document.getDescription()
    println("${document.name}")
    document.load("load from the file")

    //inheritence implementation. with the single class.
    val payment = Payment(25000)
    val cardPayment = CardPayment(payment.amount, "12345", CardType.AMEX)
    cardPayment.amount()

    //inheritence with the Interface.
    val drivable: Drivable
    drivable = AmphibiuosCar("    Driveable")
    drivable.drive()
    val sailable: Sailable
    sailable = AmphibiuosCar("Sailable")
    sailable.drive()

    //open class demo ...
    val derivedContainer = DerivedContainer()
    println("The default amount is ${derivedContainer.defaultValue()}")
    println("The new amount is ${derivedContainer.getNewAmount()}")


    //default interface implementation

    val evilKotlinMaster = EvilKotlinMaster("Dhiraj is Evil")
    evilKotlinMaster.sayHello()
    evilKotlinMaster.startGame()

    //abstract implementation.

    val childOfParent = ChildOfParent()
    println("The value of ${childOfParent.getValue()}")

    //open class can be used to create a instance also...
    val openClass = AParrent();
    println("The value of ${openClass.getValue()}")

    val t = Thread(Runnable { println("I am the demo thead") })
    t.start()

    //overridding rules...

    val imageEvalution = ImageEvalution();
    imageEvalution.save("Hi")

    //class delegattion

    val panel = Panel(Rectangle(4, 5, 6))
    println("Tha panel Height ${panel.getHeight()}")
    println("Tha panel Weight ${panel.getWeight()}")

    //function extension

    val s = Submarine()
    s.fire();
    s.submerge()
    s.submerged(10)

    //member extension function..
    val ext = Mappings()
    ext.add("Dhiraj")

    //operators
    val ints = arrayOf(1, 2, 3, 4)
    val a = 3 in ints
    println("The value of val is $a")
    println("The Multple arguments ${multiprint("a", "b", "d")}")

    //More extension functions

    val extension = Extension()
    val task = Runnable { extension.lazyDemo() }
    Thread(task).start()

    extension.lazyDemo()
    // extension.useDemo()
    extension.repeatDemo()

    val service = Executors.newSingleThreadExecutor()
    service.submit({ extension.applyDemo() })

    //properties

    val student = Student("DHIRAJ", 20)
    println("The student age student ${student.age1}")
    student.name1 = "Anumeha"
    println("The student name name :student ${student.name1}")

    //properties 1
    val rectangle = Rectange(3.0, 4.0)
    println("The property feild is defined ${rectangle.isSquare}")

    //properties 2
    val counter = LengthCounter()
    counter.counter = 3
    counter.addWord("dHIRAJ")
    println("The property feild of couunter ${counter.counter}")

    //properties 2

    val faceBookUser = FaceBookUser(12343)
    println("My Facebook is activated ${faceBookUser.userName}")

    //late initialization
    val myClass = MyClass()
    myClass.initilizationLogic()

    //lazy property
    val myLazyClass = WithLazyProperty()
    println(myLazyClass.foo + myLazyClass.foo)

    //Null demos
    val demoNull = NullDemo()
    println("The demos of null ?${demoNull.demoNull()} ")

    //Smart Cast

    val country = Country("Germany")
    val city = City("Berlin", country)
    val address = Address("kerpen West", "345343", city)

    val person2: Person2? = Person2("Dhiraj", address)
    //Force Operator
    //Sometimes we might decide that we want to dispense with the compiler's checks and force
    //a nullable type into a non-nullable type. This is useful in situations were we are dealing
    //with Java code, which we know is never null, and we need to use a variable with a function
    //that only accepts non-nullable values. To do this, we can use the !! operator:
    println("The country Name of Dhiraj ${person2!!.getCountryName(person2)}")

    //safe null check...

    val person3: Person2? = Person2("Anumeha", address)
    //Safe null does not need (((!!)==>Force Operator
    println("The country Name of anumeha ${person2.getCountryNameWithSafeNullCheck(person3)}")

    //Force Operator
    val nullableName: String? = "Dhiraj"
    val name: String = nullableName!!
    println("The Force Operator${forceOperatorDemo(name)}")

    //Elivas opertator
    //  This infix operator can be placed in between a nullable expression and an expression to use
    //        if the nullable expression is indeed null. So the general usage resembles the following:
    val nullableName1: String? = "Dhiraj"
    val name1: String = nullableName1 ?: "default_name"

    println("The Elivas Operator$name1")

    //Safe Casting...
    //In the following example, we will cast a parameter that we know is a String, but the
    //compiler doesn't know it is a String as we declared it as an Any:
    val location: Any = "London"
    val safeString: String? = location as? String
    val safeInt: Int? = location as? Int

    //Annotations :

    //JVM Name
    val AnnotationDemo = AnnotationDemo();

    AnnotationDemo.filter(listOf("dhira", "anu"))
    AnnotationDemo.filter(listOf(2, 3, 4, 4))

    //JVM Static

    //The @JvmStatic annotation informs the compiler that you wish the function or property
    //annotated to have a Java static method generated in the compiled output. This annotation
    //can only be used on objects or companion objects.
    //By default an object or companion object is compiled into a class that has a single instance.
    //This instance is then stored in a static field named INSTANCE. To access functions on these
    //objects in Java, you are required to first resolve the singleton. For example:
    // HasStaticFuncs.INSTANCE.foo();
    //However, the annotation will result in the function being a static method rather than an
    //instance method, so we can invoke it directly on the type:
    // HasStaticFuncs.foo();


}

fun forceOperatorDemo(value: String?): String? {
    return value
}

fun multiprint(vararg strings: String): Unit {
    for (string in strings)
        println(string)
}

object SingletonExample {
    init {
        println("I will be initilized once...")
    }

    private val counter = AtomicInteger(4)
    @JvmStatic
    fun increment() = counter.getAndIncrement()
}

//Interface demos.....
interface Document {
    val version: Long
    val size: Long
    val name: String
        get() = "NnName"

    fun save(input: String)

    fun load(stream: String)
    //default implementation.
    fun getDescription(): String {
        return "Docuemnt $name"
    }

}

class DocumentImpl : Document {
    override val version: Long
        get() = 0
    override val size: Long
        get() = 0

    override fun save(input: String) {
        println("$input")
    }

    override fun load(stream: String) {
        println("$stream")
    }


}

open class Container() {
    protected open val feild: String = "I am open"
    protected var amount: Int = 1

    init {
        this.amount = amount
    }

    open fun defaultValue(): Int {
        return amount
    }

}

class DerivedContainer : Container() {
    fun getNewAmount(): Int {
        return amount * 10
    }

    override fun defaultValue(): Int {
        return super.defaultValue()
    }
}