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

    public CreatePlayerPanel() {
        setBounds(30, 30, 500, 500);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("cat.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 200, 200);
        add(label);

        JPanel p = new JPanel(new GridLayout(5, 2, 15, 2));

        attackParameter = new CreatePlayerTextField();
        defenseParameter = new CreatePlayerTextField();
        maxHealth = new CreatePlayerTextField();
        minDamageParameter = new CreatePlayerTextField();
        maxDamageParameter = new CreatePlayerTextField();

//        attackParameter.setBounds(0, TEXT_FIELD_START_Y_DIMENSION,100,20);
        JLabel label1 = new JLabel("Атака (1-30):", SwingConstants.RIGHT);


        JLabel label2 = new JLabel("Защита (1-30):", SwingConstants.RIGHT);
        JLabel label3 = new JLabel("Здоровье (1-" + Creature.MAX_ALLOWED_HEALTH + "):", SwingConstants.RIGHT);
        JLabel label4 = new JLabel("Мин. Урон (от 1):", SwingConstants.RIGHT);
        JLabel label5 = new JLabel("Макс. Урон (до " + Creature.MAX_ALLOWED_DAMAGE_PARAMETER + "):", SwingConstants.RIGHT);
        p.add(label1);
        p.add(attackParameter);
        p.add(label2);
        p.add(defenseParameter);
        p.add(label3);
        p.add(maxHealth);
        p.add(label4);
        p.add(minDamageParameter);
        p.add(label5);
        p.add(maxDamageParameter);

        add(p);


        createPlayerButton = new JButton("Создать");
        createPlayerButton.setPreferredSize(new Dimension(100, 20));
        add(createPlayerButton);

    }
}
