package ProyectoFinal.src.Models;

import ProyectoFinal.src.Interfaces.*;
//import ProyectoFinal.src.Fabricas.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    public List<String> buscaStringEnColumnaN(int N) {
        List<String> resultados = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] columnas = linea.split(","); // Separador ','
                if (N >= 0 && N < columnas.length) {
                    resultados.add(columnas[N]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultados;
    }
}
