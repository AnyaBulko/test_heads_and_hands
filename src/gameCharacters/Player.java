package gameCharacters;

public class Player extends Creature {

    public Player(int attackParameter, int defenseParameter, int maxHealth, int minDamageParameter, int maxDamageParameter) {
        super(attackParameter, defenseParameter, maxHealth, minDamageParameter, maxDamageParameter);
        currentNumberOfHealing = 4;
    }

    public final static int NUMBER_OF_HEALING = 4;
    private int currentNumberOfHealing;

    public boolean heal() {
        if (currentNumberOfHealing == 0) {
            setNewLog("Исцеление невозможно. Количество исцелений = 0\n");
            return false;
        } else {
            currentNumberOfHealing--;
            int calculatedHealth = (int) (getCurrentHealth() + 0.3 * getMaxHealth());
            int newHealth = Math.min(calculatedHealth, getMaxHealth());
            setNewLog("Игрок исцелен на " + (newHealth - getCurrentHealth()) + "\n");
            setCurrentHealth(newHealth);
            return true;
        }
    }

    @Override
    public String toString() {
        return "gameCharacters.Player" + super.toString();
    }

    public int getCurrentNumberOfHealing() {
        return currentNumberOfHealing;
    }
}
