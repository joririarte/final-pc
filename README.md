# Proyecto Final PC

Este es un proyecto Java basado en Maven llamado "finalpc". Está diseñado para demostrar el uso de Java 21.0.7 e incluye la biblioteca DJL (Deep Java Library) para capacidades de aprendizaje automático (machine learning).

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