package graphics;

import admin.GameLoopHandler;
import admin.MainGameAdmin;
import admin.MusicPlayer;
import logic.GameEngineModel;
import logic.GameState;

import javax.swing.*;

public class MainFrame extends JFrame {
    private MenuPanel menuPanel;
    private GamePanel gamePanel;

    public MainFrame() {
        if (GameState.isMusicEnabled)
            MusicPlayer.playLoop("file_example_WAV_1MG.wav");
        GameLoopHandler.setGameOver(false);
        setTitle("Polygon Survival Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        showMenu();
    }

    public void showMenu() {
        if (gamePanel != null) remove(gamePanel);
        menuPanel = new MenuPanel(this);
        setContentPane(menuPanel);
        revalidate();
        repaint();
        System.out.println();
    }

    public void startGame() {
        MainGameAdmin mainGameAdmin = new MainGameAdmin();
        mainGameAdmin.start();
        GameEngineModel engine = mainGameAdmin.getGameEngineModel();
        GamePanel gamePanel = mainGameAdmin.getGamePanel();


        GameLoopHandler loop = new GameLoopHandler(engine, gamePanel);
        loop.start();

        setContentPane(gamePanel);
        gamePanel.requestFocusInWindow();
        revalidate();
        repaint();
    }
    public void showNameInput() {
        if (gamePanel != null) remove(gamePanel);
        setContentPane(new NameInputPanel(this));
        revalidate();
        repaint();
    }
    public void showSettings() {
        setContentPane(new SettingsPanel(this));
        revalidate();
        repaint();
    }


}
