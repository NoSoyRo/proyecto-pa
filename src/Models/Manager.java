package ProyectoFinal.src.Models;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ProyectoFinal.src.Fabricas.*;
import ProyectoFinal.src.Interfaces.*;


public class Manager {
    private List<File> chunks;
    private int numWorkers;
    private IFabricaWorker fabricaWorker;

    public Manager(List<File> chunks, int numWorkers) {
        this.chunks = chunks;
        this.numWorkers = numWorkers;
        this.fabricaWorker = new FabricaWorkers();
    }

    public void comienzaProceso() {
        ExecutorService executor = Executors.newFixedThreadPool(numWorkers);

        // Asignar cada chunk a un Worker
        for (File chunk : chunks) {
            IWorker worker = fabricaWorker.creaWorker(chunk); // Crear el Worker
            executor.execute(worker); // Enviar la tarea al pool de hilos
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Espera a que todos los workers finalicen
        }

        System.out.println("Todos los chunks han sido procesados.");
    }
}
