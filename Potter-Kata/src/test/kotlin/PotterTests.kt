package tests

import Potter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PotterTests {

    private val sut = Potter();

    @Test
    fun `testBasics`() {
        assertEquals(0f, sut.price(arrayOf()));
        assertEquals(8f, sut.price(arrayOf(0)))
        assertEquals(8f, sut.price(arrayOf(1)))
        assertEquals(8f, sut.price(arrayOf(2)))
        assertEquals(8f, sut.price(arrayOf(3)))
        assertEquals(8f, sut.price(arrayOf(4)))
        assertEquals(8f * 3, sut.price(arrayOf(1, 1, 1)))
    }

    @Test
    fun `testSimpleDiscounts`() {
        assertEquals(8f * 2 * 0.95, sut.price(arrayOf(0, 1)))
        assertEquals(8f * 3 * 0.9, sut.price(arrayOf(0, 2, 4)))
        assertEquals(8f * 4 * 0.8, sut.price(arrayOf(0, 1, 2, 4)))
        assertEquals(8f * 5 * 0.75, sut.price(arrayOf(0, 1, 2, 3, 4)))
    }

    @Test
    fun testSeveralDiscounts() {
        assertEquals(8f + (8 * 2 * 0.95),
            sut.price(arrayOf(0, 0, 1)))
        assertEquals(2f * (8 * 2 * 0.95),
            sut.price(arrayOf(0, 0, 1, 1)))
        assertEquals((8f * 4 * 0.8) + (8 * 2 * 0.95),
            sut.price(arrayOf(0, 0, 1, 2, 2, 3)))
        assertEquals(8f + (8 * 5 * 0.75),
            sut.price(arrayOf(0, 1, 1, 2, 3, 4)))
    }

    @Test
    fun `testEdgeCases`() {
        assertEquals(2f * (8 * 4 * 0.8),
            sut.price(arrayOf(0, 0, 1, 1, 2, 2, 3, 4)))
        assertEquals(3f * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8),
            sut.price(arrayOf(0, 0, 0, 0, 0,
                1, 1, 1, 1, 1,
                2, 2, 2, 2,
                3, 3, 3, 3, 3,
                4, 4, 4, 4)))
    }
}
