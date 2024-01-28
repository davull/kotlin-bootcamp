import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class FizzBuzzerTests {

    private val sut = FizzBuzzer()

    @ParameterizedTest
    @CsvSource("1,1", "2,2", "3,Fizz", "6,Fizz", "9,Fizz")
    fun `test Fizz`(number: Int, expected: String) {
        val actual = sut.convertNumber(number)
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource("1,1", "2,2", "5,Buzz", "10,Buzz")
    fun `test Buzz`(number: Int, expected: String) {
        val actual = sut.convertNumber(number)
        assertEquals(expected, actual)
    }

    @Test
    fun `test getFizzBuzzes`() {
        val list = mutableListOf<String>()
        sut.getFizzBuzzes { list.add(it) }

        assertEquals(100, list.size)
        assertEquals("1", list[0])
        assertEquals("2", list[1])
        assertEquals("Fizz", list[2])
        assertEquals("4", list[3])
        assertEquals("Buzz", list[4])
        assertEquals("FizzBuzz", list[14])
    }
}