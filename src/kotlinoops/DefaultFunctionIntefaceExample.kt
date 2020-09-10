package kotlinoops

interface Greeter {
    fun sayHello() {
        println("hello...")
    }
}

abstract class AbstractKotlinMaster(private val gameName:String){
    open fun startGame(){
        println("Game $gameName has started")
    }
}

open class KotlinMaster constructor(gameName: String):Greeter,AbstractKotlinMaster(gameName){

}
class EvilKotlinMaster(private val awfulgame:String):KotlinMaster(awfulgame){
    override fun sayHello() {
        super.sayHello()
       println("Prepare to Die")
    }

    override fun startGame() {
        super.startGame()
        println("Game $awfulgame is over")

    }
}