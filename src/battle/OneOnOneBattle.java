package battle;

import utils.AnsiColors;
import arena.Arena;
import droids.Droid;
import droids.HealerDroid;
import utils.BattleLogger;

public class OneOnOneBattle implements Battle {
    private Droid droid1;
    private Droid droid2;
    private Arena arena;

    public OneOnOneBattle(Droid droid1, Droid droid2, Arena arena) {
        this.droid1 = droid1;
        this.droid2 = droid2;
        this.arena = arena;
    }

    @Override
    public void start() {
        BattleLogger.clearLog();
        arena.applyEffects(droid1);
        arena.applyEffects(droid2);

        String battleStartMessage = "=== БІЙ НА АРЕНІ: " + arena.getName() + " ===";
        System.out.println("\n" + AnsiColors.YELLOW + battleStartMessage + AnsiColors.RESET);
        BattleLogger.log(battleStartMessage);

        while (droid1.isAlive() && droid2.isAlive()) {
            System.out.println("\n" + AnsiColors.BLUE + "-----------------------------------" + AnsiColors.RESET);
            performAction(droid1, droid2);
            logDroidStatus(droid1, droid2);

            if (!droid2.isAlive()) {
                announceDroidDeath(droid2);
                break;
            }

            performAction(droid2, droid1);
            logDroidStatus(droid2, droid1);

            if (!droid1.isAlive()) {
                announceDroidDeath(droid1);
                break;
            }
        }

        String resultMessage = droid1.isAlive() ? droid1.getName() + " переміг!" : droid2.getName() + " переміг!";
        System.out.println("\n" + AnsiColors.YELLOW + resultMessage + AnsiColors.RESET);
        BattleLogger.log(resultMessage);
    }
    private void announceDroidDeath(Droid droid) {
        String deathMessage = droid.getName() + " загинув!";
        System.out.println("\n" + AnsiColors.RED + deathMessage + AnsiColors.RESET);
        BattleLogger.log(deathMessage);
    }

    private void performAction(Droid attacker, Droid target) {
        if (attacker instanceof HealerDroid && attacker.getEnergy() >= 15) {
            HealerDroid healer = (HealerDroid) attacker;
            if (Math.random() > 0.5) {
                healer.attack(target);
            } else {
                healer.healSelfOrAlly(attacker);
            }
        } else {
            attacker.attack(target);
        }

        String actionMessage = attacker.getName() + " атакує " + target.getName() + "!";
        BattleLogger.log(actionMessage);
    }

    private void logDroidStatus(Droid droid1, Droid droid2) {
        BattleLogger.log(droid1.toString());
        BattleLogger.log(droid2.toString());
    }
}