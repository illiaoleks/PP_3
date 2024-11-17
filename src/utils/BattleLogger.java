package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BattleLogger {
    private static final String FILE_NAME = "battle_log.txt";

    // Метод для логування повідомлення у файл
    public static void log(String message) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для очищення лог-файлу перед новим боєм
    public static void clearLog() {
        try (PrintWriter pw = new PrintWriter(FILE_NAME)) {
            pw.print(""); // Очищення файлу
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
