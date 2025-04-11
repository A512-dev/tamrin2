import admin.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphics.GamePanel;
import graphics.MainFrame;
import logic.GameEngineModel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(new String[]{"Hello", "Jackson"});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
//        SwingUtilities.invokeLater(() -> {
//            MainGameAdmin mainGameAdmin = new MainGameAdmin();
//            mainGameAdmin.start();
//            GameEngineModel engine = mainGameAdmin.getGameEngineModel();
//            GamePanel panel = mainGameAdmin.getGamePanel();
//
//            JFrame frame = new JFrame("Super Hexagon");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setContentPane(panel);
//            frame.setSize(800, 600);
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//
//            GameLoopHandler loop = new GameLoopHandler(engine, panel);
//            loop.start();
//        });
    }
	// write your code here
}
