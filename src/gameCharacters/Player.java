package gameCharacters;

public class Player extends Creature {
    public Player(int attackParameter, int defenseParameter, int maxHealth, int minDamageParameter, int maxDamageParameter) {
        super(attackParameter, defenseParameter, maxHealth, minDamageParameter, maxDamageParameter);
    }

    private void heal() {
        int calculatedHealth = (int) (getCurrentHealth() + 0.3 * getMaxHealth());
        setCurrentHealth(Math.min(calculatedHealth, getMaxHealth()));
    }

    @Override
    public String toString() {
        return "gameCharacters.Player" + super.toString();
    }
}
