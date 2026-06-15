# TP5 — Proyecto Grupal Integrador: Gestión de Inventario Inteligente 📦

**Universidad Nacional de La Rioja (UNLaR) - Ingeniería en Sistemas de Información**

Este proyecto es un microservicio REST desarrollado para la gestión eficiente de un depósito inteligente. Implementa una arquitectura limpia en capas, garantizando la concurrencia segura y aplicando principios de diseño de software a nivel empresarial.

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.5.15
* **Gestor de dependencias:** Maven
* **Almacenamiento:** Memoria (Implementación thread-safe con `ConcurrentHashMap`)
* **Documentación de API:** SpringDoc OpenAPI (Swagger UI)

## 🏗️ Arquitectura y Patrones de Diseño

El sistema respeta estrictamente los **Principios SOLID** y utiliza:

1.  **Arquitectura en Capas:** Separación clara entre Controladores (REST/DTOs), Servicios (Lógica de Negocio) y Repositorios (Acceso a Datos).
2.  **Repositorios Genéricos:** Uso de polimorfismo paramétrico con `IGenericRepository<T, ID>` y una clase base abstracta `GenericInMemoryRepository` para evitar código duplicado (DRY).
3.  **Patrón Strategy (OCP):** Reglas de alerta de stock (Bajo/Crítico) intercambiables dinámicamente según la configuración.
4.  **Concurrencia Segura:** Uso de `AtomicInteger` para garantizar que las modificaciones al stock mediante entradas y salidas sean atómicas y *thread-safe*.

## 🚀 Cómo ejecutar el proyecto

### 1. Compilación
Para compilar el proyecto y ejecutar las pruebas y empaquetado, ejecutar en la terminal:
```bash
mvn clean package
```

### 2. Ejecución
Para levantar el servidor embebido (Tomcat) en el puerto `8081`, ejecutar:

```bash
mvn spring-boot:run
```

## 📚 Documentación de la API (Swagger)

Una vez que la aplicación esté en ejecución, se puede interactuar gráficamente con todos los endpoints (crear productos, listar categorías, registrar movimientos, etc.) accediendo a:

👉 **[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)**

## 📊 Informe de Rendimiento

El proyecto incluye un análisis empírico y teórico de la complejidad algorítmica (Big O) de cada endpoint operativo.
* **Endpoint de medición:** `GET /api/admin/performance-report`
* **Documento detallado:** Se puede consultar el archivo `PERFORMANCE.md` en la raíz de este repositorio para ver el desglose completo.

## 👥 Autores

* **Camila Lucero**
* **Joaquin Codocea**
