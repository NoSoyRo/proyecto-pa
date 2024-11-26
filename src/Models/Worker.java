package ProyectoFinal.src.Models;

import ProyectoFinal.src.Interfaces.*;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;



public class Worker implements IWorker{
    private File chunk;
    private Map<String, Object> resultados;
    private static final Map<String, Map<String, Object>> resultadosGlobales = new HashMap<>();

    public Worker(File file) {
        this.chunk = file;
        this.resultados = new HashMap<>();
    }

    @Override
    public void run() {
        System.out.println("Procesando el archivo: " + chunk.getName());
        try {
            // Simular análisis de datos
            resultados.put("Chunk", chunk.getName());
            resultados.put("TotalLineas", contarLineas());
            resultados.put("PalabraClave", contarOcurrencias("ORIGEN")); // Test con "ORIGEN"
            resultados.put("Promedio", calcularPromedio());
            resultados.put("ModaNumerica", calcularModa());
            resultados.put("ModaTextual", calcularModa(true));
            resultados.put("Mediana", calcularMediana());
            
            
            // Guardar resultados en el HashMap global de manera sincronizada
            guardarResultadosSincronizado(chunk.getName(), resultados);
        } catch (Exception e) {
            System.out.printf("Error procesando el archivo %s: %s%n", chunk.getName(), e.getMessage());
        }
    }

    private static synchronized void guardarResultadosSincronizado(String workerId, Map<String, Object> resultados) {
        resultadosGlobales.put(workerId, resultados);
        System.out.println("Resultados guardados para: " + workerId);
    }

    public static Map<String, Map<String, Object>> getResultadosGlobales() {
        return resultadosGlobales;
    }

    @Override
    public File getArchivoChunk() {
        return chunk;
    }


    //Ej 1 de lo que puede hacer el Worker
    private int contarLineas() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(chunk));
        int count = 0;
        while (reader.readLine() != null) {
            count++;
        }
        reader.close();
        return count;
    }
    //Ej 2 de lo que puede hacer el Worker
    private int contarOcurrencias(String palabraClave) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(chunk));
        int count = 0;
        String linea;
        while ((linea = reader.readLine()) != null) {
            if (linea.contains(palabraClave)) {
                count++;
            }
        }
        reader.close();
        return count;
    }
        //Moda, Media Y Mediana 

    //Promedio 
    private double calcularPromedio() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(chunk));
        double suma = 0;
        int totalNumeros = 0;
        String linea;
        while ((linea = reader.readLine()) != null) {
            try {
                suma += Double.parseDouble(linea.trim()); 
                totalNumeros++;
            } catch (NumberFormatException e) {
                // Ignorar líneas no numéricas
            }
        }
        reader.close();
        return totalNumeros == 0 ? 0 : suma / totalNumeros;
    }


    //Mediana
    private double calcularMediana() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(chunk));
    List<Double> numeros = new ArrayList<>();
    String linea;
    while ((linea = reader.readLine()) != null) {
        try {
            numeros.add(Double.parseDouble(linea.trim()));
        } catch (NumberFormatException e) {
            // Ignorar líneas no numéricas
        }
    }
    reader.close();

    if (numeros.isEmpty()) {
        return Double.NaN; // NaN si no hay datos
    }

    Collections.sort(numeros);
    int n = numeros.size();
    if (n % 2 == 0) {
        return (numeros.get(n / 2 - 1) + numeros.get(n / 2)) / 2.0;
    } else {
        return numeros.get(n / 2);
    }
}

private double calcularModa() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(chunk));
    Map<Double, Integer> frecuencias = new HashMap<>();
    String linea;

    while ((linea = reader.readLine()) != null) {
        try {
            double numero = Double.parseDouble(linea.trim()); // Convertir a Double
            frecuencias.put(numero, frecuencias.getOrDefault(numero, 0) + 1);
        } catch (NumberFormatException e) {
            // Ignorar líneas no numéricas
        }
    }
    reader.close();

    // Calcular la moda
    Double moda = null;
    int maxFrecuencia = 0;

    for (Map.Entry<Double, Integer> entry : frecuencias.entrySet()) {
        if (entry.getValue() > maxFrecuencia) {
            moda = entry.getKey();
            maxFrecuencia = entry.getValue();
        }
    }

    return (moda != null) ? moda : Double.NaN; // Si no hay moda, devuelve NaN
}


private String calcularModa(boolean esTextual) throws IOException {
    if (!esTextual) {
        throw new IllegalArgumentException("Usa este método solo para calcular moda textual.");
    }

    BufferedReader reader = new BufferedReader(new FileReader(chunk));
    Map<String, Integer> frecuencias = new HashMap<>();
    String linea;

    // Leer línea por línea
    while ((linea = reader.readLine()) != null) {
        linea = linea.trim();
        if (!linea.isEmpty()) {
            frecuencias.put(linea, frecuencias.getOrDefault(linea, 0) + 1);
        }
    }
    reader.close();

    // Calcular la moda
    String moda = null;
    int maxFrecuencia = 0;

    for (Map.Entry<String, Integer> entry : frecuencias.entrySet()) {
        if (entry.getValue() > maxFrecuencia) {
            moda = entry.getKey();
            maxFrecuencia = entry.getValue();
        }
    }

    return (moda != null) ? moda : "No hay moda"; // Si no hay moda, devuelve un mensaje
}
}
