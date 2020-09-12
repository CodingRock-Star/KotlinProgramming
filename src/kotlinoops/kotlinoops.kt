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

    val service =Executors.newSingleThreadExecutor()
    service.submit({extension.applyDemo()})
}

fun multiprint(vararg strings: String): Unit {
    for (string in strings)
        println(string)
}

object SingletonExample{
    init {
        println("I will be initilized once...")
    }
    private val counter= AtomicInteger(4)
    @JvmStatic
    fun increment()= counter.getAndIncrement()
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