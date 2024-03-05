// https://codingdojo.org/kata/Potter/

// - [x] One copy of any of the five books costs 8 EUR.
// - [x] If, however, you buy two different books from the
//       series, you get a 5% discount on those two books.
// - [x] If you buy 3 different books, you get a 10% discount.
// - [x] With 4 different books, you get a 20% discount.
// - [x] If you go the whole hog, and buy all 5, you get
//       a huge 25% discount.
// - [x] Note that if you buy, say, four books, of which 3 are
//       different titles, you get a 10% discount on the 3 that
//       form part of a set, but the fourth book still costs 8 EUR.
// - [ ] Potter mania is sweeping the country and parents of
//       teenagers everywhere are queueing up with shopping baskets
//       overflowing with Potter books. Your mission is to write a
//       piece of code to calculate the price of any conceivable
//       shopping basket, **giving as big a discount as possible**.

class Potter {
    private val basePrice = 8.0

    fun price(books: Array<Int>): Double {
        val bookSets = buildSets(books)
        val totalPrice = bookSets.sumOf { getPriceOfSet(it) }
        return totalPrice
    }

    private fun buildSets(books: Array<Int>): Array<Array<Int>> {
        val itemsPerBookType = getItemsPerBookType(books)
        val sets = buildSets(itemsPerBookType)
        return sets
    }

    private fun buildSets(itemsPerBookType: MutableMap<Int, Int>): Array<Array<Int>> {

        val numberOfSets =
            if (itemsPerBookType.isNotEmpty())
                itemsPerBookType.maxOf { it.value }
            else
                0

        val sets = Array(numberOfSets) { mutableListOf<Int>() }

        for (i in 0 until numberOfSets) {
            for ((book, count) in itemsPerBookType) {
                if (count == 0) continue

                sets[i].add(book)
                itemsPerBookType[book] = count - 1
            }
        }

        return sets
            .map { it.toTypedArray() }
            .toTypedArray()
    }

    private fun getPriceOfSet(bookSet: Array<Int>): Double {
        val numberOfDistinctBooks = bookSet.size
        val discount = getDiscount(numberOfDistinctBooks)
        val price = getDiscountedPrice(basePrice, discount)
        return price * bookSet.size
    }

    private fun getItemsPerBookType(books: Array<Int>): MutableMap<Int, Int> {
        return books
            .groupBy { it }
            .map { it.key to it.value.size }
            .toMap()
            .toMutableMap()
    }

    private fun getDiscount(numberOfDistinctBooks: Int) = when (numberOfDistinctBooks) {
        2 -> .05
        3 -> .1
        4 -> .2
        5 -> .25
        else -> .0
    }

    private fun getDiscountedPrice(price: Double, discount: Double) = price * (1 - discount)
}