package battle;

import arena.Arena;
import droids.Droid;
import droids.HealerDroid;
import utils.AnsiColors;
import utils.BattleLogger;

import java.util.List;
import java.util.Random;

public class TeamBattle implements Battle {
    private List<Droid> team1;
    private List<Droid> team2;
    private Arena arena;
    private Random random;

    public TeamBattle(List<Droid> team1, List<Droid> team2, Arena arena) {
        this.team1 = team1;
        this.team2 = team2;
        this.arena = arena;
        this.random = new Random();
    }

    @Override
    public void start() {
        BattleLogger.clearLog();
        applyArenaEffects();

        String battleStartMessage = "=== КОМАНДНИЙ БІЙ НА АРЕНІ: " + arena.getName() + " ===";
        BattleLogger.log(battleStartMessage);

        while (isTeamAlive(team1) && isTeamAlive(team2)) {
            System.out.println("\nХід команди 1:");
            performTeamAction(team1, team2);
            logTeamsStatus();

            if (!isTeamAlive(team2)) {
                announceTeamLoss("Команда 2");
                break;
            }

            System.out.println("\nХід команди 2:");
            performTeamAction(team2, team1);
            logTeamsStatus();

            if (!isTeamAlive(team1)) {
                announceTeamLoss("Команда 1");
                break;
            }
        }

        String resultMessage = isTeamAlive(team1) ? "Команда 1 перемогла!" : "Команда 2 перемогла!";
        System.out.println("\n" + AnsiColors.YELLOW + resultMessage + AnsiColors.RESET);
        BattleLogger.log(resultMessage);
    }

    private void applyArenaEffects() {
        for (Droid droid : team1) {
            arena.applyEffects(droid);
        }
        for (Droid droid : team2) {
            arena.applyEffects(droid);
        }
    }

    private void performTeamAction(List<Droid> attackingTeam, List<Droid> defendingTeam) {
        Droid attacker = chooseRandomAliveDroid(attackingTeam);
        Droid target = chooseRandomAliveDroid(defendingTeam);

        if (attacker instanceof HealerDroid && attacker.getEnergy() >= 15) {
            HealerDroid healer = (HealerDroid) attacker;
            if (random.nextBoolean()) {
                healer.attack(target);
            } else {
                Droid ally = chooseRandomAliveDroid(attackingTeam);
                healer.healSelfOrAlly(ally);
            }
        } else {
            attacker.attack(target);
        }

        String actionMessage = attacker.getName() + " атакує " + target.getName() + "!";
        BattleLogger.log(actionMessage);
        if (!target.isAlive()) {
            String deathMessage = target.getName() + " загинув!";
            System.out.println("\n" + AnsiColors.RED + deathMessage + AnsiColors.RESET);
            BattleLogger.log(deathMessage);
        }
    }

    private Droid chooseRandomAliveDroid(List<Droid> team) {
        Droid droid;
        do {
            droid = team.get(random.nextInt(team.size()));
        } while (!droid.isAlive());
        return droid;
    }

    private boolean isTeamAlive(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private void logTeamsStatus() {
        BattleLogger.log("=== СТАН КОМАНД ===");
        BattleLogger.log("Команда 1:");
        logTeamStatus(team1);
        BattleLogger.log("Команда 2:");
        logTeamStatus(team2);
    }

    private void logTeamStatus(List<Droid> team) {
        for (Droid droid : team) {
            BattleLogger.log(droid.toString());
        }
    }
    private void announceTeamLoss(String teamName) {
        String message = teamName + " повністю знищена!";
        System.out.println("\n" + AnsiColors.RED + message + AnsiColors.RESET);
        BattleLogger.log(message);
    }
}
