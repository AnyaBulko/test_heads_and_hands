package gameCharacters;

public class Monster extends Creature {
    public Monster(int attackParameter, int defenseParameter, int maxHealth, int minDamageParameter, int maxDamageParameter) {
        super(attackParameter, defenseParameter, maxHealth, minDamageParameter, maxDamageParameter);
    }

    @Override
    public String toString() {
        return "gameCharacters.Monster" + super.toString();
    }
}
