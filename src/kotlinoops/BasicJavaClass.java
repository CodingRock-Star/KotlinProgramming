package kotlinoops;

import basickotlin.BasicKt;
import kotlin.jvm.JvmMultifileClass;


public class BasicJavaClass {
    public static void main(String []args){
        //The function from the Kotlin class can be called as static even the main() function also
      BasicKt.main();
      //The singleton instance will be called in the below way/
      SingletonExample.increment();
    }

}
