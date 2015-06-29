package com.google.guava.example;

import java.math.BigInteger;
import java.math.RoundingMode;

import com.google.common.math.BigIntegerMath;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

public class Math {

    /* Guava's math utilities are already exhaustively tested for unusual overflow conditions.
     * Overflow semantics, if relevant, are clearly specified in the associated documentation.
     * When a precondition fails, it fails fast.
     *
     * Guava's math utilities have been painstakingly benchmarked and optimized. While performance
     * inevitably varies depending on particular hardware details, their speed is competitive with
     * -- and in some cases, significantly better than -- analogous functions in Apache Commons MathUtils.
     *
     * Guava's math utilities are designed to encourage readable, correct programming habits.
     * The meaning of IntMath.log2(x, CEILING) is unambiguous and obvious even on a casual read-through.
     * The meaning of 32 - Integer.numberOfLeadingZeros(x - 1) is not.
     */

    @SuppressWarnings("unused")
    public static void usingMath() {
        int logFloor = LongMath.log2(20, RoundingMode.FLOOR);
        // fast fail if there is an overflow
        int mustNotOverflow = IntMath.checkedMultiply(10000, 100000);
        // fail fast on non-multiple of 3
        long quotient = LongMath.divide(12, 3, RoundingMode.UNNECESSARY);
        BigInteger nearestInteger = DoubleMath.roundToBigInteger(10.5432d, RoundingMode.HALF_EVEN);
        BigInteger sideLength = BigIntegerMath.sqrt(new BigInteger("25"), RoundingMode.CEILING);
        // greatest common divisor
        IntMath.gcd(10, 15);
        IntMath.mod(9, 3);
        IntMath.pow(2, 8);
        IntMath.isPowerOfTwo(64);

        // Tests if the input is finite and an exact integer
        DoubleMath.isMathematicalInteger(4.000d);
        DoubleMath.roundToInt(5.123, RoundingMode.FLOOR);
        DoubleMath.roundToLong(5.123, RoundingMode.CEILING);
        DoubleMath.roundToBigInteger(5.123, RoundingMode.HALF_EVEN);
    }
}
