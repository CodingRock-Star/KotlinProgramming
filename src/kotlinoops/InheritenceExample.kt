package kotlinoops

import java.io.OutputStream
import java.math.BigDecimal

open class Payment(val amount: Int){
    fun getValue():Int{
        return amount
    }
}

enum class CardType {
    VISA, MASTERCARD, AMEX
}

class CardPayment constructor(
    amount: Int
    , val number: String, val type: CardType
) : Payment(amount) {

    fun amount(){
        println("The amount in the card $number is ${amount}")
    }

}

class ChequePayment : Payment {
    constructor(amount: Int, number: String) : super(amount) {
        this.name = number
    }

    private var name: String
        get() = this.name

    fun amount(){
        println("The amount in the card $name is ${amount}")
    }
}

interface Drivable{

    fun drive()
}

interface Sailable{
    fun drive()
}

class AmphibiuosCar(val name:String): Any(),Drivable,Sailable{
    override fun drive() {
       println("Amphibious car is $name")
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

open class Image{
    open fun save(output:String){
        println("logic for saving the image")
    }
}


interface VendorImage {
    fun save(output:String){
        println("Vendor logic for saving the method $output")
    }
}

class ImageEvalution: Image(),VendorImage{
    override fun save(output: String) {
        println("Image Evalution  $output")
        super<VendorImage>.save(output)
    }

}