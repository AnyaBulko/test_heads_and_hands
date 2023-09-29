package testGame;

import gameCharacters.Creature;
import gameCharacters.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    private static final int X_DIMENSION = 800;
    private static final int Y_DIMENSION = 500;
    private CreatePlayerPanel createPlayerPanel;
    private Creature player;

    public GameFrame() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(X_DIMENSION, Y_DIMENSION);
        setResizable(false);
        setTitle("Test game");
        ImageIcon imageIcon = new ImageIcon("cat.png");
        setIconImage(imageIcon.getImage());

        JLabel title = new JLabel("Test game");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0, 0, 500, 30);
        getContentPane().setBackground(new Color(230, 230, 250));
        setLayout(null);

        add(title);
        createPlayerPanel = new CreatePlayerPanel();
        add(createPlayerPanel);

        createPlayerPanel.createPlayerButton.addActionListener(this);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Создание персонажа
        if (e.getSource() == createPlayerPanel.createPlayerButton) {
            System.out.println(createPlayerPanel.attackParameter.getText());
            System.out.println(createPlayerPanel.defenseParameter.getText());
            System.out.println(createPlayerPanel.maxHealth.getText());
            System.out.println(createPlayerPanel.minDamageParameter.getText());
            System.out.println(createPlayerPanel.maxDamageParameter.getText());
            try {
                player = new Player(Integer.parseInt(createPlayerPanel.attackParameter.getText()),
                        Integer.parseInt(createPlayerPanel.defenseParameter.getText()),
                        Integer.parseInt(createPlayerPanel.maxHealth.getText()),
                        Integer.parseInt(createPlayerPanel.minDamageParameter.getText()),
                        Integer.parseInt(createPlayerPanel.maxDamageParameter.getText()));
                System.out.println(player);
                createPlayerPanel.setVisible(false);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this,
                        "В каждом из полей должно быть число из указанного диапазона",
                        "Ошибка создания персонажа", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(this,
                        exception.getMessage(),
                        "Ошибка создания персонажа", JOptionPane.WARNING_MESSAGE);
                System.out.println();
            }
        }
    }
}
