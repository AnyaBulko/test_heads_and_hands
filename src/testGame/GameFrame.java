package testGame;

import gameCharacters.Monster;
import gameCharacters.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    public static final int FRAME_WIDTH = 515;
    public static final int FRAME_HEIGHT = 590;
    private CreatePlayerPanel createPlayerPanel;
    private MonsterSelectionPanel monsterPanel;
    private FightPanel fightPanel;
    public Player player;
    public Monster[] monsters;
    public Monster selectedMonster;

    public GameFrame() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setTitle("Test game");
        ImageIcon imageIcon = new ImageIcon("cat.png");
        setIconImage(imageIcon.getImage());

        getContentPane().setBackground(new Color(230, 230, 250));
        setLayout(null);

        createPlayerPanel = new CreatePlayerPanel();
        add(createPlayerPanel);

        createPlayerPanel.createPlayerButton.addActionListener(this);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Создание персонажа
        if (e.getSource() == createPlayerPanel.createPlayerButton) {
            try {
                player = new Player(Integer.parseInt(createPlayerPanel.attackParameter.getText()),
                        Integer.parseInt(createPlayerPanel.defenseParameter.getText()),
                        Integer.parseInt(createPlayerPanel.maxHealth.getText()),
                        Integer.parseInt(createPlayerPanel.minDamageParameter.getText()),
                        Integer.parseInt(createPlayerPanel.maxDamageParameter.getText()));
                // После создания персонажа, панель с созданием убирается
                createPlayerPanel.setVisible(false);
                // И создается следующая с выбором монстра
                monsterPanel = new MonsterSelectionPanel(this);
                this.add(monsterPanel);
                monsterPanel.chooseRatButton.addActionListener(this);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this,
                        "В каждом из полей должно быть число из указанного диапазона",
                        "Ошибка создания персонажа", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(this,
                        exception.getMessage(),
                        "Ошибка создания персонажа", JOptionPane.WARNING_MESSAGE);
            }
        }

        // Выбор монстра для последующего боя
        if (monsterPanel != null && e.getSource() == monsterPanel.chooseRatButton) {
            for (int i = 0; i < MonsterSelectionPanel.NUMBER_OF_RATS; i++)
                if (monsterPanel.ratsButton[i].isSelected()) {
                    selectedMonster = monsters[i];
                    monsterPanel.setVisible(false);
                    fightPanel = new FightPanel(this, i);
                    this.add(fightPanel);

                    fightPanel.attackButton.addActionListener(this);
                    fightPanel.healButton.addActionListener(this);
                    break;
                }

        }

        // Обработка кнопки "Атаковать"
        if (fightPanel != null && e.getSource() == fightPanel.attackButton) {
            playerAttacksMonster();
            monsterAttacksPlayer();
            fightPanel.updateParameters();

            if (isGameOver()) {
                showGameOverMessage();
                setFightPanelGameOver();
            }
        }

        // Обработка кнопки "Вылечиться"
        if (fightPanel != null && e.getSource() == fightPanel.healButton) {
            // В случае неуспешного лечения (если не осталось попыток), выводим сообщение
            if (player.heal()) {
                // Получаем из лога сообщение с информацией о лечении
                fightPanel.updateFightDescriptionText("\nХод игрока:\n");
                fightPanel.updateFightDescriptionText(player.getLog().toString());

                // Если игрок лечится, монстр атакует
                monsterAttacksPlayer();
                fightPanel.updateParameters();

                if (isGameOver()) {
                    showGameOverMessage();
                    setFightPanelGameOver();
                }
            } else fightPanel.updateFightDescriptionText(player.getLog().toString());
        }
    }

    private void playerAttacksMonster() {
        player.attack(selectedMonster);
        fightPanel.updateFightDescriptionText("\nХод игрока:\n");
        fightPanel.updateFightDescriptionText(player.getLog().toString());
    }

    private void monsterAttacksPlayer() {
        selectedMonster.attack(player);
        fightPanel.updateFightDescriptionText("\nХод монстра:\n");
        fightPanel.updateFightDescriptionText(selectedMonster.getLog().toString());
    }

    private boolean isGameOver() {
        return player.isDead() || selectedMonster.isDead();
    }

    private void showGameOverMessage() {
        JOptionPane.showMessageDialog(this,
                selectedMonster.isDead() ? "Монстр побежден!" : "Вас убила мышь...",
                "Игра окончена", JOptionPane.PLAIN_MESSAGE);
    }

    private void setFightPanelGameOver() {
        fightPanel.buttonsGroup.setVisible(false);
        fightPanel.updateFightDescriptionText("\nИгра окончена");
    }
}
