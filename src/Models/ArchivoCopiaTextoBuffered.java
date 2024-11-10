package ProyectoFinal.src.Models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoCopiaTextoBuffered extends Archivo {
    private String nombreArchivoOrigen = null;
    private String nombreArchivoDestino = null;
    private File objetoFile_archivoOrigen = null;
    private File objetoFile_archivoDestino = null;
    // private FileReader objetoFileReader_archivoOrigen = null;
    // private FileWriter objetoFileWriter_archivoDestino = null;
    private BufferedReader archivoOrigen = null;
    private BufferedWriter archivoDestino = null;
    private int bloqueBytes = 0;
    private long tiempoInicio, tiempoFin;
    private String linea = null;
    private String[] campos = null;

    @Override
    public void copiar(String origen) {
        nombreArchivoOrigen = origen;
        nombreArchivoDestino = "copia_" + nombreArchivoOrigen;
        // callibrating remove later
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" + currentDir);
        //
        objetoFile_archivoOrigen = new File(RUTA_DEFAULT, nombreArchivoOrigen);

        if (!verificarExistencia(objetoFile_archivoOrigen)) {
            System.out.printf(" %s \n", nombreArchivoOrigen);
            return;
        }

        objetoFile_archivoDestino = new File(RUTA_DEFAULT, nombreArchivoDestino);

        try {
            archivoOrigen = new BufferedReader(new FileReader(objetoFile_archivoOrigen));
            archivoDestino = new BufferedWriter(new FileWriter(objetoFile_archivoDestino));

            tiempoInicio = System.nanoTime();
            /* Lee el contenido del archivoOrigen y lo escribe en archivoDestino */
            while ((linea = archivoOrigen.readLine()) != null) {
                /* Copia TODO el archivo igual, descomente la sig linea si deseo observar */
                archivoDestino.write(linea + "\n");

                /* Copia campos seleccionados, descomente las sig 2 lineas si deseo observar */
                // campos = linea.split(SEPARADOR_COLUMNA_DEFAULT);
                // archivoDestino.write(campos[0] + "," + campos[1] + "," + campos[2] + "," +
                // campos[3] + "\n");
            }
            tiempoFin = System.nanoTime();

            archivoOrigen.close();
            archivoDestino.close();

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            imprimirSalida(nombreArchivoOrigen, nombreArchivoDestino, tiempoInicio, tiempoFin);
        }
    }

    @Override
    public void copiar(String origen, String destino) {
        nombreArchivoOrigen = origen;
        nombreArchivoDestino = destino;

        // repite codigo

        objetoFile_archivoOrigen = new File(RUTA_DEFAULT, nombreArchivoOrigen);

        if (!verificarExistencia(objetoFile_archivoOrigen)) {
            System.out.printf(" %s \n", nombreArchivoOrigen);
            return;
        }

        objetoFile_archivoDestino = new File(RUTA_DEFAULT, nombreArchivoDestino);

        // F

        try {
            archivoOrigen = new BufferedReader(new FileReader(objetoFile_archivoOrigen));
            archivoDestino = new BufferedWriter(new FileWriter(objetoFile_archivoDestino));

            tiempoInicio = System.nanoTime();
            /* Lee el contenido del archivoOrigen y lo escribe en archivoDestino */
            while ((linea = archivoOrigen.readLine()) != null) {
                /* Copia TODO el archivo igual, descomente la sig linea si deseo observar */
                archivoDestino.write(linea + "\n");
                /* Copia campos seleccionados, descomente las sig 2 lineas si deseo observar */
                // campos = linea.split(SEPARADOR_COLUMNA_DEFAULT);
                // archivoDestino.write(campos[0] + "," + campos[1] + "," + campos[2] + "," +
                // campos[3] + "\n");
            }
            tiempoFin = System.nanoTime();
            archivoOrigen.close();
            archivoDestino.close();

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            imprimirSalida(nombreArchivoOrigen, nombreArchivoDestino, tiempoInicio, tiempoFin);
        }
    }
    // Regresa un arreglo con dos entradas: el primer elemento nos dice la cantidad de registros y la segunda me dice la cantidad de columnas
    public int[] getDimensiones(String origen){
        nombreArchivoOrigen = origen;
        objetoFile_archivoOrigen = new File(RUTA_DEFAULT, nombreArchivoOrigen);
        if (!verificarExistencia(objetoFile_archivoOrigen)) {
            System.out.printf(" %s \n", nombreArchivoOrigen);
            return new int[] {-1,-1};
        }
        int cuentaLineas = 0;
        int cuentaCampos = 0;
        int[] arr = new int[] {cuentaLineas, cuentaCampos};
        try {
            archivoOrigen = new BufferedReader(new FileReader(objetoFile_archivoOrigen));
            tiempoInicio = System.nanoTime();
            /* Lee el contenido del archivoOrigen y lo escribe en archivoDestino */
            
            while ((linea = archivoOrigen.readLine()) != null) {
                if (cuentaLineas == 0) {
                    campos = linea.split(SEPARADOR_COLUMNA_DEFAULT);
                    cuentaCampos = campos.length;
                }
                cuentaLineas = cuentaLineas + 1;
                /* Copia TODO el archivo igual, descomente la sig linea si deseo observar */
                // archivoDestino.write(linea + "\n");
                /* Copia campos seleccionados, descomente las sig 2 lineas si deseo observar */
                // campos = linea.split(SEPARADOR_COLUMNA_DEFAULT);
                // archivoDestino.write(campos[0] + "," + campos[1] + "," + campos[2] + "," +
                // campos[3] + "\n");
            }
            tiempoFin = System.nanoTime();
            archivoOrigen.close();
            arr = new int[] {cuentaLineas, cuentaCampos};
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            imprimirSalida(nombreArchivoOrigen, nombreArchivoDestino, tiempoInicio, tiempoFin);
            return arr;
        }

    }

}
