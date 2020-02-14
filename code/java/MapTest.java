package code.java;

import java.awt.geom.*;
import static java.lang.Math.*;

public class MapTest {
    /**
     * 地球半径 米
     */
    private final static double EARTH_RADIUS = 6370856.0;

    /**
     * 角度转弧度
     * 
     * @param d 角度
     * @return 弧度
     */
    private static double rad(double d) {
        return d * PI / 180.0;
    }

    /**
     * 计算两点距离
     * 
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 距离
     */
    public static double distance(Point2D.Double p1, Point2D.Double p2) {
        var a = rad(p1.x) - rad(p2.x);
        var b = rad(p1.y) - rad(p2.y);
        var s = 2 * asin(sqrt(pow(sin(a / 2), 2) + cos(rad(p1.x)) * cos(rad(p2.x)) * pow(sin(b / 2), 2)));
        return s * EARTH_RADIUS;
    }

}