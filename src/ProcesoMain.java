package ProyectoFinal.src;

import ProyectoFinal.src.Fabricas.FabricaChunks;
import ProyectoFinal.src.Models.Manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProcesoMain {
    public static void main(String[] args) {
        // Calcular el número de Workers dejando 1 núcleo para el Manager
        int numWorkers = Runtime.getRuntime().availableProcessors() - 1;

        // Generar los chunks
        FabricaChunks fabricaChunks = new FabricaChunks("covid.csv");
        fabricaChunks.manufacturaChunks(numWorkers);

        // Obtener los archivos chunks generados
        File carpetaChunks = new File(FabricaChunks.RUTA_DEFAULT_NCHUNKS);
        File[] listaChunks = carpetaChunks.listFiles();
        List<File> chunks = new ArrayList<>();
        if (listaChunks != null) {
            for (File chunk : listaChunks) {
                chunks.add(chunk);
            }
        }

        // Crear el Manager y procesar los chunks
        Manager manager = new Manager(chunks, numWorkers);
        manager.startProcessing();
    }
}