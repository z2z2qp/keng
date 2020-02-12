package code.java;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberTest{
    /**
     * 四舍五入保留 newScale 位小数
     */
    public static Double round(final Double d, final int newScale) {
        final BigDecimal bd = new BigDecimal(d);
        return bd.setScale(newScale, RoundingMode.HALF_UP).doubleValue();
    }
}