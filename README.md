# Invernadero con hilos
## Qué hace
Se encarga de disimular lo que es un invernadero y esto lo hace con 4 hilos que funcionan como sensor de luz, sensor de humedad, sensor de temperatura y un programador de riego.
## Cómo ejecutarlo
1. Abrir el proyecto en IntelliJ con JDK 21.
2. Ejecutar Fase1_Secuencial, Fase2_Concurrente, Fase3_Experimentos y Fase4_Vigilante.
## Captura de la Fase 2

<img width="1546" height="798" alt="image" src="https://github.com/user-attachments/assets/f63f4e6d-f2d5-4ef0-9e67-832cddeda401" />

Explica por qué el tiempo concurrente es menor (y por qué no es exactamente
el del hilo más lento).
## Experimentos
- A: qué salió antes y después de join(), y por qué.
- B: nombre del hilo y estado final con run().
- C: excepción obtenida y su significado.
## Estados observados
(Pega las líneas del vigilante y explica qué línea de código provoca cada estado.)
## Thread frente a Runnable
| Aspecto | Thread | Runnable |
|---------------------------|-----------------------|--------------------------|
| Qué representa el objeto | | |
| Cómo se arranca | | |
| Cómo se obtiene el nombre | | |
| Herencia | | |
| Se puede reutilizar | | |
Explica por qué los sensores son Runnable y qué forma elegirías para el riego.
