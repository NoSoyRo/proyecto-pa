package ProyectoFinal.src.Interfaces;

import java.lang.Runnable;
import java.util.List;

public interface IWorker extends Runnable{
     List<String> buscaStringEnColumnaN(int N);
     void setFile(String fileName);
}
