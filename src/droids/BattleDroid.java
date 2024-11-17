package droids;

import utils.AnsiColors;

public class BattleDroid extends Droid {

    public BattleDroid(String name) {
        super(name, 120, 30, 5, 60);
    }

    @Override
    public void attack(Droid opponent) {
        if (energy >= 10) {
            printWithColor(AnsiColors.RED,name + " атакує " + opponent.getName() + " на " + damage + " урону.");
            opponent.takeDamage(damage);
            energy -= 10;
        } else {
            printWithColor(AnsiColors.BLUE,name + " недостатньо енергії для атаки.");
            restoreEnergy(5);
        }
    }
}