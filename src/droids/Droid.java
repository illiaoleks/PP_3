package droids;

import utils.AnsiColors;



public abstract class Droid {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int damage;
    protected int armor;
    protected int energy;
    protected int maxEnergy;


    public Droid(String name, int health, int damage, int armor, int energy) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.armor = armor;
        this.energy = energy;
        this.maxEnergy = energy;
    }

    public abstract void attack(Droid opponent);

    public Boolean isAlive(){
        return health > 0;
    }

    public void takeDamage(int damage){
       int actualDamage = damage - armor;
         if (actualDamage < 0) actualDamage = 0;
         health -= actualDamage;
         if (health < 0) health = 0;
        printWithColor(AnsiColors.RED, name + " отримав " + actualDamage + " урону після врахування броні. Здоровя лишилось: " + health + "\n");
    }

    public void heal(int amount){
        health += amount;
        if (health > maxHealth) health = maxHealth;
        printWithColor(AnsiColors.GREEN,name + " відновив " + amount + " здоров'я. Здоровя: " + health + " здоров'я\n");

    }

    public void restoreEnergy(int amount){
        energy +=amount;
        if(energy > maxEnergy) energy = maxEnergy;
        printWithColor(AnsiColors.BLUE,"Відновлено " + amount + " енергії");
    }

    protected void printWithColor(String color, String message) {
        System.out.println(color + message + AnsiColors.RESET);
    }
    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public String getName(){ return name; }
    public int getHealth(){ return health; }
    public int getDamage(){ return damage; }
    public int getArmor(){ return armor; }
    public int getEnergy(){ return energy; }
    public int getMaxHealth(){ return maxHealth; }
    public int getMaxEnergy(){ return maxEnergy; }


    @Override
    public String toString() {
        return name + " [Здоров'я: " + health + "/" + maxHealth + ", Урон: " + damage + ", Броня: " + armor + ", Енергія: " + energy + "/" + maxEnergy + ", Клас: " + this.getClass().getSimpleName() + "]";
    }

}
