package utils.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

import searchEngine.SearchTask;

public class DataSetGenerator {

    public static void main(String[] args) {
        int[] dataSizes = {1000, 5000, 10000, 20000, 50000};
        int[] blockSizesToTest = {100, 200, 500, 1000, 2000, 3000};

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dataset.csv"))) {
            writer.write("dataSize,blockSize\n"); // encabezado CSV

            for (int dataSize : dataSizes) {
                List<String> data = DataLoader.loadDummyData(dataSize);
                long bestTime = Long.MAX_VALUE;
                int bestBlockSize = -1;

                for (int blockSize : blockSizesToTest) {
                    long start = System.nanoTime();
                    simulateSearch(data, "registro", blockSize);
                    long duration = System.nanoTime() - start;

                    if (duration < bestTime) {
                        bestTime = duration;
                        bestBlockSize = blockSize;
                    }
                }

                System.out.println("dataSize=" + dataSize + " => blockSize óptimo: " + bestBlockSize);
                writer.write(dataSize + "," + bestBlockSize + "\n");
            }

            System.out.println("CSV guardado como dataset.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void simulateSearch(List<String> data, String query, int blockSize) {
        ForkJoinPool pool = new ForkJoinPool();
        SearchTask task = new SearchTask(data, 0, data.size(), query, blockSize);
        pool.invoke(task);
        pool.close();
    }
}
