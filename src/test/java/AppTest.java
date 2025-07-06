import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.concurrent.*;

import utils.data.DataLoader;
import utils.ia.Optimizer;
import utils.ia.OptimizerFactory;
import utils.ia.OptimizerFactory.ModoOptimizacion;
import searchEngine.SearchEngine;

public class AppTest {

    @Test
    void testDataLoader() {
        List<String> data = DataLoader.loadDummyData();
        assertNotNull(data);
        assertFalse(data.isEmpty());
        assertEquals("Registro numero 0", data.get(0));
    }

    @Test
    void testDummyIAOptimizerReturnsExpectedBlockSize() {
        try{
            Optimizer optimizer = OptimizerFactory.createOptimizer(ModoOptimizacion.DUMMY_OPTIMIZER);
            int blockSize = optimizer.getOptimalBlockSize(5000);

            assertTrue(blockSize > 0);
        }
        catch(Exception ex){
            System.out.println("error: " + ex.getMessage());
        }
    }

    @Test
    void testSearchEngineFindsExpectedResult() {
        SearchEngine engine = new SearchEngine(ModoOptimizacion.DUMMY_OPTIMIZER); // usa constructor con optimizador
        ConcurrentHashMap<Integer, String> result = engine.search("Registro numero 3");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.values().stream().anyMatch(s -> s.contains("3")));
    }
}
