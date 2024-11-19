package ProyectoFinal.src.Models;

import ProyectoFinal.src.Interfaces.*;
//import ProyectoFinal.src.Fabricas.*;
import java.io.File;


public class Worker implements IWorker{
    private File file;

    public Worker(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        System.out.println("Procesando el archivo: " + file.getName());
        // Llama a la lógica del análisis
        buscaStringEnColumna();
    }

    @Override
    public void buscaStringEnColumna(){
    };
}
