package graphics;

import admin.MusicPlayer;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private final MainFrame frame;
    private JCheckBox musicCheckbox;
    private JCheckBox saveHistoryCheckbox;

    public SettingsPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        musicCheckbox = new JCheckBox("Music Sound");
        saveHistoryCheckbox = new JCheckBox("Save Game In History");

        musicCheckbox.setSelected(logic.GameState.isMusicEnabled);
        saveHistoryCheckbox.setSelected(logic.GameState.isHistoryEnabled);

        JButton backButton = new JButton("Return");
        backButton.addActionListener(e -> frame.showMenu());

        musicCheckbox.addActionListener(e -> {
            logic.GameState.isMusicEnabled = musicCheckbox.isSelected();
            if (musicCheckbox.isSelected()) {
                MusicPlayer.playLoop("file_example_WAV_1MG.wav");
            }
            else {
                MusicPlayer.stop();
            }
        });
        saveHistoryCheckbox.addActionListener(e -> logic.GameState.isHistoryEnabled = saveHistoryCheckbox.isSelected());

        add(musicCheckbox, gbc);
        add(saveHistoryCheckbox, gbc);
        gbc.gridy = 1;
        add(backButton, gbc);
    }
}
