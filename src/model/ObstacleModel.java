package model;

import graphics.GamePanel;
import logic.GameEngineModel;
import logic.GameState;

import java.awt.*;
import java.awt.geom.Point2D;

public class ObstacleModel {
    private double distanceFromCenter;
    private double speed;
    private Point center;
    private double angle;
    private double rotationSpeed = 2.5;
    Point2D point1, point2, point3, point4;
    public static final double thickness = 20;

    public double getAngle() {
        return (2 * Math.PI / GameEngineModel.totalSectors + GameState.getGlobalRotation()) * getSector();
    }

    private int sector; // which sector the obstacle is in

    public ObstacleModel(double distanceFromCenter, double speed, int sector) {
        this.distanceFromCenter = distanceFromCenter;
        this.speed = speed;
        this.sector = sector;
    }
    public void rotateRight() {
        angle += rotationSpeed;
        Point2D[] point2DS = findThePoints();
        point1 = point2DS[0];
        point2 = point2DS[1];
        point3 = point2DS[2];
        point4 = point2DS[3];
    }

    private Point2D[] findThePoints() {
        return null;
    }

    public void update() {
        distanceFromCenter -= speed;
    }

    public boolean isOffScreen() {
        return distanceFromCenter <= PlayerModel.radius+20;
    }

    public double getDistance() {
        return distanceFromCenter;
    }

    public int getSector() {
        return sector;
    }
}
