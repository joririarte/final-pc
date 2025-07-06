# Proyecto Final PC

Este es un proyecto Java basado en Maven llamado "finalpc". Está diseñado para demostrar el uso de Java 21.0.7 e incluye la biblioteca DJL (Deep Java Library) para capacidades de aprendizaje automático (machine learning).

## Requerimiento
### Optimización de Búsqueda Concurrente en un Gran Conjunto de Datos
#### Descripción
Desarrolla un motor de búsqueda concurrente que procese consultas sobre un conjunto de datos muy grande, distribuyendo las tareas de búsqueda entre varios hilos.
#### Requerimientos
1.	Carga el conjunto de datos en bloques utilizando ForkJoinPool para dividir la búsqueda.
2.	Implementa un RecursiveTask para realizar la búsqueda en paralelo.
3.	Usa ConcurrentHashMap para almacenar y combinar los resultados de manera eficiente.
4.	(Aplicación de IA): Optimiza el rendimiento mediante IA, ajustando dinámicamente:
    *	El tamaño de los bloques de búsqueda.
    * La distribución de carga entre hilos.
#### Pistas para aplicar IA
*  	Aplica Aprendizaje por Refuerzo para ajustar el tamaño de los bloques de búsqueda.
*	Utiliza Redes Neuronales Recurrentes (RNNs) para predecir patrones de búsqueda y optimizar la cache de resultados.


## Estructura del Proyecto

```
FINALPC
├── iaTrainer/                  # Scripts de entrenamiento del modelo IA (Python)
│   ├── trainer.py              # Script para entrenar el modelo
│   └── readme.md               # Documentación del entrenamiento
│
├── models/                     # Carpeta para guardar el modelo entrenado (model.pt)
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── gui/
│   │   │   │   └── SearchGUI.java
│   │   │   │
│   │   │   ├── searchEngine/
│   │   │   │   ├── SearchEngine.java
│   │   │   │   └── SearchTask.java
│   │   │   │
│   │   │   ├── utils/
│   │   │   │   ├── data/
│   │   │   │   │   ├── DataLoader.java
│   │   │   │   │   └── DataSetGenerator.java
│   │   │   │   │
│   │   │   │   └── ia/
│   │   │   │       ├── Optimizer.java               # Interfaz
│   │   │   │       ├── DummyIAOptimizer.java        # Implementación dummy
│   │   │   │       ├── IAOptimizer.java             # Implementación real (DJL)
│   │   │   │       └── OptimizerFactory.java        # Selector de implementación
│   │   │   │
│   │   │   └── App.java
│   │   │
│   │   └── resources/           # Archivos de recursos (por ahora vacío)
│   │
│   └── test/
│       ├── java/
│       │   └── AppTest.java     # Test unitarios
│       └── resources/           # Recursos para test (vacío por ahora)
│
│
├── .gitignore
├── dataset.csv                # Dataset de entrenamiento para IA
├── pom.xml                    # Configuración de Maven
└── README.md                  # Descripción del proyecto

```

## Requisitos

- Java 21.0.7
- Maven 3.9.10

## Compilar el Proyecto

To build the project, navigate to the root directory of the project and run the following command:

```
mvn clean install
```

## Ejecutar la Aplicación

Después de compilar el proyecto, puedes ejecutar la aplicación con el siguiente comando:

```
mvn exec:java -Dexec.mainClass="App"
```

## Ejecutar las Pruebas

Para ejecutar las pruebas, usa el siguiente comando:

```
mvn test
```

## Dependencias

Este proyecto incluye la biblioteca DJL para aprendizaje automático (machine learning). Las dependencias están gestionadas en el archivo pom.xml.