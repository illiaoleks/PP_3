package menu;

import arena.Arena;
import battle.Battle;
import battle.OneOnOneBattle;
import battle.TeamBattle;
import droids.*;
import utils.BattleLogger;
import utils.BattleReplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameMenu {
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера
            switch(choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    removeDroid();
                    break;
                case 4:
                    startOneOnOneBattle();
                    break;
                case 5:
                    startTeamBattle();
                    break;
                case 6:
                    BattleLogger.clearLog();
                    System.out.println("Лог бою очищено.");
                    break;
                case 7:
                    BattleReplayer.replay();
                    break;
                case 8:
                    System.out.println("Вихід з програми.");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        } while(choice != 8);
    }
    private void showMenu(){
        System.out.println("\n=== МЕНЮ ГРИ ===");
        System.out.println("1. Створити дроїда");
        System.out.println("2. Показати список дроїдів");
        System.out.println("3. Видалити дроїда");
        System.out.println("4. Бій 1 на 1");
        System.out.println("5. Командний бій");
        System.out.println("6. Видалити поточний бій з файлу");
        System.out.println("7. Відтворити бій з файлу");
        System.out.println("8. Вийти з програми");
        System.out.print("Виберіть опцію: ");
    }

    private void createDroid() {
        int choice;
        do {
            System.out.println("=== СТВОРЕННЯ ДРОЇДА ===");
            System.out.println("1. Створити дроїда вручну");
            System.out.println("2. Створити випадкового дроїда");
            System.out.println("3. Створити кілька випадкових дроїдів");
            System.out.println("4. Повернутися до головного меню");
            choice = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера
            switch (choice) {
                case 1:
                    createManualDroid();
                    break;
                case 2:
                    droids.add(createRandomDroid());
                    break;
                case 3:
                    createMultipleRandomDroids();
                    break;
                case 4:
                    System.out.println("Повернення до головного меню.");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        } while (choice != 4);
    }

    private void createManualDroid() {
        System.out.println("=== ВИБЕРІТЬ ТИП ДРОЇДА ===");
        System.out.println("1. Бойовий дроїд");
        System.out.println("2. Дроїд-цілитель");
        System.out.println("3. Снайперський дроїд");
        System.out.println("4. Танковий дроїд");
        int type = scanner.nextInt();
        scanner.nextLine(); // Очищення буфера
        System.out.println("Введіть ім'я дроїда:");
        String name = scanner.nextLine();

        Droid droid = null;
        switch (type) {
            case 1:
                droid = new BattleDroid(name);
                break;
            case 2:
                droid = new HealerDroid(name);
                break;
            case 3:
                droid = new SniperDroid(name);
                break;
            case 4:
                droid = new TankDroid(name);
                break;
            default:
                System.out.println("Невірний тип дроїда.");
                return;
        }
        droids.add(droid);
        System.out.println("Дроїд створено: " + droid);
    }

    private Droid createRandomDroid() {
        Random rand = new Random();
        int type = rand.nextInt(4) + 1; // Випадкове число від 1 до 4

        String[] names = {"Ілля", "Азов", "X", "Вова", "Порох", "Янукович", "Міг", "Маг", "Муг", "Араб"};
        String name = names[rand.nextInt(names.length)];

        Droid droid = null;
        switch (type) {
            case 1:
                droid = new BattleDroid(name);
                break;
            case 2:
                droid = new HealerDroid(name);
                break;
            case 3:
                droid = new SniperDroid(name);
                break;
            case 4:
                droid = new TankDroid(name);
                break;
        }

        System.out.println("Дроїд створений: " + droid);
        return droid;
    }

    private void createMultipleRandomDroids() {
        System.out.println("Введіть кількість випадкових дроїдів, яких потрібно створити:");
        int count = scanner.nextInt();
        scanner.nextLine(); // Очищення буфера

        if (count <= 0) {
            System.out.println("Кількість дроїдів повинна бути більше нуля.");
            return;
        }

        for (int i = 0; i < count; i++) {
            Droid droid = createRandomDroid();
            droids.add(droid);
        }
        System.out.println("Створено " + count + " випадкових дроїдів.");
    }

    private void removeDroid() {
        if (droids.isEmpty()) {
            System.out.println("Список дроїдів порожній.");
            return;
        }

        System.out.println("Виберіть дроїда для видалення:");
        showDroids();
        int index = scanner.nextInt() - 1;

        if (index < 0 || index >= droids.size()) {
            System.out.println("Невірний вибір.");
            return;
        }

        System.out.println("Дроїд " + droids.get(index).getName() + " видалений.");
        droids.remove(index);
    }

    private void showDroids() {
        if(droids.isEmpty()) {
            System.out.println("Список дроїдів порожній.");
            return;
        }

        System.out.println("=== СПИСОК ДРОЇДІВ ===");
        for (int i = 0; i < droids.size(); i++) {
            String status = droids.get(i).isAlive() ? "" : " (ВБИТИЙ)";
            System.out.println((i + 1) + ". " + droids.get(i) + status);
        }
    }
    private void startOneOnOneBattle() {
        if(droids.size() < 2) {
            System.out.println("Дроїдів недостатньо для бою.");
            return;
        }

        List<Droid> availableDroids = getAvailableDroids();
        if (availableDroids.size() < 2) {
            System.out.println("Недостатньо живих дроїдів для бою.");
            return;
        }

        System.out.println("Виберіть дроїдів для бою:");
        showDroids();
        System.out.println("Виберіть першого дроїда:");
        int index1 = scanner.nextInt() - 1;
        System.out.println("Виберіть другого дроїда:");
        int index2 = scanner.nextInt() - 1;

        if(index1 == index2) {
            System.out.println("Дроїди не можуть бути однаковими.");
            return;
        }

        Droid droid1 = droids.get(index1);
        Droid droid2 = droids.get(index2);

        if (!droid1.isAlive() || !droid2.isAlive()) {
            System.out.println("Вибраний дроїд вбитий і не може брати участь у бою.");
            return;
        }

        Arena arena = chooseArena();

        BattleLogger.clearLog();

        OneOnOneBattle battle = new OneOnOneBattle(droids.get(index1), droids.get(index2), arena);
        battle.start();
    }

    private void startTeamBattle() {
        if(droids.size() < 4) {
            System.out.println("Дроїдів недостатньо для командного бою.");
            return;
        }

        List<Droid> availableDroids = getAvailableDroids();
        if (availableDroids.size() < 4) {
            System.out.println("Недостатньо живих дроїдів для формування команд.");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println("Формування команд...");
        for(int i = 0; i < droids.size(); i++) {
            if(i % 2 == 0) {
                team1.add(droids.get(i));
            } else {
                team2.add(droids.get(i));
            }
        }
        if (team1.isEmpty() || team2.isEmpty()) {
            System.out.println("Недостатньо живих дроїдів для формування команд.");
            return;
        }

        Arena arena = chooseArena();

        BattleLogger.clearLog();

        TeamBattle battle = new TeamBattle(team1, team2, arena);
        battle.start();
    }

    private List<Droid> getAvailableDroids() {
        List<Droid> availableDroids = new ArrayList<>();
        for (Droid droid : droids) {
            if (droid.isAlive()) {
                availableDroids.add(droid);
            }
        }
        return availableDroids;
    }

    private Arena chooseArena() {
        System.out.println("Виберіть арену:");
        System.out.println("1. Пустеля (зменшує точність на 20%, енергія -10)");
        System.out.println("2. Ліс (збільшує точність на 10%, енергія +5)");
        System.out.println("3. Міські руїни (без змін)");
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                return new Arena("Пустеля", 0.8, -10);
            case 2:
                return new Arena("Ліс", 1.1, 5);
            case 3:
                return new Arena("Міські руїни", 1.0, 0);
            default:
                System.out.println("Невірний вибір, обрано Міські руїни за замовчуванням.");
                return new Arena("Міські руїни", 1.0, 0);
        }
    }

}
