package admin;

import data.Database;
import logic.GameEngineModel;

import static java.lang.Thread.sleep;

import javax.swing.*;

public class GameLoopHandler {
    private final GameEngineModel engine;
    private final JPanel gamePanel;
    private final Timer logicTimer;
    private final Timer renderTimer;

    public GameLoopHandler(GameEngineModel engine, JPanel gamePanel) {
        this.engine = engine;
        this.gamePanel = gamePanel;

        // Logic update timer (60 FPS → every ~16ms)
        logicTimer = new Timer(16, e -> {
            engine.updateGame();

            if (engine.checkCollision()) {
                System.out.println("Game Over");
                stop(); // اگر برخورد شد، بازی متوقف شود
            }
        });

        // Render update timer (60 FPS → every ~16ms)
        renderTimer = new Timer(32, e -> gamePanel.repaint());
    }

    public void start() {
        logicTimer.start();
        renderTimer.start();
    }

    public void stop() {
        logicTimer.stop();
        renderTimer.stop();
    }
}