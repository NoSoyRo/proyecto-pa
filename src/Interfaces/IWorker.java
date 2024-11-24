package ProyectoFinal.src.Interfaces;

import java.lang.Runnable;
import java.util.Map;

public interface IWorker extends Runnable{
    void buscaStringEnColumna();

    Map<String, Object> getResultados();
}
