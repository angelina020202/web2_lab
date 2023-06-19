import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

data class Person(
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("isStudent") val isStudent: Boolean
)

class JsonConverter {

    private val gson = Gson()

    fun toJson(person: Person): String {
        return gson.toJson(person)
    }

    fun fromJson(json: String): Person {
        return gson.fromJson(json, Person::class.java)
    }
}

class PersonTest {
    private val jConv = JsonConverter()

    @Test
    fun serializePerson() {
        val person = Person("Ivan", 23, true)
        val json = jConv.toJson(person)
        assertEquals("""{"name":"Ivan","age":23,"isStudent":true}""", json)
        println("Cериализация: \nВходные данные: $person \nВыходные данные: $json")
        println()
    }

    @Test
    fun deserializePerson() {
        val json = """{"name":"Marina","age":32,"isStudent":false}"""
        val person = jConv.fromJson(json)
        assertEquals(Person("Marina", 32, false), person)
        println("Десериализация: \nВходные данные: $json \nВыходные данные: $person")
        println()
    }
}