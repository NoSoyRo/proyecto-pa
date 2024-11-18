package ProyectoFinal.src.Fabricas;

import ProyectoFinal.src.Interfaces.*;
import ProyectoFinal.src.Models.*;
import java.io.File;

public class FabricaWorkers implements IFabricaWorker {
    @Override
    public IWorker creaWorker(File file) {
        return new Worker(file); 
    }
}
