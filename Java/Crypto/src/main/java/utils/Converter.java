package utils;

public class Converter {
    public static final int EXPECTED_INT_SIZE_IN_BYTES = Integer.SIZE / Byte.SIZE;

    /*
    * Intuition behind the 'byte & 0xFF' mask:
    *
    * By default, the JVM casts a byte to signed int and does a 'sign extension':
    *
    * e.g Suppose we have the following byte - (byte) 200; // 11001000
    * Integers use up to 32 bits, so our byte has to be extended and to
    * preserve the sign, the JVM will produce the following int:
    *
    * 11111111 11111111 11111111 11001000 (-56)
    *
    * So now, in order to convert this to an unsigned integer, we apply
    * a mask, which happens to be 0xFF because (in bits) this represents:
    *
    * e.g: 00000000 00000000 00000000 11111111
    *
    * 00000000 00000000 00000000 11111111
    * &
    * 11111111 11111111 11111111 11001000
    * -----------------------------------
    * 00000000 00000000 00000000 11001000 (200)
    * */
    public static int byteArrayToInt(byte[] bytes) {

        if(bytes.length > EXPECTED_INT_SIZE_IN_BYTES) {
            throw new RuntimeException("Too big to fit in int");
        }

        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result <<= 8;
            result |= bytes[i] & 0xFF;
        }
        return result;
    }
}
