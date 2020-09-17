package kotlinoops;

import basickotlin.BasicKt;

import java.io.FileNotFoundException;



public class BasicJavaClass {
    public static void main(String[] args) {
        //The function from the Kotlin class can be called as static even the main() function also
        BasicKt.main();
        //The singleton instance will be called in the below way/
        SingletonExample.increment();
        Student student = new Student("Dhiraj", 20);
        System.out.println(student.getAge1());

        try {
            throwsExample();
        } catch (FileNotFoundException e) {
            System.out.println("File Not exists" + e);
        }
        //Demo JVM OVERLOAD
        JvmOverLoadDemo overLoadDemo = new JvmOverLoadDemo();
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        overLoadDemo.join(cars);

    }

    private static void throwsExample() throws FileNotFoundException {
        boolean exists = new FileThroughClass("somefile.txt").exists();
        System.out.println("File exists");


    }
}