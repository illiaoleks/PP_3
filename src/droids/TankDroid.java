package droids;

import utils.AnsiColors;

public class TankDroid extends Droid {
    public TankDroid(String name) {
        super(name, 180, 30, 15, 50);
    }

    @Override
    public void attack(Droid opponent) {
        if (energy >= 15){
            printWithColor(AnsiColors.RED,name + " виконує потужну атаку на " + opponent.getName() + " на " + damage + " урону.");
            opponent.takeDamage(damage);
            energy -= 15;
        } else {
            printWithColor(AnsiColors.BLUE,name + " недостатньо енергії для атаки.");
            restoreEnergy(5);
        }
    }

    @Override
    public void takeDamage(int damage){
        int actualDamage = damage - armor;
        if (actualDamage < 0) actualDamage = 0;
        if(Math.random() < 0.3) { // 30% шанс
            actualDamage /= 2;
            printWithColor(AnsiColors.CYAN,name + " зменшив урон вдвічі!");
        }
        health -= actualDamage;
        if (health < 0) health = 0;
        printWithColor(AnsiColors.RED,name + " отримав " + actualDamage + " урону після врахування броні.");
    }
}
