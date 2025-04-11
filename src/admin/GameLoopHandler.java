package admin;


import logic.GameEngineModel;
import logic.GameState;

import static java.lang.Thread.sleep;

import javax.swing.*;
import java.time.LocalDateTime;

public class GameLoopHandler {
    private final GameEngineModel engine;
    private final JPanel gamePanel;
    private final Timer logicTimer;
    private final Timer renderTimer;

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        GameLoopHandler.gameOver = gameOver;
    }

    private static boolean gameOver = false;

    public GameLoopHandler(GameEngineModel engine, JPanel gamePanel) {
        this.engine = engine;
        this.gamePanel = gamePanel;


        // Logic update timer (60 FPS → every ~16ms)
        logicTimer = new Timer(16, e -> {

            engine.updateGame();
            if (engine.checkCollision()) {
                if (GameState.isHistoryEnabled) {
                    double finalScore = (System.currentTimeMillis() - engine.getStartTime() - engine.getTotalPausedDuration()) / 1000.0;
                    data.HistoryManager.saveRecord(new data.GameRecord(GameState.currentPlayerName, finalScore, LocalDateTime.now().toString()));
                }
                gameOver = true;
                System.out.println("Game Over");
                stop();
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
        //renderTimer.stop();
    }
}