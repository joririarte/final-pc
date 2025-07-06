package searchEngine;
import java.util.*;
import java.util.concurrent.*;

import utils.data.DataLoader;
import utils.ia.Optimizer;
import utils.ia.OptimizerFactory;
import utils.ia.OptimizerFactory.ModoOptimizacion;

public class SearchEngine {
    private List<String> data;
    private ForkJoinPool pool;
    private Optimizer optimizer;

    public SearchEngine(ModoOptimizacion m) {
        data = DataLoader.loadDummyData(); //un dataset de entre 1000 y 50000 registros
        pool = new ForkJoinPool();
        try{
            optimizer = OptimizerFactory.createOptimizer(m);
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