package graphics;

import javax.swing.*;
import java.awt.*;

public class NameInputPanel extends JPanel {
    private final MainFrame frame;
    private final JTextField nameField;

    public NameInputPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        setBackground(Color.GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("نام خود را وارد کنید:");
        label.setForeground(Color.WHITE);
        nameField = new JTextField(15);

        JButton startButton = new JButton("شروع بازی");
        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                logic.GameState.currentPlayerName = name; // ذخیره‌ی نام
                frame.startGame();
            } else {
                JOptionPane.showMessageDialog(this, "لطفاً نامی وارد کنید.");
            }
        });

        gbc.gridy = 0;
        add(label, gbc);
        gbc.gridy = 1;
        add(nameField, gbc);
        gbc.gridy = 2;
        add(startButton, gbc);
    }
}
