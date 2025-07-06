package utils.data;
import java.util.*;

public class DataLoader {
    public static List<String> loadDummyData(int size) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < size ; i++) {
            data.add("Registro numero " + i);
        }
        return data;
    }
}