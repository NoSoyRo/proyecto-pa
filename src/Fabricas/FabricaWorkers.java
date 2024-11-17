package ProyectoFinal.src.Fabricas;

import ProyectoFinal.src.Interfaces.TareaWorker;

import java.io.File;

public class FabricaWorkers implements TareaWorker{
    private File chunkFile;

    public FabricaWorkers(File chunkFile) {
        this.chunkFile = chunkFile;
    }

    @Override
    public void ejecutarTarea() {
        System.out.println("Procesando chunk: " + chunkFile.getName());
        // lógica para procesar el archivo.
    }

    @Override
    public void run() {
        ejecutarTarea();
    }
}
