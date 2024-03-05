// https://codingdojo.org/kata/Potter/

// - One copy of any of the five books costs 8 EUR.
// - If, however, you buy two different books from the
//   series, you get a 5% discount on those two books.
// - If you buy 3 different books, you get a 10% discount.
// - With 4 different books, you get a 20% discount.
// - If you go the whole hog, and buy all 5, you get
//   a huge 25% discount.

class Potter {

    private var basePrice = 8f;

    fun price(books: Array<Int>): Float {
        return basePrice * books.size;
    }



}