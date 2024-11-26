package ProyectoFinal.src.Models;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;

import ProyectoFinal.src.Fabricas.*;
import ProyectoFinal.src.Interfaces.*;
import ProyectoFinal.src.Utils.*;


public class Manager {
    private List<IWorker> workers;
    private int numWorkers;
    private IFabricaWorker fabricaWorker;
    private ExecutorService executorService;

    public Manager(List<File> chunks, int numWorkers) {
        this.numWorkers = numWorkers;
        this.workers = new ArrayList<>();
        // Metodo para crear los Workers
        crearWorkers(chunks);
        // Seleccionar el tipo de hilos desde el menú
        int threadOption = MenuTerminal.mostrarMenuSeleccionHilos();
        configurarExecutorService(threadOption);
    }


    private void crearWorkers(List<File> chunks) {
        this.fabricaWorker = new FabricaWorkers();
        for (File chunk : chunks) {
            IWorker worker = fabricaWorker.creaWorker(chunk);
            workers.add(worker);
        }
        System.out.println("Workers creados: " + workers.size());
    }

    private void configurarExecutorService(int threadOption) {
        switch (threadOption) {
            case 1: // Hilos de plataforma
                this.executorService = Executors.newFixedThreadPool(numWorkers);
                System.out.println("Usando hilos de plataforma.");
                break;

            case 2: // Hilos virtuales
                this.executorService = Executors.newVirtualThreadPerTaskExecutor();
                System.out.println("Usando hilos virtuales.");
                break;

            default: // Valor por defecto
                this.executorService = Executors.newFixedThreadPool(numWorkers);
                System.out.println("Opción desconocida. Usando hilos de plataforma por defecto.");
        }
    }

    public void comienzaProceso() {

        for (IWorker worker : workers) {
            executorService.execute(worker);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Espera a que todos los workers finalicen
        }
        System.out.println("Procesamiento finalizado.");
        escribirResultadosEnArchivo();
//        if (archivoResultados != null) {
//            procesarResultados();
//        } else {
//            System.err.println("No se pudo encontrar el archivo de resultados. Verifique la ejecución de los Workers.");
//        }
    }

    private void escribirResultadosEnArchivo() {
        System.out.println("\nEscribiendo resultados en el archivo...");
        Map<String, Map<String, Object>> resultadosGlobales = Worker.getResultadosGlobales();

        try {
            // Crear el archivo en el directorio abuelo
            File directorioAbuelo = workers.get(0).getArchivoChunk().getParentFile().getParentFile();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
            File archivoResultados = new File(directorioAbuelo, "resultados_filtered(" + timestamp + ").csv");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoResultados))) {
                // Escribir encabezados
                if (!resultadosGlobales.isEmpty()) {
                    Map<String, Object> primerResultado = resultadosGlobales.values().iterator().next();
                    String encabezados = String.join(",", primerResultado.keySet());
                    writer.write("WorkerID," + encabezados + "\n");
                }

                // Escribir filas
                for (Map.Entry<String, Map<String, Object>> entry : resultadosGlobales.entrySet()) {
                    String workerId = entry.getKey();
                    Map<String, Object> resultados = entry.getValue();
                    StringBuilder fila = new StringBuilder(workerId + ",");
                    resultados.values().forEach(valor -> fila.append(valor).append(","));
                    writer.write(fila.substring(0, fila.length() - 1) + "\n"); // Eliminar última coma
                }
            }
            System.out.println("Resultados escritos en: " + archivoResultados.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir resultados en el archivo: " + e.getMessage());
        }
    }

    private void procesarResultados() {
    }
}

