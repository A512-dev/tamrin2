package graphics.view;

import logic.GameState;
import model.PlayerModel;

import java.awt.*;
import java.awt.geom.Point2D;

import static graphics.GamePanel.center;

public class PlayerView {
    private PlayerModel model;

    public PlayerView() {
    }

    public void draw(Graphics2D g, Point center, double radius, double angle) {
//        double angleRad = Math.toRadians(model.getAngle()) + GameState.getGlobalRotation();
//        double angleRad2 = Math.toRadians(model.getAngle()+120) + GameState.getGlobalRotation();
//        double angleRad3 = Math.toRadians(model.getAngle()+240) + GameState.getGlobalRotation();
        double angleRad = Math.toRadians(angle) + GameState.getGlobalRotation();
        double angleRad2 = Math.toRadians(angle+120) + GameState.getGlobalRotation();
        double angleRad3 = Math.toRadians(angle+240) + GameState.getGlobalRotation();
        //center of Triangle
        double x =  (center.x + Math.cos(angleRad) * radius);
        double y =  (center.y + Math.sin(angleRad) * radius);
        //System.out.println("XY== "+x+"________"+y);

        int size = 10;
        Polygon triangle = new Polygon();
//        System.out.println((int) (x + Math.cos(angleRad)*size)+"______"+ (int) (y + Math.cos(angleRad)*size));
//        System.out.println((int) (x + Math.cos(angleRad2)*size)+"______"+(int) (y + Math.cos(angleRad2)*size));
//        System.out.println((int) (x - Math.cos(angleRad2)*size)+"______"+(int) (y - Math.cos(angleRad2)*size));
        triangle.addPoint((int) (x + Math.cos(angleRad)*size), (int) (y + Math.sin(angleRad)*size));
        triangle.addPoint((int) (x + Math.cos(angleRad2)*size), (int) (y + Math.sin(angleRad2)*size));
        triangle.addPoint((int) (x + Math.cos(angleRad3)*size), (int) (y + Math.sin(angleRad3)*size));
        //triangle.addPoint((int) model.findThePoints()[0].getX(), (int) model.findThePoints()[0].getY());
        //triangle.addPoint((int) model.findThePoints()[1].getX(), (int) model.findThePoints()[1].getY());
        //triangle.addPoint((int) model.findThePoints()[2].getX(), (int) model.findThePoints()[2].getY());

        g.setColor(Color.YELLOW);
        g.fillPolygon(triangle);
    }
    /*public void draw(Graphics2D g, PlayerModel model) {
        //center of Triangle
        //double x =  (center.x + Math.cos(angleRad) * model.);
        //double y =  (center.y + Math.sin(angleRad) * radius);
        //System.out.println("XY== "+x+"________"+y);

        int size = 10;
        Polygon triangle = new Polygon();
//        System.out.println((int) (x + Math.cos(angleRad)*size)+"______"+ (int) (y + Math.cos(angleRad)*size));
//        System.out.println((int) (x + Math.cos(angleRad2)*size)+"______"+(int) (y + Math.cos(angleRad2)*size));
//        System.out.println((int) (x - Math.cos(angleRad2)*size)+"______"+(int) (y - Math.cos(angleRad2)*size));
        /*triangle.addPoint((int) (x + Math.cos(angleRad)*size), (int) (y + Math.sin(angleRad)*size));
        triangle.addPoint((int) (x + Math.cos(angleRad2)*size), (int) (y + Math.sin(angleRad2)*size));
        triangle.addPoint((int) (x + Math.cos(angleRad3)*size), (int) (y + Math.sin(angleRad3)*size));
        Point2D[] point2Ds = model.findThePoints();
        triangle.addPoint((int) point2Ds[0].getX(), (int) point2Ds[0].getY());
        triangle.addPoint((int) point2Ds[1].getX(), (int) point2Ds[1].getY());
        triangle.addPoint((int) point2Ds[2].getX(), (int) point2Ds[2].getY());

        g.setColor(Color.YELLOW);
        g.fillPolygon(triangle);
    }*/

}