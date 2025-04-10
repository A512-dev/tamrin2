package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {
    private final MainFrame frame;

    public MenuPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton startButton = new JButton("New Game");
        JButton recordButton = new JButton("Best Record");
        JButton historyButton = new JButton("History");
        JButton settingsButton = new JButton("Settings");
        JButton exitButton = new JButton("Exit");

        //startButton.addActionListener(e -> frame.startGame());
        startButton.addActionListener(e -> frame.showNameInput());
        recordButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Record: " + logic.GameState.getBestRecord() + " seconds"));

        historyButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "تاریخچه بازی‌ها هنوز پیاده‌سازی نشده."));
        settingsButton.addActionListener(e -> frame.showSettings());
        exitButton.addActionListener(e -> System.exit(0));

        add(startButton, gbc);
        add(recordButton, gbc);
        add(historyButton, gbc);
        add(settingsButton, gbc);
        add(exitButton, gbc);
    }
}
