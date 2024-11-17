package ProyectoFinal.src.Models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ProyectoFinal.src.Fabricas.FabricaWorkers;

public class Manager {
    private List<File> chunks;
    private int numWorkers;

    public Manager(List<File> chunks, int numWorkers) {
        this.chunks = chunks;
        this.numWorkers = numWorkers;
    }

    public void startProcessing() {
        // Pool de hilos con el número de Workers calculado
        ExecutorService executor = Executors.newFixedThreadPool(numWorkers);

        // Asignar cada chunk a un Worker
        for (File chunk : chunks) {
            FabricaWorkers worker = new FabricaWorkers(chunk); // Crear el Worker
            executor.execute(worker); // Enviar la tarea al pool de hilos
        }

        // Finalizar el ExecutorService
        executor.shutdown();

        // Esperar a que todas las tareas terminen
        while (!executor.isTerminated()) {
            // Espera a que todos los workers finalicen
        }

        System.out.println("Todos los chunks han sido procesados.");
    }
}
