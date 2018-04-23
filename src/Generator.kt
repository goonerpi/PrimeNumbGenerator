import java.math.BigInteger
import java.util.*

    class Generator(bitLength: Int, rounds: Int){
    private var number: BigInteger

    init {
        do {
            number = BigInteger(bitLength, Random()).or(BigInteger.ONE)!!
            val x = TestRabinMiller(number, rounds).isPrime()
            println("Checking $number...")
            if (!x) println("Complex") else println("Prime!")
        }
        while (!x)

        println("----------------------------------------------------\n" +
                "----------------------------------------------------\n" +
        "Your $bitLength-th BitLength prime number with ${(1 - 1 / Math.pow(4.0,rounds.toDouble())) * 100}% chance is\n $number")
    }
}