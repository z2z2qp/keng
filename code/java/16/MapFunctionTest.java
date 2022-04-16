import java.util.*;

public class MapFunctionTest {

    public static void main(String[] args) {
        var map = new HashMap<String, String>();
        map.put("1", null);
        System.out.println(map);// print {1=null}

        // putIfAbsent 如果没有值则赋值
        var result = map.putIfAbsent("1", "2");
        System.out.println(result + ":" + map);// print null:{1=1}
        // putIfAbsent 如果没有值则赋值
        result = map.putIfAbsent("2", "3");
        System.out.println(result + ":" + map);// print null:{1=1,2=3}

        // computeIfAbsent 如果没有值则赋予计算结果值
        result = map.computeIfAbsent("2", k -> "4");
        result = map.computeIfAbsent("3", k -> "4");
        System.out.println(result + ":" + map);// print 4:{1=1,2=3,3=4}

        // computeIfPresent 如果存在值则赋予计算结果值
        result = map.computeIfPresent("4", (k, v) -> "4");
        result = map.computeIfPresent("2", (k, v) -> "5");
        System.out.println(result + ":" + map);// print 5:{1=1,2=5,3=4}

    }
}