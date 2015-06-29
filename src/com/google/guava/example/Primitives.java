package com.google.guava.example;

import java.util.List;

import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedInts;

public class Primitives {

    // can do this with Doubles, Floats, Shorts and Longs as well
    public static void usingInts() {
        final int[] array2 = new int[] {0, 14, 99};
        final int[] array = new int[] {5, 2, 4, -12, 100};
        Ints.contains(array, 4);    // true
        Ints.indexOf(array, 5);     // 0
        Ints.concat(array, array2); // 0, 14, 99, 5, 2, 4, -12, 100
        Ints.join(":", array2);     // "0:14:99"
        Ints.max(array2);           // 99
        Ints.min(array2);           // 0
    }

    @SuppressWarnings("unused")
    public static void usingObjectArrays() {
        String[] array1 = new String[] {"one", "two", "three" };
        String[] array2 = new String[] {"four", "five" };

        // prepend
        String[] newArray1 = ObjectArrays.concat("zero", array1);
        // append
        String[] newArray2 = ObjectArrays.concat(array2, "six");
        String[] newArray = ObjectArrays.concat(array1, array2, String.class);
    }

    // can also use UnsignedLongs and UnsignedLong
    public static void usingUnsigned() {
        UnsignedInts.parseUnsignedInt("4294967295");
        UnsignedInts.toString(12345);
        UnsignedInteger uint = UnsignedInteger.MAX_VALUE;
        UnsignedInteger uint2 = UnsignedInteger.valueOf(2);
        uint = uint.plus(uint2);
        uint = uint.minus(uint2);
        uint = uint.dividedBy(uint2);
        uint = uint.times(uint2);
        uint = uint.mod(uint2);
    }

    public static List<Byte> convertByteArrayToListOfBytes(byte[] byteArray) {
        // Doesn't actually create a new list!  That's why it says "asList"
        return Bytes.asList(byteArray);
    }

    // this can be done for other primitives
    @SuppressWarnings("unused")
    public static void usingByteConversion() {
        int numberOfBytesInAnInteger = Ints.BYTES;  // 4
        Ints.fromByteArray(new byte[] {0x12, 0x13, 0x14, 0x15, 0x33}); // 0x12131415
        Ints.toByteArray(0xFFEEDDCC); // {(byte) 0xFF, (byte) 0xEE, (byte) 0xDD, (byte) 0xCC}
        Ints.fromBytes((byte) 0x12, (byte) 0x13, (byte) 0x14, (byte) 0x15); // 0x12131415
    }

}
