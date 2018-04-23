import java.math.BigInteger


class TestRabinMiller(private val number: BigInteger, private val rounds: Int){

    companion object {
        val TWO = BigInteger.valueOf(2)!!
        val arrayOfPrimes = arrayOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997)
    }

    private fun isSureComplex() : Boolean{
        for (i in 0..arrayOfPrimes.lastIndex)
            if (number.mod(BigInteger.valueOf(arrayOfPrimes[i].toLong())).compareTo(BigInteger.ZERO) == 0){
                return true
            }
        return false
    }

    private fun decompose() : Pair<Int, BigInteger>{
        var t = number.subtract(BigInteger.ONE)
        var s = 0

        do {
            t = t.divide(TWO)
            s += 1
        }
            while (t.mod(TWO).compareTo(BigInteger.ZERO) == 0)

        return Pair(s, t)
    }

    fun isPrime(): Boolean {

        if (isSureComplex()) return false

        var a : BigInteger
        val s = decompose().first
        val t = decompose().second
        var x : BigInteger

        loop@for (round in 1..rounds){
            a = number.subtract(BigInteger.valueOf(3)).multiply(BigInteger.valueOf(Math.random().toLong())).add(TWO)
            x = a.modPow(t, number)
            if (x == BigInteger.ONE || x == number.subtract(BigInteger.ONE)) continue
            for (i in 1 until s){
                x = x.modPow(TWO, number)
                if (x == BigInteger.ONE) return false
                if (x == number.subtract(BigInteger.ONE)) continue@loop
            }
            return false
        }
        return true

    }
}