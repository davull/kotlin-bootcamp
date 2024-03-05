package tests

import Potter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PotterTests {

    private val sut = Potter()

    @Test
    fun testBasics() {
        assertEquals(0.0, sut.price(arrayOf()))
        assertEquals(8.0, sut.price(arrayOf(0)))
        assertEquals(8.0, sut.price(arrayOf(1)))
        assertEquals(8.0, sut.price(arrayOf(2)))
        assertEquals(8.0, sut.price(arrayOf(3)))
        assertEquals(8.0, sut.price(arrayOf(4)))
        assertEquals(8.0 * 3, sut.price(arrayOf(1, 1, 1)))
    }

    @Test
    fun testSimpleDiscounts() {
        assertEquals(8 * 2 * 0.95, sut.price(arrayOf(0, 1)))
        assertEquals(8 * 3 * 0.9, sut.price(arrayOf(0, 2, 4)))
        assertEquals(8 * 4 * 0.8, sut.price(arrayOf(0, 1, 2, 4)))
        assertEquals(8 * 5 * 0.75, sut.price(arrayOf(0, 1, 2, 3, 4)))
    }

    @Test
    fun testSeveralDiscounts() {
        assertEquals(
            8 + (8 * 2 * 0.95),
            sut.price(arrayOf(0, 0, 1))
        )
        assertEquals(
            2 * (8 * 2 * 0.95),
            sut.price(arrayOf(0, 0, 1, 1))
        )
        assertEquals(
            (8 * 4 * 0.8) + (8 * 2 * 0.95),
            sut.price(arrayOf(0, 0, 1, 2, 2, 3))
        )
        assertEquals(
            8 + (8 * 5 * 0.75),
            sut.price(arrayOf(0, 1, 1, 2, 3, 4))
        )
    }

    @Test
    fun testEdgeCase1() {
        assertEquals(
            2 * (8 * 4 * 0.8),
            sut.price(arrayOf(0, 0, 1, 1, 2, 2, 3, 4))
        )
    }

    @Test
    fun testEdgeCase2() {
        assertEquals(
            3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8),
            sut.price(
                arrayOf(
                    0, 0, 0, 0, 0,
                    1, 1, 1, 1, 1,
                    2, 2, 2, 2,
                    3, 3, 3, 3, 3,
                    4, 4, 4, 4
                )
            )
        )
    }
}
