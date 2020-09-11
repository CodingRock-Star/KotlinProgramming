package kotlinoops


abstract class DDerived : AParrent() {
    abstract override fun getValue(): Int;
    protected val s: Int =20
    fun getDescription(): Unit {
        println("The abstract method default...")
    }
}

open class AParrent {
    open fun getValue(): Int =25
}

class ChildOfParent : DDerived() {
    override fun getValue(): Int {
        return s*10
    }

}
