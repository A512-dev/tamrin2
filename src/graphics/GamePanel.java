package graphics;


import data.Database;
import graphics.view.ObstacleView;
import graphics.view.PlayerView;
import graphics.view.PolygonCoreView;
import logic.GameEngineModel;
import model.PlayerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private Timer rightHoldTimer;
    private Timer leftHoldTimer;
    private final GameEngineModel engine;
    private final PlayerView playerView;
    private final PolygonCoreView coreView;
    public static final int width = 400;
    public static final int height = 600;
    public static final int radius = 100;

    public static Point center = new Point(width / 2, height / 2);

    public GamePanel(GameEngineModel engine) {
        this.engine = engine;
        //this.playerView = new PlayerView(engine.getPlayer());
        this.playerView = new PlayerView();

        this.coreView = new PolygonCoreView(engine.getPolygonCore());
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        rightHoldTimer = new Timer(500, e -> engine.getPlayer().moveRight(engine.getTotalSectors()));
        leftHoldTimer = new Timer(500, e -> engine.getPlayer().moveLeft(engine.getTotalSectors()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.setBackground(Color.CYAN);
        this.setVisible(true);

        Point center = new Point(getWidth() / 2, getHeight() / 2);

        // Draw core polygon
        coreView.draw(g2, center, radius);

        // Draw player
        playerView.draw(g2, center, 120, engine.getPlayer().getAngle());

        // Draw obstacles
        for (var obstacle : engine.getObstacles()) {
            new ObstacleView(obstacle).draw(g2, center, radius + 20, engine.getTotalSectors());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (!rightHoldTimer.isRunning()) {
                engine.getPlayer().moveRight(engine.getTotalSectors());
                rightHoldTimer.start();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (!leftHoldTimer.isRunning()) {
                engine.getPlayer().moveLeft(engine.getTotalSectors());
                leftHoldTimer.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightHoldTimer.stop();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftHoldTimer.stop();
        }
    }
}

