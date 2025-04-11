package graphics;

import data.GameRecord;
import data.HistoryManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

        historyButton.addActionListener(e -> showHistory());
        settingsButton.addActionListener(e -> frame.showSettings());
        exitButton.addActionListener(e -> System.exit(0));

        add(startButton, gbc);
        add(recordButton, gbc);
        add(historyButton, gbc);
        add(settingsButton, gbc);
        add(exitButton, gbc);
    }
    private void showHistory() {
        List<GameRecord> history = HistoryManager.loadOddHistory();

        StringBuilder sb = new StringBuilder();
        for (GameRecord r : history) {
            sb.append("player: ").append(r.getPlayerName())
                    .append(" | score: ").append(String.format("%.1f", r.getScore()))
                    .append(" | time: ").append(r.getTimeOfPlay())
                    .append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "history", JOptionPane.PLAIN_MESSAGE);
    }
}
