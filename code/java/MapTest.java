package code.java;

import java.awt.geom.*;
import static java.lang.Math.*;

public class MapTest {
    /**
     * 地球半径 米
     */
    private final static double EARTH_RADIUS = 6370856.0;

    /**
     * 计算两点距离
     * 
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 距离
     */
    public static double distance(Point2D.Double p1, Point2D.Double p2) {
        var a = toRadians(p1.x) - toRadians(p2.x);
        var b = toRadians(p1.y) - toRadians(p2.y);
        var s = 2 * asin(sqrt(pow(sin(a / 2), 2) + cos(toRadians(p1.x)) * cos(toRadians(p2.x)) * pow(sin(b / 2), 2)));
        return s * EARTH_RADIUS;
    }

}