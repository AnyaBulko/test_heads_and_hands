package testGame;

import gameCharacters.Creature;

import javax.swing.*;
import java.awt.*;

public class CreatePlayerPanel extends JPanel {
    CreatePlayerTextField defenseParameter;
    CreatePlayerTextField attackParameter;
    CreatePlayerTextField maxHealth;
    CreatePlayerTextField minDamageParameter;
    CreatePlayerTextField maxDamageParameter;
    JButton createPlayerButton;
    JPanel parametersPanel;

    // Форма для создания персонажа
    public CreatePlayerPanel() {
        this.setBounds(0, 0, GameFrame.FRAME_WIDTH - 15, GameFrame.FRAME_HEIGHT);
        JLabel description = new JLabel("Создание персонажа", SwingConstants.CENTER);
        description.setPreferredSize(new Dimension(GameFrame.FRAME_WIDTH, 20));
        add(description);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("cat.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 200, 200);
        add(label);

        // Панель с полями для ввода
        parametersPanel = new JPanel(new GridLayout(5, 2, 15, 2));
        createTextField();
        createLabels();
        add(parametersPanel);
        createButton();
        add(createPlayerButton);
    }

    private void createLabels() {
        JLabel label1 = new JLabel("Атака (1-30):", SwingConstants.RIGHT);
        JLabel label2 = new JLabel("Защита (1-30):", SwingConstants.RIGHT);
        JLabel label3 = new JLabel("Здоровье (1-" + Creature.MAX_ALLOWED_HEALTH + "):", SwingConstants.RIGHT);
        JLabel label4 = new JLabel("Мин. Урон (от 1):", SwingConstants.RIGHT);
        JLabel label5 = new JLabel("Макс. Урон (до " + Creature.MAX_ALLOWED_DAMAGE_PARAMETER + "):", SwingConstants.RIGHT);

        parametersPanel.add(label1);
        parametersPanel.add(attackParameter);
        parametersPanel.add(label2);
        parametersPanel.add(defenseParameter);
        parametersPanel.add(label3);
        parametersPanel.add(maxHealth);
        parametersPanel.add(label4);
        parametersPanel.add(minDamageParameter);
        parametersPanel.add(label5);
        parametersPanel.add(maxDamageParameter);
    }

    private void createTextField() {
        attackParameter = new CreatePlayerTextField();
        defenseParameter = new CreatePlayerTextField();
        maxHealth = new CreatePlayerTextField();
        minDamageParameter = new CreatePlayerTextField();
        maxDamageParameter = new CreatePlayerTextField();
    }

    private void createButton() {
        createPlayerButton = new JButton("Создать");
        createPlayerButton.setPreferredSize(new Dimension(100, 20));
    }
}
