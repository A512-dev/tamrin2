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

        JLabel label = new JLabel("enter a name");
        label.setForeground(Color.WHITE);
        nameField = new JTextField(15);

        JButton startButton = new JButton("START");
        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                logic.GameState.currentPlayerName = name;
                frame.startGame();
            } else {
                JOptionPane.showMessageDialog(this, "please enter a name");
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
