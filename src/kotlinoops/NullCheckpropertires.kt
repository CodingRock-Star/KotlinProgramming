package kotlinoops

import java.io.FileNotFoundException
import java.nio.file.Paths

class NullDemo {
    //To inform the Kotlin compiler that we will allow a variable to contain a null, we must suffix
    //the type with a ?
    private var name: String? = null
    private var name1: String? = "Dhiraj"

    fun demoNull(): String? {
        return name
    }
}

//Smart cast demo

class Address(name: String, postcode: String, val city: City?)
class City(name: String, val country: Country?)
class Country(val name: String)

class Person2(name: String, val address: Address?) {

    fun getCountryName(person: Person2?): String? {
        var countryName: String? = null
        if (person != null) {
            val address = person.address
            if (address != null) {
                val city = address.city
                if (city != null) {
                    val country = city.country
                    if (country != null) {
                        countryName = country.name
                    }
                }
            }
        }
        return countryName
    }

    fun getCountryNameWithSafeNullCheck(person: Person2?): String? {
        //safe null ==>((?.)
        //Force operator is used with the Safe null
        return person?.address?.city?.country?.name
    }
}

class AnnotationDemo {
    @JvmName("filterString")
    fun filter(list: List<String>): Unit {
        println("The List${list.get(0)}")
    }

    @JvmName("filterInt")
    fun filter(list: List<Int>): Unit {
        println("The List${list.get(0)}")
    }

}

//Throws Demo

class FileThroughClass(val path: String) {
    @Throws(FileNotFoundException::class)
    fun exists(): Boolean {
        if (!Paths.get(path).toFile().exists())
            throw FileNotFoundException("$path does not exist")
        return true
    }

}

class JvmOverLoadDemo {
    @JvmOverloads
    fun join(
        array: Array<String>, prefix: String = "",
        separator: String = "", suffix: String = ""
    ) {
        println("The array for the default ${array.size}")
    }

}
