package code.java;

import java.awt.geom.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Point2DTest {

    /**
     * 根据点集合绘制平面
     * 
     * @param points 平面的点
     * @return
     */
    public static GeneralPath drawPlane(List<Point2D.Float> points) {
        var path = new GeneralPath();
        if (points != null && points.size() >= 3) {
            path.moveTo(points.get(points.size() - 1).x, points.get(points.size() - 1).y);
            for (var i = 0; i < points.size(); i++) {
                path.lineTo(points.get(i).x, points.get(i).y);
            }
            path.closePath();
        }
        return path;
    }

    /**
     * 判断点 point 是否在平面 points 内
     * 
     * @param points
     * @param point
     * @return
     */
    public static boolean contains(List<Point2D.Float> points, Point2D.Float point) {
        return drawPlane(points).contains(point);
    }

    public static void main(String[] args) {
        var p0 = new Point2D.Float(0, 0);
        var p1 = new Point2D.Float(3, 3);
        var p2 = new Point2D.Float(3, 0);
        var p3 = new Point2D.Float(2, 2);
        var p4 = new Point2D.Float(5, 5);
        var p5 = new Point2D.Float(2, 1);
        var points = Stream.of(p0, p1, p2).collect(Collectors.toList());
        System.out.println(contains(points, p3));
        System.out.println(contains(points, p4));
        System.out.println(contains(points, p5));
    }
}