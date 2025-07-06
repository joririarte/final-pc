package utils.data;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DataLoader {
    public static List<String> loadDummyData() {
        int size = ThreadLocalRandom.current().nextInt(1000,50001);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < size ; i++) {
            data.add("Registro numero " + i);
        }
        return data;
    }

    public static List<String> loadDummyData(int size) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < size ; i++) {
            data.add("Registro numero " + i);
        }
        return data;
    }
}