/**
 * 新发现   可以直接使用 java Switch14.java 运行不用javac编译
 */
public class Switch14 {
    public static void main(String[] args) {
        var x = Integer.valueOf(args[0]);
        var i = switch (x) {
            case 0,1 -> 1;
            case 2 -> 2;
            default -> 3;
        };
        System.out.println(i);
    }
}