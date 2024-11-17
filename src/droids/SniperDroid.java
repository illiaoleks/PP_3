package droids;

import utils.AnsiColors;

import java.util.Random;

public class SniperDroid extends Droid{
    private double accuracy;
    private double accuracyModifier = 1.0;

    public SniperDroid(String name) {
        super(name, 70, 50, 2, 40);
        this.accuracy = 0.85;
    }

    @Override
    public void attack(Droid opponent) {
        if (energy >= 20) {
            Random rand = new Random();
            if (rand.nextDouble() < accuracy * accuracyModifier) {
                printWithColor(AnsiColors.RED,name + " атакує " + opponent.getName() + " на " + damage + " урону.");
                opponent.takeDamage(damage);
            } else {
                printWithColor(AnsiColors.YELLOW,name + " промахнувся.");
            }
            energy -= 20;
        } else {
            System.out.println(name + " недостатньо енергії для атаки.");
            restoreEnergy(10);
        }
    }

    public void setAccuracyModifier(double modifier) {
        this.accuracyModifier = modifier;
    }
}
