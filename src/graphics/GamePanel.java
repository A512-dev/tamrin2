package graphics;


import admin.GameLoopHandler;

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
    private double timePaused;

    private void togglePause() {
        isPaused = !isPaused;
        engine.setPaused(isPaused);
        if (isPaused) {
            resumeButton = new JButton("Resume");
            exitButton = new JButton("Exit to Menu");

            resumeButton.setBounds(getWidth() / 2 - 70, getHeight() / 2, 140, 30);
            exitButton.setBounds(getWidth() / 2 - 70, getHeight() / 2 + 40, 140, 30);

            resumeButton.addActionListener(e -> {
                isPaused = false;
                remove(resumeButton);
                remove(exitButton);
                engine.setPaused(isPaused);
                repaint();
                requestFocusInWindow();
            });

            exitButton.addActionListener(e -> {
                SwingUtilities.getWindowAncestor(this).dispose(); // Close game window
                new MainFrame().setVisible(true); // Return to menu (assumes MainFrame exists)
            });

            setLayout(null);
            add(resumeButton);
            add(exitButton);
            repaint();
        } else {
            remove(resumeButton);
            remove(exitButton);
            repaint();
            requestFocusInWindow();
        }
    }
    private boolean isPaused = false;
    private JButton resumeButton;
    private JButton exitButton;

    private Timer rightHoldTimer;
    private Timer leftHoldTimer;
    private final GameEngineModel engine;
    private final PlayerView playerView;
    private final PolygonCoreView coreView;
    public static final int width = 400;
    public static final int height = 600;
    public static final int radius = 100;

    private JButton gameOverExitButton;
    private boolean gameOver = false;
    private void handleGameOver() {
        gameOver = true;



        gameOverExitButton = new JButton("exit to menu");
        gameOverExitButton.setBackground(Color.PINK);
        gameOverExitButton.setBounds(getWidth() / 2 - 80, getHeight() / 2 + 40, 160, 30);
        gameOverExitButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(this).dispose(); // بستن پنجره
            gameOver = false;
            new MainFrame().setVisible(true); // بازگشت به منو
        });

        setLayout(null);
        add(gameOverExitButton);
        repaint();
    }

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

        // Draw player name
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString("بازیکن: " + logic.GameState.currentPlayerName, getWidth() - 160, 20);

        double elapsed = 0;
        if (isPaused) {
            elapsed = timePaused;
        }
        else {
            elapsed = (System.currentTimeMillis() - engine.getStartTime()) / 1000.00;
        }
        double best = engine.getBestTime();

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Time: " + String.format("%.01f", elapsed) + "s", 20, 30);
        g2.drawString("Best: " + String.format("%.01f", best) + "s", 20, 50);

        if (gameOver) {
            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 32));
            g2.drawString("Game Over", getWidth() / 2 - 90, getHeight() / 2 - 30);
        }
        if (GameLoopHandler.isGameOver() && !gameOver) {
            handleGameOver();
        }
//        System.out.println("collision:"+engine.checkCollision());
//        System.out.println("gameover:"+gameOver);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_S) {
            togglePause();
            timePaused = (System.currentTimeMillis() - engine.getStartTime()) / 1000.0;
            return;
        }
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

