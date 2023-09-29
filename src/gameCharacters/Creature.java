package gameCharacters;

import java.util.Random;


public abstract class Creature {

    static final int MIN_NUMBER_FOR_SUCCESS = 5;
    public static final int MAX_ALLOWED_HEALTH = 100;
    public static final int MAX_ALLOWED_DAMAGE_PARAMETER = 30;
    /*
     * У Существа есть параметры Атака и Защита. Это целые числа от 1 до 30.
     */
    private int attackParameter;     // от 1 до 30
    private int defenseParameter;    // от 1 до 30
    /*
     * Натуральное число от 0 до N
     * Если Здоровье становится равным 0, то Существо умирает.
     *
     * Игрок может себя исцелить до 4-х раз на 30% от максимального Здоровья.
     */
    private int maxHealth;
    private int currentHealth;

    /*
     * У Существа есть параметр Урон. Это диапазон натуральных чисел M - N. Например, 1-6.
     */
    private int minDamageParameter;
    private int maxDamageParameter;

    private boolean isDead = false;

    public Creature(int attackParameter, int defenseParameter, int maxHealth, int minDamageParameter, int maxDamageParameter) {
        if (attackParameter > 30 || attackParameter < 1)
            throw new IllegalArgumentException("Параметр Атака должен быть в диапазоне от 1 до 30");
        if (defenseParameter > 30 || defenseParameter < 1)
            throw new IllegalArgumentException("Параметр Защита должен быть в диапазоне от 1 до 30");
        if (maxHealth > MAX_ALLOWED_HEALTH || maxHealth < 1)
            throw new IllegalArgumentException("Изначальное Здоровье существа должно быть в диапазоне от 1 до " +
                    MAX_ALLOWED_HEALTH);
        if (minDamageParameter >= maxDamageParameter || minDamageParameter < 1 || maxDamageParameter > MAX_ALLOWED_DAMAGE_PARAMETER)
            throw new IllegalArgumentException("Параметр Урон должен быть в диапазоне от 1 до " + MAX_ALLOWED_DAMAGE_PARAMETER);

        this.attackParameter = attackParameter;
        this.defenseParameter = defenseParameter;
        this.maxHealth = maxHealth;
        this.minDamageParameter = minDamageParameter;
        this.maxDamageParameter = maxDamageParameter;

        this.currentHealth = maxHealth;
    }

    /*
     * Одно Существо может ударить другое по такому алгоритму:
     *  - Рассчитываем Модификатор атаки. Он равен разности Атаки атакующего и Защиты защищающегося плюс 1
     *  - Успех определяется броском N кубиков с цифрами от 1 до 6, где N - это Модификатор атаки. Всегда бросается хотя бы один кубик.
     *       - Удар считается успешным, если хотя бы на одном из кубиков выпадает 5 или 6
     *  - Если удар успешен, то берется произвольное значение из параметра Урон атакующего и вычитается из Здоровья защищающегося.
     */
    private int getAttackModifier(Creature targetCreature) {
        return this.attackParameter - targetCreature.defenseParameter + 1;
    }

    private int getRandomDiceNumber() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    private int getRandomDamageParameter() {
        Random random = new Random();
        return random.nextInt(maxDamageParameter - minDamageParameter + 1) + minDamageParameter;
    }

    private boolean isAttackSuccess(int numberOfDiceRoll) {
        for (int i = 0; i < numberOfDiceRoll; i++) {
            int diceNumber = getRandomDiceNumber();
            System.out.println("Бросок кубика " + (i + 1) + " = " + diceNumber);
            if (diceNumber >= MIN_NUMBER_FOR_SUCCESS)
                return true;
        }
        return false;
    }

    public void attack(Creature targetCreature) {
        int attackModifier = getAttackModifier(targetCreature);
        System.out.println("Модификатор атаки = " + attackModifier);

        int numberOfDiceRoll = attackModifier > 0 ? attackModifier : 1;
        System.out.println("Количество бросков кубика = " + numberOfDiceRoll);

        if (isAttackSuccess(numberOfDiceRoll)) {
            System.out.println("Атака успешна!");
            int damage = getRandomDamageParameter();
            System.out.println("Нанесенный урон = " + damage);
            targetCreature.currentHealth -= damage;
//            targetCreature.currentHealth -= getRandomDamageParameter();
            if (targetCreature.currentHealth <= 0){
                targetCreature.isDead = true;
                targetCreature.currentHealth = 0;
            }


        } else
            System.out.println("Атака неуспешная!");

    }

    @Override
    public String toString() {
        return "{" +
                "attackParameter = " + attackParameter +
                ", defenseParameter = " + defenseParameter +
                ", maxHealth = " + maxHealth +
                ", currentHealth = " + currentHealth +
                ", DamageParameter = " + minDamageParameter + " - " + maxDamageParameter +
                ", isDead = " + isDead +
                '}';
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        if (currentHealth > maxHealth)
            throw new IllegalArgumentException("Устанавливаемое здоровье существа не может быть больше максимального");
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
