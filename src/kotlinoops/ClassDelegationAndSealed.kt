package kotlinoops
//Through this pattern, you replace inheritance with composition. You should always favor
//composition over inheritance for the sake of simplicity, reducing type coupling, and
//flexibility. Using this approach, you can chose and swap the type you put in the delegate
//position based on various requirements
interface UIElement{

    fun getHeight():Int
    fun getWeight():Int
}

class Rectangle constructor(val x1:Int,val x2:Int,val x3:Int):UIElement{
    override fun getHeight(): Int {
    return x1+x2;
    }
    override fun getWeight(): Int {
       return x2*x3
    }

}
// It's basically a hint
//for the compiler to do the work for you: forwarding the calls for the methods exposed by
//the interface UIElement to the underlying Rectangle object.
class Panel(val rectangle: Rectangle):UIElement by rectangle

//TODO:The Sealed class implementaion is pending.