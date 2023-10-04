package testGame;

import gameCharacters.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static testGame.MonsterSelectionPanel.IMAGE_SIZE;

public class FightPanel extends JPanel {
    private JLabel playerParameters;
    private JLabel monsterParameters;
    GameFrame gameFrame;
    JPanel buttonsGroup;
    JButton attackButton;
    JButton healButton;
    private JTextArea fightDescription;
    private StringBuilder fightDescriptionText;

    // Панель, отображающая игрока и выбранного монстра (мышь).
    // Также здесь осуществляется бой между ними с подробным описание действий.
    public FightPanel(GameFrame gameFrame, int selectedMonster) {
        this.setBounds(0, 0, GameFrame.FRAME_WIDTH - 15, GameFrame.FRAME_HEIGHT);
        this.gameFrame = gameFrame;

        createParametersPanel(selectedMonster);
        createTextArea();
        createButtons();
    }

    private void createParametersPanel(int selectedMonster) {
        JPanel jPanel = new JPanel(new GridLayout(2, 3));

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("rat" + (selectedMonster + 1) + ".png").getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(imageIcon);

        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("cat.png").getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
        JLabel label1 = new JLabel(imageIcon1);

        jPanel.add(label1);
        jPanel.add(new JLabel("VS", SwingConstants.CENTER));
        jPanel.add(label);

        playerParameters = new JLabel();
        playerParameters.setHorizontalAlignment(SwingConstants.CENTER);
        monsterParameters = new JLabel();
        monsterParameters.setHorizontalAlignment(SwingConstants.CENTER);
        updateParameters();
        jPanel.add(playerParameters);
        jPanel.add(new JLabel(""));
        jPanel.add(monsterParameters);

        playerParameters.setFont(playerParameters.getFont().deriveFont(Font.PLAIN));
        playerParameters.setVerticalAlignment(SwingConstants.TOP);
        monsterParameters.setFont(monsterParameters.getFont().deriveFont(Font.PLAIN));
        monsterParameters.setVerticalAlignment(SwingConstants.TOP);

        this.add(jPanel);
    }

    private void createTextArea() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        fightDescriptionText = new StringBuilder("За один ход можно либо атаковать, либо вылечиться на 30%.\n");
        fightDescription = new JTextArea(fightDescriptionText.toString());
        fightDescription.setBorder(new LineBorder(Color.black));
        fightDescription.setEditable(false);
        fightDescription.setFocusable(false);

        scrollPane.setPreferredSize(new Dimension(450, 150));
        scrollPane.getViewport().add(fightDescription);
        this.add(scrollPane);
    }

    private void createButtons() {
        buttonsGroup = new JPanel();
        attackButton = new JButton("Атаковать");
        healButton = new JButton("Вылечиться");
        buttonsGroup.add(attackButton);
        buttonsGroup.add(healButton);
        this.add(buttonsGroup);
    }

    public void updateParameters() {
        String playerParameters = String.format(
                "<html>" +
                        "&#8226; Атака: %d<br>" +
                        "&#8226; Защита: %d<br>" +
                        "&#8226; Здоровье: %d/%d<br>" +
                        "&#8226; Урон: %d-%d<br>" +
                        "&#8226; Жив: %s<br>" +
                        "Доступно лечений: %d/%d" +
                        "</html>",
                gameFrame.player.getAttackParameter(),
                gameFrame.player.getDefenseParameter(),
                gameFrame.player.getCurrentHealth(),
                gameFrame.player.getMaxHealth(),
                gameFrame.player.getMinDamageParameter(),
                gameFrame.player.getMaxDamageParameter(),
                gameFrame.player.isDead() ? "нет" : "да",
                gameFrame.player.getCurrentNumberOfHealing(),
                Player.NUMBER_OF_HEALING
        );
        this.playerParameters.setText(playerParameters);

        String monsterParameters = String.format(
                "<html>" +
                        "&#8226; Атака: %d<br>" +
                        "&#8226; Защита: %d<br>" +
                        "&#8226; Здоровье: %d/%d<br>" +
                        "&#8226; Урон: %d-%d<br>" +
                        "&#8226; Жив: %s<br>" +
                        "</html>",
                gameFrame.selectedMonster.getAttackParameter(),
                gameFrame.selectedMonster.getDefenseParameter(),
                gameFrame.selectedMonster.getCurrentHealth(),
                gameFrame.selectedMonster.getMaxHealth(),
                gameFrame.selectedMonster.getMinDamageParameter(),
                gameFrame.selectedMonster.getMaxDamageParameter(),
                gameFrame.selectedMonster.isDead() ? "нет" : "да"
        );
        this.monsterParameters.setText(monsterParameters);

    }

    public void updateFightDescriptionText(String string) {
        fightDescription.setText(fightDescriptionText.append(string).toString());
    }
}
