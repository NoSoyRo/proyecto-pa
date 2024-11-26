package ProyectoFinal.src.Models;

import ProyectoFinal.src.Interfaces.*;
import java.io.File;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Worker implements IWorker {
    private File chunk;
    private Map<String, Object> resultados;

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
            resultados.put("PalabraClave", contarOcurrencias("ORIGEN")); // Test con "ORIGEN" en encabezado

            // Operaciones adicionales
            List<String> columnaSeleccionada = extraerColumna(0); // Cambia el índice para seleccionar otra columna
            if (!columnaSeleccionada.isEmpty() && esNumerico(columnaSeleccionada.get(0))) {
                // Calcular promedio y moda si la columna contiene datos numéricos
                List<Double> datosNumericos = columnaSeleccionada.stream()
                                                                 .map(Double::parseDouble)
                                                                 .collect(Collectors.toList());
                resultados.put("Promedio", calcularPromedio(datosNumericos));
                resultados.put("Moda", calcularModa(datosNumericos));
            } else {
                // Si la columna no es numérica, realizar conteo de frecuencias
                Map<String, Long> frecuencia = contarFrecuencia(columnaSeleccionada);
                resultados.put("Frecuencia", frecuencia);
            }

            // Buscar un texto en la columna
            String buscarTexto = "clave"; // Cambiar por texto deseado
            resultados.put("ResultadosBusqueda", buscaStringEnColumna(buscarTexto, 0)); // Buscar en la columna 0

        } catch (Exception e) {
            System.out.printf("Error procesando el archivo %s: %s%n", chunk.getName(), e.getMessage());
        }
    }

    @Override
    public void buscaStringEnColumna() {
        try {
            String textoBuscar = "clave"; // Cambiar o parametrizar el texto a buscar
            int columnaIndex = 0; // Cambiar el índice para buscar en otra columna
            List<String> resultadosBusqueda = buscaStringEnColumna(textoBuscar, columnaIndex);
            System.out.println("Resultados de búsqueda: " + resultadosBusqueda);
        } catch (IOException e) {
            System.out.printf("Error buscando texto en el archivo %s: %s%n", chunk.getName(), e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getResultados() {
        return resultados;
    }

    private int contarLineas() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(chunk));
        int count = 0;
        while (reader.readLine() != null) {
            count++;
        }
        reader.close();
        return count;
    }

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

    private boolean esNumerico(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private double calcularPromedio(List<Double> data) {
        return data.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    private double calcularModa(List<Double> data) {
        Map<Double, Long> freqMap = data.stream()
                                        .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
        return freqMap.entrySet().stream()
                      .max(Map.Entry.comparingByValue())
                      .get()
                      .getKey();
    }

    private Map<String, Long> contarFrecuencia(List<String> data) {
        return data.stream()
                   .collect(Collectors.groupingBy(valor -> valor, Collectors.counting()));
    }

    private List<String> extraerColumna(int columnaIndex) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(chunk));
        List<String> columna = new ArrayList<>();
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] valores = linea.split(",");
            if (valores.length > columnaIndex) {
                columna.add(valores[columnaIndex]);
            }
        }
        reader.close();
        return columna;
    }

    private List<String> buscaStringEnColumna(String texto, int columnaIndex) throws IOException {
        List<String> columna = extraerColumna(columnaIndex);
        return columna.stream()
                      .filter(valor -> valor.contains(texto))
                      .collect(Collectors.toList());
    }
}
