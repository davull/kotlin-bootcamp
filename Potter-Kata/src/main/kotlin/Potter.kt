// https://codingdojo.org/kata/Potter/

// - [x] One copy of any of the five books costs 8 EUR.
// - [x] If, however, you buy two different books from the
//   series, you get a 5% discount on those two books.
// - [x] If you buy 3 different books, you get a 10% discount.
// - [x] With 4 different books, you get a 20% discount.
// - [x] If you go the whole hog, and buy all 5, you get
//   a huge 25% discount.

class Potter {
    private var basePrice = 8.0

    fun price(books: Array<Int>): Double {
        val itemsPerBookType = getItemsPerBookType(books)
        val numberOfDistinctBooks = itemsPerBookType.size
        val discount = getDiscount(numberOfDistinctBooks)
        val price = getDiscountedPrice(basePrice, discount)

        return price * books.size

    }

    private fun getItemsPerBookType(books: Array<Int>): Map<Int, Int> {
        val groups = books
            .groupBy { it }
            .map { it.key to it.value.size }
            .toMap()
        return groups
    }

    private fun getDiscount(numberOfDistinctBooks: Int): Double {
        return when (numberOfDistinctBooks) {
            2 -> .05
            3 -> .1
            4 -> .2
            5 -> .25
            else -> .0
        }
    }

    private fun getDiscountedPrice(price: Double, discount: Double): Double {
        val factor = 1 - discount
        return price * factor
    }
}