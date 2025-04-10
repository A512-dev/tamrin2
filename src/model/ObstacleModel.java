package model;

import logic.GameEngineModel;
import logic.GameState;

import java.awt.*;
import java.awt.geom.Point2D;

import static logic.GameEngineModel.totalSectors;

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
        return distanceFromCenter <= PlayerModel.distanceFromCenter +20;
    }

    public double getDistance() {
        return distanceFromCenter;
    }

    public int getSector() {
        return sector;
    }
    public int getCurrentSector() {
        double rawAngle = getAngle() % 360;
        if (rawAngle < 0) rawAngle += 360;
        double sectorAngle = 360.0 / GameEngineModel.totalSectors;
        if (angle>-0.05)
            return 0;
        return (int)(rawAngle / sectorAngle)%totalSectors;
    }
}
