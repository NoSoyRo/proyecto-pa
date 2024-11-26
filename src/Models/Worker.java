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
}
