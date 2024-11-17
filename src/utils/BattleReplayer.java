package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BattleReplayer {
    private static final String FILE_NAME = "battle_log.txt";

    public static void replay() {
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
                Thread.sleep(500); // Пауза для ефекту
            }
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
