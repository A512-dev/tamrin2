package graphics.view;

import logic.GameState;
import model.ObstacleModel;

import java.awt.*;

public class ObstacleView {
    private ObstacleModel model;

    public ObstacleView(ObstacleModel model) {
        this.model = model;
    }

    public void draw(Graphics2D g, Point center, double radius, int totalSectors) {
        /*
        double angle = (2 * Math.PI / totalSectors) * model.getSector();
        double dist = model.getDistance();
        int x1 = (int) (center.x + Math.cos(angle) * dist);
        int y1 = (int) (center.y + Math.sin(angle) * dist);

        int width = 20;
        g.setColor(Color.RED);
        g.fillRect(x1 - width / 2, y1 - width / 2, width, width);

         */
        double sectorAngle = 2 * Math.PI / totalSectors;
        int sector = model.getSector();

        double angle1 = sectorAngle * sector + GameState.getGlobalRotation();
        double angle2 = sectorAngle * (sector + 1) + GameState.getGlobalRotation();

        double dist = model.getDistance();
        double thickness = 20;

        // چهار نقطه‌ی ذوزنقه را می‌سازیم
        int[] xPoints = {
                (int) (center.x + Math.cos(angle1) * (dist - thickness)),
                (int) (center.x + Math.cos(angle1) * dist),
                (int) (center.x + Math.cos(angle2) * dist),
                (int) (center.x + Math.cos(angle2) * (dist - thickness))
        };

        int[] yPoints = {
                (int) (center.y + Math.sin(angle1) * (dist - thickness)),
                (int) (center.y + Math.sin(angle1) * dist),
                (int) (center.y + Math.sin(angle2) * dist),
                (int) (center.y + Math.sin(angle2) * (dist - thickness))
        };

        Polygon trapezoid = new Polygon(xPoints, yPoints, 4);
        g.setColor(Color.RED);
        g.fillPolygon(trapezoid);
    }
}
