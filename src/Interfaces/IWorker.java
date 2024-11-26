package ProyectoFinal.src.Interfaces;

import java.lang.Runnable;
import java.io.File;
import java.util.Map;

public interface IWorker extends Runnable{
    File getArchivoChunk();
}
