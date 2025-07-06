package searchEngine;
import java.util.*;
import java.util.concurrent.*;

public class SearchTask extends RecursiveTask<ConcurrentHashMap<Integer, String>> {
    private List<String> data;
    private int start, end;
    private String query;
    private int blockSize;

    public SearchTask(List<String> data, int start, int end, String query, int blockSize) {
        this.data = data;
        this.start = start;
        this.end = end;
        this.query = query;
        this.blockSize = blockSize;
    }

    @Override
    protected ConcurrentHashMap<Integer, String> compute() {
        if (end - start <= blockSize) {
            ConcurrentHashMap<Integer, String> result = new ConcurrentHashMap<>();
            for (int i = start; i < end; i++) {
                if (data.get(i).toLowerCase().contains(query.toLowerCase())) {
                    result.put(i, data.get(i));
                }
            }
            return result;
        } else {
            int mid = (start + end) / 2;
            SearchTask left = new SearchTask(data, start, mid, query, blockSize);
            SearchTask right = new SearchTask(data, mid, end, query, blockSize);
            left.fork();
            ConcurrentHashMap<Integer, String> rightResult = right.compute();
            ConcurrentHashMap<Integer, String> leftResult = left.join();
            leftResult.putAll(rightResult);
            return leftResult;
        }
    }
}