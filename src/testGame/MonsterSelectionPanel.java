package testGame;

import gameCharacters.Creature;
import gameCharacters.Monster;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MonsterSelectionPanel extends JPanel {

    private final static int PANEL_WIDTH = GameFrame.FRAME_WIDTH - 15;
    public final static int NUMBER_OF_RATS = 3;
    public final static int IMAGE_SIZE = PANEL_WIDTH / NUMBER_OF_RATS;
    JRadioButton[] ratsButton;
    JButton chooseRatButton;
    private GameFrame gameFrame;

    public MonsterSelectionPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.setBounds(0, 0, GameFrame.FRAME_WIDTH - 15, GameFrame.FRAME_HEIGHT);

        createCharacterParametersPanel();
        createRatsImages();
        createRandomMonsters();
        createButtons();
    }

    private void createRatsImages() {
        JLabel panelDescription = new JLabel("Теперь нужно выбрать мышь, на которую будем нападать");
        add(panelDescription);

        JPanel ratsImgPanel = new JPanel(new GridLayout(1, 3));

        for (int i = 0; i < NUMBER_OF_RATS; i++) {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon("rat" + (i + 1) + ".png").getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
            JLabel label = new JLabel(imageIcon);
            ratsImgPanel.add(label);
        }
        this.add(ratsImgPanel);
    }

    // Получаем случайное число из диапазона, чтобы создать случайного монстра (мышь)
    private static int getRandomParameter(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // Для простоты будем создавать монстра со случайными параметрами
    private void createRandomMonsters() {
        gameFrame.monsters = new Monster[NUMBER_OF_RATS];
        for (int i = 0; i < NUMBER_OF_RATS; i++) {
            int minDamageParameter = getRandomParameter(1, Creature.MAX_ALLOWED_DAMAGE_PARAMETER - 1);
            int maxDamageParameter = getRandomParameter(minDamageParameter + 1, Creature.MAX_ALLOWED_DAMAGE_PARAMETER);
            gameFrame.monsters[i] = new Monster(
                    getRandomParameter(1, Creature.MAX_ALLOWED_ATTACK_PARAMETER),
                    getRandomParameter(1, Creature.MAX_ALLOWED_DEFENSE_PARAMETER),
                    getRandomParameter(1, Creature.MAX_ALLOWED_HEALTH),
                    minDamageParameter,
                    maxDamageParameter);
        }
    }

    // Создаем радиокнопки для выбора 1 из 3 мышей, на которую нужно напасть
    private void createButtons() {
        ratsButton = new JRadioButton[NUMBER_OF_RATS];
        ButtonGroup buttonGroup = new ButtonGroup();

        for (int i = 0; i < NUMBER_OF_RATS; i++) {
            String ratParameters = String.format(
                    "<html>" +
                            "&#8226; Атака: %d<br>" +
                            "&#8226; Защита: %d<br>" +
                            "&#8226; Здоровье: %d<br>" +
                            "&#8226; Урон: %d-%d<br>" +
                            "</html>",
                    gameFrame.monsters[i].getAttackParameter(),
                    gameFrame.monsters[i].getDefenseParameter(),
                    gameFrame.monsters[i].getMaxHealth(),
                    gameFrame.monsters[i].getMinDamageParameter(),
                    gameFrame.monsters[i].getMaxDamageParameter()
            );
            ratsButton[i] = new JRadioButton(ratParameters);
            Font font = ratsButton[i].getFont().deriveFont(Font.PLAIN);
            ratsButton[i].setFont(font);
            ratsButton[i].setPreferredSize(new Dimension(IMAGE_SIZE - 10, 90));
            // Добавляем кнопки в группу и на панель
            buttonGroup.add(ratsButton[i]);
            this.add(ratsButton[i]);
        }
        // Предустановленный выбор первой кнопки
        ratsButton[0].setSelected(true);
        // Обычная кнопка для выбора, на которую в GameFrame установлен слушатель
        chooseRatButton = new JButton("Выбрать");
        this.add(chooseRatButton);
    }

    // Выводим параметры персонажа, который был создан
    private void createCharacterParametersPanel() {
        JPanel characterParametersPanel = new JPanel(); // вынести?
        // if null
        String characterParameters = String.format(
                "<html>" +
                        "    <b>Текущие параметры персонажа</b>" +
                        "    <ul>" +
                        "        <li>Атака: %d</li>" +
                        "        <li>Защита: %d</li>" +
                        "        <li>Здоровье: %d/%d</li>" +
                        "        <li>Урон: %d-%d</li>" +
                        "        <li>Жив: %s</li>" +
                        "    </ul>" +
                        "</html>",
                gameFrame.player.getAttackParameter(),
                gameFrame.player.getDefenseParameter(),
                gameFrame.player.getCurrentHealth(),
                gameFrame.player.getMaxHealth(),
                gameFrame.player.getMinDamageParameter(),
                gameFrame.player.getMaxDamageParameter(),
                gameFrame.player.isDead() ? "нет" : "да"
        );

        JLabel characterParametersLabel = new JLabel(characterParameters);
        Font font = characterParametersLabel.getFont().deriveFont(Font.PLAIN);
        characterParametersLabel.setFont(font);
        characterParametersPanel.add(characterParametersLabel);

        add(characterParametersPanel);
    }
}
