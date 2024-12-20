package ProyectoFinal.src.Fabricas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ProyectoFinal.src.Interfaces.IArchivo;
import ProyectoFinal.src.Models.ArchivoCopiaTextoBuffered;

public class FabricaChunks extends ArchivoCopiaTextoBuffered {
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

    public FabricaChunks(String origen) {
        this.origen = origen;
    }

    public void manufacturaChunks(int numeroChunks) {
        File directorioChunks = new File(RUTA_DEFAULT_NCHUNKS);
        if (!directorioChunks.exists()) {
            if (directorioChunks.mkdir()) {
                System.out.println("Directorio 'NChunks' creado en: " + directorioChunks.getAbsolutePath());
            } else {
                System.err.println("Error al crear el directorio 'NChunks'. Verifique los permisos.");
                return;
            }
        }

        // //ToDo pensar algoritmo
        // ----------------------------------------------------------------------------------------------
        objetoFile_archivoOrigen = new File(RUTA_DEFAULT, origen);
        if (!verificarExistencia(objetoFile_archivoOrigen)) {
            System.out.printf(" %s \n", origen);
        }
        int cuentaChunksCreados = 1;
        int cuentaLineas = 0;
        String header = null;

        int[] dimensiones = getDimensiones(origen);

        try {
            archivoOrigen = new BufferedReader(new FileReader(objetoFile_archivoOrigen));
            String origenNombre = origen.substring(0, origen.length() - 4);
            String nombreArchivoDestino = "copia_" + origenNombre;
            String nombreArchivoDestinoAux = new String(nombreArchivoDestino);
            System.out.println(nombreArchivoDestino);
            Integer tamanioChunk = (Integer) dimensiones[0] / numeroChunks;
            System.out.println(tamanioChunk);
            tiempoInicio = System.nanoTime();

            // Leer el header
            header = archivoOrigen.readLine();
            if (header == null) {
                throw new IOException("El archivo está vacío o no tiene encabezado.");
            }

            /* Lee el contenido del archivoOrigen y lo escribe en archivoDestino */
            nombreArchivoDestinoAux = nombreArchivoDestino + Integer.toString(cuentaChunksCreados) + ".csv";
            objetoFile_archivoDestino = new File(RUTA_DEFAULT_NCHUNKS, nombreArchivoDestinoAux);
            crearArchivo(objetoFile_archivoDestino);
            archivoDestino = new BufferedWriter(new FileWriter(objetoFile_archivoDestino));
            archivoDestino.write(header + "\n");
            while ((linea = archivoOrigen.readLine()) != null) {
                if (cuentaLineas == 0) {
                    System.out.println("En headings");
                    cuentaLineas = 1;
                    continue;
                }
                if (cuentaLineas == tamanioChunk + 1) { // +1 ya que necesitamos reinicial al cuentaLineal
                    // cierra el archivo
                    // crea uno nuevo con nuevo numero
                    // actualiza el apuntador al archivo nuevo
                    // reinicia cuenta lineas
                    cuentaChunksCreados = cuentaChunksCreados + 1;
                    archivoDestino.close();
                    nombreArchivoDestinoAux = nombreArchivoDestino + Integer.toString(cuentaChunksCreados) + ".csv";
                    objetoFile_archivoDestino = new File(RUTA_DEFAULT_NCHUNKS, nombreArchivoDestinoAux);
                    crearArchivo(objetoFile_archivoDestino);
                    archivoDestino = new BufferedWriter(new FileWriter(objetoFile_archivoDestino));
                    // para agregar el header a cada copia.
                    archivoDestino.write(header + "\n");

                    cuentaLineas = 1;
                    System.out.println(nombreArchivoDestino);
                }

                archivoDestino.write(linea + "\n");

                // System.out.println(linea);
                cuentaLineas++;
                /* Copia TODO el archivo igual, descomente la sig linea si deseo observar */
                // archivoDestino.write(linea + "\n");
                /* Copia campos seleccionados, descomente las sig 2 lineas si deseo observar */
                // campos = linea.split(SEPARADOR_COLUMNA_DEFAULT);
                // archivoDestino.write(campos[0] + "," + campos[1] + "," + campos[2] + "," +
                // campos[3] + "\n");
            }
            archivoDestino.close();
            tiempoFin = System.nanoTime();
            archivoOrigen.close();
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            imprimirSalida(origen, origen, tiempoInicio, tiempoFin);
        }

    }

}
