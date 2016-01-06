package generalUtils;

/**
 * Created by Laurens on 5-1-2016.
 */
public class ByteUtils {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 3];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 3] = hexArray[v >>> 4];
            hexChars[j * 3 + 1] = hexArray[v & 0x0F];
            hexChars[j * 3 + 2] = ' ';
        }
        return new String(hexChars);
    }

    /**
     * @param value
     * @return
     */
    public static final byte[] intToByteArray(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value
        };
    }


    public static byte[] stripZeroBytes(int toStrip) {
        return stripZeroBytes(intToByteArray(toStrip));
    }

    public static byte[] stripZeroBytes(byte[] toStrip) {
        int leadingZeros = 0;
        for (byte b : toStrip) {
            if (b == 0x00) {
                leadingZeros++;
            } else {
                break;
            }
        }
        byte[] newArray = new byte[toStrip.length - leadingZeros];
        int pointer = 0;
        for (int i = 0; i < toStrip.length; i++) {
            byte b = toStrip[i];
            if (b == 0x00)
                if (pointer == 0) continue;
            newArray[pointer++] = b;
        }
        return newArray;
    }
}
