package ProyectoFinal.src;

import ProyectoFinal.src.Fabricas.*;
import ProyectoFinal.src.Models.*;
import ProyectoFinal.src.Utils.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProcesoMain {
    public static void main(String[] args) {
        String ARCHIVO = "covid.csv";
        int numWorkers = Runtime.getRuntime().availableProcessors() - 1;

        // Generar los chunks
        FabricaChunks fabricaChunks = new FabricaChunks(ARCHIVO);
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

        boolean continuar = true;
        while (continuar) {
            int opcion = MenuTerminal.mostrarMenuPrincipal(); // Mostrar menú principal

            switch (opcion) {
                case 1 -> {
                    long tiempo = ejecutarManager(chunks, numWorkers, 1); // Ejecutar con Hilos de Plataforma
                    MetricasDesempenio.registrarTiempo("Hilos de Plataforma", tiempo);
                }
                case 2 -> {
                    long tiempo = ejecutarManager(chunks, numWorkers, 2); // Ejecutar con Hilos Virtuales
                    MetricasDesempenio.registrarTiempo("Hilos Virtuales", tiempo);
                }
                case 3 -> {
                    long tiempo = ejecutarSecuencial(ARCHIVO); // Ejecutar de forma secuencial
                    MetricasDesempenio.registrarTiempo("Secuencial", tiempo);
                }
                case 4 -> {
                    System.out.println("Mostrando resumen y saliendo...");
                    MetricasDesempenio.mostrarResumen();
                    continuar = false;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static long ejecutarManager(List<File> chunks, int numWorkers, int threadOption) {
        long startTime = System.currentTimeMillis();

        // Crear el Manager con la opción de hilos seleccionada
        Manager manager = new Manager(chunks, numWorkers, threadOption);
        manager.comienzaProceso();

        long endTime = System.currentTimeMillis();
        String tipoHilo = (threadOption == 2) ? "Hilos Virtuales" : "Hilos de Plataforma";
        System.out.printf("Tiempo total con %s: %d ms%n", tipoHilo, (endTime - startTime));
        return endTime - startTime;
    }

    private static long ejecutarSecuencial(String archivoOriginal) {
        long startTime = System.currentTimeMillis();

        File archivo = new File(FabricaChunks.RUTA_DEFAULT, archivoOriginal);

        if (archivo.exists()) {
            Manager manager = new Manager(archivo);
            manager.procesarSecuencial();
        } else {
            System.err.println("El archivo original no se encuentra: " + archivoOriginal);
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("Tiempo total procesando de forma secuencial: %d ms%n", (endTime - startTime));
        return endTime - startTime;
    }
}

