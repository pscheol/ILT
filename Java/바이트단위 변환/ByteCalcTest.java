import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ByteCalcTest {
    private static final List<String> units = new ArrayList<>(Arrays.asList("bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB"));

    public static final String BYTE_CALC_PATTERN = "#,###.##";

    /**
     * 바이트 단위
     * kb = 1000^1 = 10^3 : 1kb = 1024b
     * mb = 1000^2 = 10^6 : 1mb = 1024kb
     * gb = 1000^3 = 10^9 : 1gb = 1024MB
     * tb = 1000^4 = 10^12 : 1TB = 1048576 MB
     * pb = 1000^5 = 10^15
     * eb = 1000^6 = 10^18
     * zb = 1000^7 = 10^21
     * @param bytes : 바이트
     * @param pattern : 단위 #,###.##
     * @return
     */
    public static String byteCalculation(String bytes, String pattern) {
        StringBuilder result = new StringBuilder();
        if (bytes != null && bytes.length() > 0) {
            double size = Double.parseDouble(bytes);
            int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
            DecimalFormat df = new DecimalFormat(pattern);
            double ret = ((size / Math.pow(1024, Math.floor(idx))));
            result.append(df.format(ret))
                    .append(" ")
                    .append(units.get(idx));
        } else {
            result.append("0 ")
                    .append(units.get(0));
        }
        return result.toString();
    }


    public static void main(String[] args) {
        System.out.println("바이트 표현 : " + byteCalculation("102400000", BYTE_CALC_PATTERN));
    }
}
