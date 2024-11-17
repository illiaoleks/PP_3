package droids;

import utils.AnsiColors;

public class HealerDroid extends Droid {
    private int healAmount;

    public HealerDroid(String name) {
        super(name, 90, 15, 3, 80);
        this.healAmount = 25;
    }


    @Override
    public void attack(Droid opponent) {
        if (energy >= 15) {
            printWithColor(AnsiColors.RED, name + " атакує " + opponent.getName() + " на " + damage + " урону.");
            opponent.takeDamage(damage);
            energy -= 15;
        } else {
            printWithColor(AnsiColors.BLUE, name + " не має достатньо енергії для атаки! Відновлюємо енергію.");
            restoreEnergy(10);
        }
    }

    public void healSelfOrAlly(Droid ally) {
        if (energy >= 15) {
            heal(healAmount);
            energy -= 15;
        } else {
            printWithColor(AnsiColors.BLUE, name + " не має достатньо енергії для лікування! Відновлюємо енергію.");
            restoreEnergy(10);
        }
    }

}