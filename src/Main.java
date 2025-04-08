import admin.*;
import graphics.GamePanel;
import logic.GameEngineModel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        MainGameAdmin mainGameAdmin = new MainGameAdmin();
        mainGameAdmin.start();
        SwingUtilities.invokeLater(() -> {
            GameEngineModel engine = mainGameAdmin.getGameEngineModel();
            GamePanel panel = mainGameAdmin.getGamePanel();

            JFrame frame = new JFrame("Super Hexagon");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(panel);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            GameLoopHandler loop = new GameLoopHandler(engine, panel);
            loop.start();
        });
    }
	// write your code here
}
