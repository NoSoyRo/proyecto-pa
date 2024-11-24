package ProyectoFinal.src.Models;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Map;

import ProyectoFinal.src.Fabricas.*;
import ProyectoFinal.src.Interfaces.*;
import ProyectoFinal.src.Utils.*;


public class Manager {
    private List<IWorker> workers;
    private int numWorkers;
    private IFabricaWorker fabricaWorker;
    private ExecutorService executorService;
    private ConcurrentHashMap<String, Map<String, Object>> resultadosGlobales;

    public Manager(List<File> chunks, int numWorkers) {
        this.numWorkers = numWorkers;
        this.workers = new ArrayList<>();
        this.resultadosGlobales = new ConcurrentHashMap<>();

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
            executorService.execute(() -> {
                worker.run(); // Ejecutar el Worker
                resultadosGlobales.put(worker.toString(), worker.getResultados()); // Recoger resultados
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Espera a que todos los workers finalicen
        }
        System.out.println("Resultados globales:");
        resultadosGlobales.forEach((worker, resultados) -> {
            System.out.println("Worker: " + worker);
            System.out.println("Resultados: " + resultados);
        });
    }
}
