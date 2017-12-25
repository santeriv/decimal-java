package org.firebirdsql.decimal.util;

/**
 * Helper methods for byte arrays.
 *
 * @author <a href="mailto:mrotteveel@users.sourceforge.net">Mark Rotteveel</a>
 * @since 3.0
 */
public final class ByteArrayHelper {

    //@formatter:off
    private static final char[] BYTE_2_HEX=(
            "000102030405060708090A0B0C0D0E0F"+
            "101112131415161718191A1B1C1D1E1F"+
            "202122232425262728292A2B2C2D2E2F"+
            "303132333435363738393A3B3C3D3E3F"+
            "404142434445464748494A4B4C4D4E4F"+
            "505152535455565758595A5B5C5D5E5F"+
            "606162636465666768696A6B6C6D6E6F"+
            "707172737475767778797A7B7C7D7E7F"+
            "808182838485868788898A8B8C8D8E8F"+
            "909192939495969798999A9B9C9D9E9F"+
            "A0A1A2A3A4A5A6A7A8A9AAABACADAEAF"+
            "B0B1B2B3B4B5B6B7B8B9BABBBCBDBEBF"+
            "C0C1C2C3C4C5C6C7C8C9CACBCCCDCECF"+
            "D0D1D2D3D4D5D6D7D8D9DADBDCDDDEDF"+
            "E0E1E2E3E4E5E6E7E8E9EAEBECEDEEEF"+
            "F0F1F2F3F4F5F6F7F8F9FAFBFCFDFEFF").toCharArray();
    //@formatter:on

    private ByteArrayHelper() {
        // no instances
    }

    /**
     * Converts the provided byte array to a hexadecimal string
     * <p>
     * Adapted from http://stackoverflow.com/a/21429909/466862 by <a href="http://stackoverflow.com/users/1182868/higginse">higginse</a>
     * </p>
     *
     * @param bytes byte array (not {@code null}
     * @return String with the content of the byte array in hexadecimal.
     */
    public static String toHexString(byte[] bytes) {
        final int length = bytes.length;
        final char[] chars = new char[length << 1];
        final char[] byte2Hex = BYTE_2_HEX;
        int index = 0;
        int offset = 0;
        while (offset < length) {
            int hexIndex = (bytes[offset++] & 0xFF) << 1;
            chars[index++] = byte2Hex[hexIndex++];
            chars[index++] = byte2Hex[hexIndex];
        }
        return new String(chars);
    }

    /**
     * Converts a hexadecimal string to a byte array.
     * <p>
     * Adapted from https://stackoverflow.com/a/140861/466862 by <a href="https://stackoverflow.com/users/3093/dave-l">Dave L.</a>
     * </p>
     *
     * @param hexString Hexadecimal string
     * @return byte array
     */
    public static byte[] hexToBytes(String hexString) {
        if (hexString == null) {
            return null;
        }
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }
}
