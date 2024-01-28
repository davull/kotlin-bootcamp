class FizzBuzzer {

    fun getFizzBuzzes() {

        val numbers = getNumbers()

        for (number in numbers) {
            val fizzBuzz = convertNumber(number)
            println(fizzBuzz)
        }
    }

    private fun getNumbers() = 1..100

    private fun convertNumber(number: Int): String {
        if (isFizzBuzz(number)) {
            return "FizzBuzz"
        }

        if (isFizz(number)) {
            return "Fizz"
        }

        if (isBuzz(number)) {
            return "Buzz"
        }

        return number.toString()
    }

    private fun isFizz(number: Int) = number % 3 == 0

    private fun isBuzz(number: Int) = number % 5 == 0

    private fun isFizzBuzz(number: Int) = isFizz(number) && isBuzz(number)
}