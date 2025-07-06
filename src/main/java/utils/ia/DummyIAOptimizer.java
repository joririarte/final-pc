package utils.ia;
// IAOptimizer.java
//esto es lo que deberia cambiar como algoritmo de ia real
public class DummyIAOptimizer implements Optimizer {

    @Override
    public int getOptimalBlockSize(int dataSize) throws Exception {
        // Heurística simple para elegir el tamaño del bloque basado en el tamaño del dataset
        if (dataSize < 10000) return 500;
        else if (dataSize < 50000) return 1000;
        else return 2000; // podría usar tiempo de ejecución anterior para ajustar
    }
}
