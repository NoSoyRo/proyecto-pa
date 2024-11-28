# ETL de CSV para el Análisis Eficiente de Logs de Tráfico en Redes del Internet

## Descripción del Proyecto
Este proyecto es un sistema **ETL** (Extract, Transform, Load) diseñado para el **análisis eficiente de logs de tráfico en redes del internet**. Utiliza archivos **CSV** como fuente de datos y aplica técnicas avanzadas de procesamiento concurrente para extraer, transformar y cargar información de una manera ágil y escalable.

El proyecto adopta un diseño orientado a objetos y hace uso de patrones de diseño como el **patrón de fábrica** y el **Modelo-Vista-Controlador (MVC)** para estructurar los componentes de manera eficiente y modular.

## Características
- **Extracción y Transformación de Datos**: Procesa registros de tráfico desde archivos CSV.
- **Multithreading**: Utiliza hilos para distribuir el trabajo y procesar múltiples fragmentos de datos simultáneamente, aumentando la eficiencia y reduciendo el tiempo de procesamiento.
- **Escalabilidad**: Gracias al uso de hilos y la gestión optimizada de recursos, el sistema puede manejar grandes volúmenes de datos.
- **Interfaz en Línea de Comandos**: Incluye una interfaz interactiva que facilita la ejecución y configuración del sistema.

## Tecnologías Utilizadas
- **Java**: Lenguaje principal del proyecto.
- **ExecutorService**: Manejo eficiente de hilos y paralelismo.
- **Patrones de Diseño**: MVC, Fábrica y otros patrones para garantizar la extensibilidad y la modularidad.

## Arquitectura del Proyecto
El proyecto sigue una arquitectura modular basada en diferentes componentes que interactúan de forma clara:
- **Models**: Representan las entidades del dominio, como archivos de logs y workers.
- **Fabricas**: Gestionan la creación de objetos complejos, como workers y chunks de datos.
- **Interfaces**: Definen los contratos que aseguran la interoperabilidad de los distintos componentes.
- **Utils**: Proveen funcionalidades auxiliares, como un menú interactivo para la terminal.

## Cómo Usar
1. **Clonar el Repositorio**
   ```bash
   git clone https://github.com/tu_usuario/etl-csv-log-analisis.git
   cd etl-csv-log-analisis
   ```

2. **Compilar el Proyecto**
   Puedes usar el script de compilación incluido:
   ```
   compile.bat
   ```
   O compilar manualmente:
   ```bash
   javac -d out -sourcepath src src/Models/*.java src/Fabricas/*.java src/Interfaces/*.java src/Utils/*.java src/ProcesoMain.java
   ```

3. **Ejecutar el Proyecto**
   Una vez compilado, ejecuta el archivo principal:
   ```
   java -cp out ProyectoFinal.ProcesoMain
   ```

## Ejemplo de Uso
Este proyecto está diseñado para ser utilizado en la línea de comandos. Al ejecutarlo, el menú interactivo permite especificar los archivos CSV de entrada, configurar los parámetros de ETL y comenzar el procesamiento.

### Flujo de Trabajo
1. **Extracción**: Se cargan los datos del archivo CSV.
2. **Transformación**: Los datos se dividen en chunks que son procesados en paralelo por los workers.
3. **Carga**: Los resultados se almacenan para su análisis posterior.

## Ventajas del Proyecto
- **Eficiencia**: El uso de hilos permite el procesamiento paralelo, disminuyendo considerablemente el tiempo total de análisis.
- **Escalabilidad**: Con más recursos (hilos, capacidad de CPU), el sistema puede procesar volúmenes crecientes de datos.
- **Extensibilidad**: La modularidad del código permite agregar nuevas funcionalidades de manera sencilla.

## Estructura del Repositorio
- `src/Models/`: Clases que representan la estructura de los datos y entidades.
- `src/Fabricas/`: Clases responsables de la creación de objetos.
- `src/Interfaces/`: Definición de contratos para clases.
- `src/Utils/`: Herramientas y funcionalidades auxiliares.
- `ProcesoMain.java`: Punto de entrada del proyecto.

## Script de Compilación
Se incluye un script Batch para compilar y ejecutar el proyecto fácilmente:
```bat
@echo off
REM Navigate to the project directory (if needed, adjust the path)
cd /d %~dp0

REM Compile the Java files
echo Compilando archivos...
javac -d out -sourcepath src src/Models/*.java src/Fabricas/*.java src/Interfaces/*.java src/Utils/*.java src/ProcesoMain.java

REM Check if compilation was successful
if %errorlevel% equ 0 (
    echo Compilación correcta, ejecutando archivos de prueba
    REM Run the ProcesoMain class
    java -cp out ProyectoFinal.ProcesoMain
) else (
    echo La compilación falló
)
pause
```

## Contribuciones
Las contribuciones son bienvenidas. Si deseas contribuir, por favor abre un issue o envía un pull request para discutir los cambios que te gustaría realizar.

## Licencia
Este proyecto está bajo la licencia MIT. Consulta el archivo `LICENSE` para más detalles.

## Contacto
Desarrollado por Rodrigo Moreno. Si tienes alguna pregunta, no dudes en contactarme a través de mi perfil de GitHub.

