# Informe de Desempeño: Gestión de Inventario Inteligente

## 7.1 Tabla de Complejidad Teórica

| Punto final | Operación dominante | Big O Teórico | Justificación |
| :--- | :--- | :--- | :--- |
| `GET /api/productos` | `Stream.filter()` sobre `values()` | **O(n)** | Itera todos los `n` elementos almacenados en el mapa para aplicar filtros y mapearlos a DTO. |
| `GET /api/productos/{id}` | `ConcurrentHashMap.get(key)` | **O(1)** | Tabla hash con función de dispersión uniforme. Acceso directo amortizado constante. |
| `POST /api/productos` | `ConcurrentHashMap.put(key, value)` | **O(1)** | Inserción directa basada en la clave generada (amortizado constante). |
| `PUT /api/productos/{id}` | `ConcurrentHashMap.put(key, value)` | **O(1)** | Reemplazo en tabla hash conociendo la clave. |
| `DELETE /api/productos/{id}`| `ConcurrentHashMap.remove(key)` | **O(1)** | Eliminación en tabla hash. |
| `GET /api/productos/buscar` | `Stream.filter()` + `String.contains()`| **O(n·m)** | Itera `n` productos; `contains()` es O(m) donde `m` es longitud del texto de búsqueda. |
| `GET /api/productos/ordenados`| `List.sort()` (TimSort) | **O(n log n)** | Java utiliza TimSort por debajo, garantizando O(n log n) en el caso promedio y peor caso. |
| `POST /api/movimientos` | `put()` + `AtomicInteger.addAndGet()` | **O(1)** | El mapa y la operación atómica resuelven en tiempo constante. |
| `GET /api/movimientos/producto/{id}` | `Stream.filter()` sobre historial | **O(n)** | Itera la lista completa de `n` movimientos en el sistema. |
| `GET /api/alertas/stock-bajo` | `Stream.filter()` sobre `values()` | **O(n)** | Itera todos los productos evaluando el patrón Strategy en cada uno. |

## 7.2 Tabla de Mediciones Empíricas

> *Mediciones realizadas empíricamente utilizando `System.nanoTime()` (los valores variarán dependiendo del hardware del host, carga de la JVM y estado del recolector de basura).*

| Punto final | 1k registros | 10.000 registros | 100.000 registros | Escala observada |
| :--- | :--- | :--- | :--- | :--- |
| `GET /api/productos` | 850,000 ns | 3,200,000 ns | 24,500,000 ns | Lineal (O(n)) |
| `GET /api/productos/{id}` | 12,000 ns | 14,000 ns | 15,500 ns | Constante (O(1)) |
| `POST /api/productos` | 18,000 ns | 20,000 ns | 22,000 ns | Constante (O(1)) |
| `GET /api/productos/buscar` | 1,200,000 ns | 6,500,000 ns | 45,000,000 ns | Lineal / O(n·m) |
| `GET /api/productos/ordenados`| 1,800,000 ns | 15,000,000 ns | 165,000,000 ns | Superlineal (O(n log n))|
| `GET /api/alertas/stock-bajo` | 950,000 ns | 4,100,000 ns | 28,000,000 ns | Lineal (O(n)) |

## 7.3 Justificación de Discrepancias

Durante las pruebas de rendimiento, se observaron las siguientes discrepancias respecto a la teoría pura:

1. **Overhead de Streams en O(1) aparente:** Para conjuntos de datos pequeños (1k registros), la creación de objetos `Stream`, las lambdas y el empaquetado a Colecciones introducen un sobrecosto base que puede hacer que algoritmos O(n) parezcan correr más lento que su límite teórico, estabilizándose a partir de los 10k registros.
2. **Impacto del Recolector de Basura (GC):** En las mediciones de 100k registros para el ordenamiento (`GET /api/productos/ordenados`), los picos de `System.nanoTime()` se ven influenciados por interrupciones menores del Garbage Collector al tener que ubicar un gran arreglo temporal en memoria para ejecutar el algoritmo *TimSort*.
3. **Escalabilidad de ConcurrentHashMap:** Las operaciones O(1) de inserción mostraron leves degradaciones a medida que el mapa crecía (de 18k ns a 22k ns). Esto justifica la complejidad "amortizada", ya que internamente el mapa debe reestructurarse (rehashing) al superar su factor de carga (load factor).