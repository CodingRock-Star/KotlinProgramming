package kotlinoops


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
    val derivedContainer= DerivedContainer()
    println("The default amount is ${derivedContainer.defaultValue()}")
    println("The new amount is ${derivedContainer.getNewAmount()}")


    //default interface implementation

    val evilKotlinMaster=EvilKotlinMaster("Dhiraj is Evil")
    evilKotlinMaster.sayHello()
    evilKotlinMaster.startGame()
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

class DocumentImpl:Document{
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

open class  Container() {
    protected open val feild:String="I am open"
    protected var amount:Int=1
    init {
        this.amount=amount
    }

    open fun defaultValue():Int{
        return amount
    }

}

class DerivedContainer:Container(){
     fun getNewAmount():Int{
        return amount*10
    }

    override fun defaultValue(): Int {
        return super.defaultValue()
    }
}