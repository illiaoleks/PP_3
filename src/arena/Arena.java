package arena;

import droids.Droid;


public class Arena {
    private String name;
    private double accuracyModifier;
    private int energyModifier;

    public Arena(String name, double accuracyModifier, int energyModifier) {
        this.name = name;
        this.accuracyModifier = accuracyModifier;
        this.energyModifier = energyModifier;
    }

    public void applyEffects(Droid droid) {
        // Змінюємо точність снайперів
        if(droid instanceof droids.SniperDroid) {
            ((droids.SniperDroid) droid).setAccuracyModifier(accuracyModifier);
        }
        // Змінюємо максимальну енергію дроїдів
        droid.setMaxEnergy(droid.getMaxEnergy() + energyModifier);
    }

    public String getName() {
        return name;
    }
}
