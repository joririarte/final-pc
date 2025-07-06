package utils.ia;

import ai.djl.inference.Predictor;
import ai.djl.ndarray.*;
import ai.djl.translate.*;
import ai.djl.repository.zoo.*;

import java.nio.file.Paths;

public class IAOptimizer implements Optimizer {

    private Predictor<NDList, NDList> predictor;

    public IAOptimizer() throws Exception {
        Criteria<NDList, NDList> criteria = Criteria.builder()
                .setTypes(NDList.class, NDList.class)
                .optModelPath(Paths.get("models/blocksize-model")) // Ruta a tu modelo
                .optEngine("PyTorch") // o "TensorFlow"
                .optTranslator(new NoopTranslator())
                .build();

        ZooModel<NDList, NDList> model = criteria.loadModel();
        this.predictor = model.newPredictor();
    }

    @Override
    public int getOptimalBlockSize(int dataSize) throws Exception {
        try (NDManager manager = NDManager.newBaseManager()) {
            NDArray inputArray = manager.create(new float[]{dataSize});
            NDList inputList = new NDList(inputArray);

            NDList outputList = predictor.predict(inputList);
            float result = outputList.get(0).getFloat();

            return Math.max(100, (int) result); // aseguramos tamaño mínimo
        }
    }

    public static class NoopTranslator implements Translator<NDList, NDList> {

        @Override
        public NDList processInput(TranslatorContext ctx, NDList input) {
            return input;
        }

        @Override
        public NDList processOutput(TranslatorContext ctx, NDList output) {
            return output;
        }

        @Override
        public Batchifier getBatchifier() {
            return null;
        }
    }
}
