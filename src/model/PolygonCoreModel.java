package model;

public class PolygonCoreModel {
    private static final int radius = 100;
    private double globalRotation = 0;
    public double getGlobalRotation() {
        return globalRotation;
    }
    public void rotateGlobal(double delta) {
        globalRotation += delta;
    }

    public static int getRadius() {
        return radius;
    }
    private int sides = 6; // number of sides of the polygon

    public int getSides() {
        return sides;
    }

    public void setSides(int s) {
        this.sides = s;
    }
}
