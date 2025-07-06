package utils.ia;

public class OptimizerFactory {
    public enum ModoOptimizacion {
        DUMMY_OPTIMIZER(0, "Dummy Optimizer"),
        IA_OPTIMIZER(1, "IA Optimizer");

        private final int id;
        private final String descripcion;

        ModoOptimizacion(int id, String descripcion) {
            this.id = id;
            this.descripcion = descripcion;
        }

        public int getId() {
            return id;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public static ModoOptimizacion getModoOptimizacion(int id){
            if(id == ModoOptimizacion.DUMMY_OPTIMIZER.id){
                return ModoOptimizacion.DUMMY_OPTIMIZER;
            }
            else if(id == ModoOptimizacion.IA_OPTIMIZER.id){
                return ModoOptimizacion.IA_OPTIMIZER;
            }
            return null;
        }
    }

    public static Optimizer createOptimizer(int type) throws Exception{
        if(type == ModoOptimizacion.DUMMY_OPTIMIZER.id){
            return new DummyIAOptimizer();
        }
        else if(type == ModoOptimizacion.IA_OPTIMIZER.id){
            return new IAOptimizer();
        }
        return null;
    }

    public static Optimizer createOptimizer(ModoOptimizacion m) throws Exception{
        if(m.equals(ModoOptimizacion.DUMMY_OPTIMIZER)){
            return new DummyIAOptimizer();
        }
        else if(m.equals(ModoOptimizacion.IA_OPTIMIZER)){
            return new IAOptimizer();
        }
        return null;
    }
}


