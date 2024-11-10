package ProyectoFinal.src.Fabricas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import ProyectoFinal.src.Interfaces.IArchivo;
import ProyectoFinal.src.Models.ArchivoCopiaTextoBuffered;

public class FabricaChunks extends ArchivoCopiaTextoBuffered{
    private String origen = null;
    private String destino = null;
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
    public FabricaChunks(String origen){
        this.origen = origen;
    }
    public void manufacturaChunks(int numeroChunks){
        // //ToDo pensar algoritmo ----------------------------------------------------------------------------------------------
        // objetoFile_archivoOrigen = new File(RUTA_DEFAULT, origen);
        // if (!verificarExistencia(objetoFile_archivoOrigen)) {
        //     System.out.printf(" %s \n", origen);
        // }
        // int cuentaChunksCreados = 0;
        // int cuentaCampos = 0;
        // try {
        //     archivoOrigen = new BufferedReader(new FileReader(objetoFile_archivoOrigen));
        //     destino = 
        //     tiempoInicio = System.nanoTime();
        //     /* Lee el contenido del archivoOrigen y lo escribe en archivoDestino */
            
        //     while ((linea = archivoOrigen.readLine()) != null) {
        //         if (cuentaLineas == 0) {
        //             campos = linea.split(SEPARADOR_COLUMNA_DEFAULT);
        //             cuentaCampos = campos.length;
        //         }
        //         cuentaLineas = cuentaLineas + 1;
        //         /* Copia TODO el archivo igual, descomente la sig linea si deseo observar */
        //         // archivoDestino.write(linea + "\n");
        //         /* Copia campos seleccionados, descomente las sig 2 lineas si deseo observar */
        //         // campos = linea.split(SEPARADOR_COLUMNA_DEFAULT);
        //         // archivoDestino.write(campos[0] + "," + campos[1] + "," + campos[2] + "," +
        //         // campos[3] + "\n");
        //     }
        //     tiempoFin = System.nanoTime();
        //     archivoOrigen.close();
        //     arr = new int[] {cuentaLineas, cuentaCampos};
        // } catch (IOException e) {
        //     System.err.println(e);
        // } finally {
        //     imprimirSalida(nombreArchivoOrigen, nombreArchivoDestino, tiempoInicio, tiempoFin);
        //     return arr;
        // }

    }
    
}
