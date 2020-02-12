package hole.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date {
    public static void main(String[] args) throws ParseException {
        var str = "YYYY-MM-dd";// 格式化最后一周跨年会有bug
        var str2 = "yyyy-MM-dd"; // 没有上述bug
        var sdf = new SimpleDateFormat(str);
        var sdf2 = new SimpleDateFormat(str2);
        var date = "2019-12-31";
        System.out.println(sdf.parse(date));
        System.out.println(sdf2.parse(date));
    }
}