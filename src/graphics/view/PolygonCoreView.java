package graphics.view;

import graphics.GamePanel;
import logic.GameState;
import model.PolygonCoreModel;

import java.awt.*;

public class PolygonCoreView {
    private PolygonCoreModel model;

    public PolygonCoreView(PolygonCoreModel model) {
        this.model = model;
    }

    public void draw(Graphics2D g, Point center, int radius) {
        /*
        int sides = model.getSides();
        Polygon p = new Polygon();
        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides;
            int x = (int) (center.x + Math.cos(angle) * radius);
            int y = (int) (center.y + Math.sin(angle) * radius);
            p.addPoint(x, y);
        }
        g.setColor(Color.GRAY);
        g.drawPolygon(p);

         */
        int sides = model.getSides();
        int outerRadius = radius + GamePanel.height; // sector radius

        // Draw colored sectors outside the hexagon
        for (int i = 0; i < sides; i++) {
            double angle1 = 2 * Math.PI * i / sides + GameState.getGlobalRotation();
            double angle2 = 2 * Math.PI * (i + 1) / sides + GameState.getGlobalRotation();

            int[] xPoints = {
                    (int) (center.x + Math.cos(angle1) * radius),
                    (int) (center.x + Math.cos(angle1) * outerRadius),
                    (int) (center.x + Math.cos(angle2) * outerRadius),
                    (int) (center.x + Math.cos(angle2) * radius)
            };
            int[] yPoints = {
                    (int) (center.y + Math.sin(angle1) * radius),
                    (int) (center.y + Math.sin(angle1) * outerRadius),
                    (int) (center.y + Math.sin(angle2) * outerRadius),
                    (int) (center.y + Math.sin(angle2) * radius)
            };

            Polygon sectorShape = new Polygon(xPoints, yPoints, 4);
            float hue = ((System.currentTimeMillis() % 5000L) / 5000f + (float) i / sides) % 1.0f;
            g.setColor(Color.getHSBColor(hue, 0.6f, 0.85f));
            //g.setColor(Color.getHSBColor((float) i / sides, 0.6f, 0.85f));
            g.fillPolygon(sectorShape);
        }

        // Draw central polygon (hexagon) in solid gray
        Polygon hex = new Polygon();
        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides + GameState.getGlobalRotation();
            int x = (int) (center.x + Math.cos(angle) * radius);
            int y = (int) (center.y + Math.sin(angle) * radius);
            hex.addPoint(x, y);
        }

        //g.setColor(Color.BLUE);
        float hue = ((System.currentTimeMillis() % 5000L) / 5000f) % 1.0f;
        g.setColor(Color.getHSBColor(hue, 0.6f, 0.3f));
        g.fillPolygon(hex);

        g.setColor(Color.BLACK);
        g.drawPolygon(hex);
    }
}

