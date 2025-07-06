package searchEngine;
import java.util.*;
import java.util.concurrent.*;

import utils.data.DataLoader;
import utils.ia.DummyIAOptimizer;
import utils.ia.IAOptimizer;
import utils.ia.Optimizer;

public class SearchEngine {
    private List<String> data;
    private ForkJoinPool pool;
    private Optimizer optimizer;

    public SearchEngine() {
        data = DataLoader.loadDummyData(ThreadLocalRandom.current().nextInt(1000,50001)); //un dataset de entre 1000 y 50000 registros
        pool = new ForkJoinPool();
        try{
            optimizer = new IAOptimizer();
        }
        catch (Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
    }

    public ConcurrentHashMap<Integer, String> search(String query) {
        try{
            int blockSize = optimizer.getOptimalBlockSize(data.size()); // acá deberia meter el algoritmo de ia
            SearchTask task = new SearchTask(data, 0, data.size(), query, blockSize);
            return pool.invoke(task);
        }
        catch (Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
        return null;
    }

    public List<String> getData(){
        return this.data;
    }
}