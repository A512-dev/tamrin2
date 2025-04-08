package model;

import graphics.GamePanel;
import logic.GameEngineModel;
import logic.GameState;

import java.awt.*;
import java.awt.geom.Point2D;

import static graphics.GamePanel.center;
import static graphics.GamePanel.radius;
import static javax.swing.JComponent.getDefaultLocale;
import static logic.GameEngineModel.totalSectors;

// ===== Player =====
public class PlayerModel {
    private double targetAngle;

    public PlayerModel(double angle) {
        this.angle = angle;
    }

    private double angle;
    private final double rotationSpeed = 0;
    public Point2D point1, point2, point3;
    public int rightTurn = 0, leftTurn = 0;
    public boolean rightTurnClicked, leftTurnClicked;



    private Point2D center;
    public static final int radius = GamePanel.radius + 20;
    public static final int size = 10;
    private int currentSector;
    private int targetSector;
    private double transitionSpeed = 0.1;


    public PlayerModel() {

        //center of Triangle

        //System.out.println("XY== "+x+"________"+y);
//        System.out.println((int) (x + Math.cos(angleRad)*size)+"______"+ (int) (y + Math.cos(angleRad)*size));
//        System.out.println((int) (x + Math.cos(angleRad2)*size)+"______"+(int) (y + Math.cos(angleRad2)*size));
//        System.out.println((int) (x - Math.cos(angleRad2)*size)+"______"+(int) (y - Math.cos(angleRad2)*size));

    }

    public Point2D[] findThePoints() {
        double base = angle;
        double angleRad = Math.toRadians(base) + GameState.getGlobalRotation();
        double angleRad2 = Math.toRadians(base+120) + GameState.getGlobalRotation();
        double angleRad3 = Math.toRadians(base+240) + GameState.getGlobalRotation();
        double x =  (GamePanel.center.x + Math.cos(angleRad) * GamePanel.radius);
        double y =  (GamePanel.center.y + Math.sin(angleRad) * GamePanel.radius);
        point1 = new Point2D.Double (x + Math.cos(angleRad)*size,  (y + Math.sin(angleRad)*size));
        point2 = new Point2D.Double((x + Math.cos(angleRad2)*size),  (y + Math.sin(angleRad2)*size));
        point3 = new Point2D.Double( (x + Math.cos(angleRad3)*size),  (y + Math.sin(angleRad3)*size));
        return new Point2D[]{point1, point2, point3};
    }
    public void moveRight(int totalSectors) {
        /*if (currentSector == targetSector) {
            targetSector = (targetSector + 1) % totalSectors;
        }

         */
        targetSector = getCurrentSector() +1;
        targetSector %= totalSectors;
        rightTurnClicked = true;
        System.out.println("nnvnvnvvnv" + targetSector+"________________"+getCurrentSector());
    }
    public int getCurrentSector() {
        double rawAngle = getAngle() % 360;
        if (rawAngle < 0) rawAngle += 360;
        double sectorAngle = 360.0 / GameEngineModel.totalSectors;
        if (angle>-0.05)
            return 0;
        return (int)(rawAngle / sectorAngle)%totalSectors;
    }

    public void moveLeft(int totalSectors) {
//        if (currentSector == targetSector) {
//            targetSector = (targetSector - 1 + totalSectors) % totalSectors;
//        }
        currentSector = getCurrentSector();
        targetSector = currentSector -1;
        targetSector %= totalSectors;
        leftTurnClicked = true;
        System.out.println("nnvnvnvvnv" + targetSector+"________________"+getCurrentSector());
    }

    public void rotate() {
        System.out.println("PPPPPPPP:"+ angle);
        System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"+ getCurrentSector());
        angle += rotationSpeed;
        if (rightTurnClicked || leftTurnClicked) {
            int sectorAngle = (int) GameEngineModel.sectorAngle;
            if ((targetSector - getCurrentSector())%totalSectors == 1 || (targetSector - getCurrentSector())%totalSectors == -5) {
                if (rightTurn==0) {
                    targetAngle = angle + 60;
                    System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
                    rightTurn++;
                }
            }
            else if ( (targetSector - getCurrentSector())%totalSectors == 5 || (targetSector - getCurrentSector())%totalSectors == -1) {
                if (leftTurn==0) {
                    targetAngle = angle - 60;
                    System.out.println("pppppppppppppppppppppppppp" + targetAngle);
                    leftTurn++;
                }
            }
            targetAngle %= 360;

            double diff = targetAngle - angle;
            System.out.println("targetAngel:"+targetAngle);
            System.out.println("Angle:"+angle);
            System.out.println("dif:"+ diff);

            // Normalize difference to [-180, 180]
            if (diff > 180) diff -= 360;
            if (diff < -180) diff += 360;

            if (diff<0)
                angle += -60 * transitionSpeed;
            else if (diff>0)
                angle += 60 * transitionSpeed;
//            if (getCurrentSector() == targetSector) {
//                System.out.println(targetSector);
//                angle = targetAngle;
//                //System.out.println(getCurrentSector());
//            }
            // Snap to target if close enough
             if (Math.abs(diff) < 5) {
                angle = targetAngle;
                System.out.println("angle: "+angle);
                targetSector = getCurrentSector();
                System.out.println(targetSector);
                leftTurn = 0;
                rightTurn = 0;
                leftTurnClicked = false;
                rightTurnClicked = false;
            }
        }
        //findThePoints();
    }

    public double getAngle() {
        return angle;
    }
}