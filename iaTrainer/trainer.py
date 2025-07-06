import torch
import torch.nn as nn
import torch.optim as optim
import pandas as pd
import os
import sys

DATASET_PATH = "../dataset.csv"
MODEL_DIR = "../models/blocksize-model"  # Ruta destino del modelo para DJL
MODEL_PATH = os.path.join(MODEL_DIR, "model.pt")
PROPERTIES_PATH = os.path.join(MODEL_DIR, "model.properties")

# Verificar existencia del dataset
if not os.path.exists(DATASET_PATH):
    print(f"❌ Error: No se encontró el archivo '{DATASET_PATH}'.")
    sys.exit(1)

# Leer y procesar CSV
try:
    df = pd.read_csv(DATASET_PATH)
    df["dataSize"] = pd.to_numeric(df["dataSize"], errors="coerce")
    df["blockSize"] = pd.to_numeric(df["blockSize"], errors="coerce")
    df = df.dropna()
except Exception as e:
    print(f"❌ Error leyendo el dataset: {e}")
    sys.exit(1)

print(df.dtypes)

X = torch.tensor(df["dataSize"].values, dtype=torch.float32).reshape(-1, 1)
y = torch.tensor(df["blockSize"].values, dtype=torch.float32).reshape(-1, 1)

model = nn.Sequential(
    nn.Linear(1, 16),
    nn.ReLU(),
    nn.Linear(16, 1)
)

loss_fn = nn.MSELoss()
optimizer = optim.Adam(model.parameters(), lr=0.01)

for epoch in range(1000):
    optimizer.zero_grad()
    output = model(X)
    loss = loss_fn(output, y)
    loss.backward()
    optimizer.step()

# 👉 Guardar modelo como TorchScript (compatible con DJL)
example_input = torch.tensor([[1000.0]])
traced_model = torch.jit.trace(model, example_input)

# Crear carpeta si no existe
os.makedirs(MODEL_DIR, exist_ok=True)
traced_model.save(MODEL_PATH)

# Crear archivo model.properties
with open(PROPERTIES_PATH, "w") as f:
    f.write("engine=PyTorch\n")
    f.write("model=model.pt\n")

print(f"✅ Modelo guardado como TorchScript en: {MODEL_PATH}")
print(f"📝 model.properties generado en: {PROPERTIES_PATH}")
