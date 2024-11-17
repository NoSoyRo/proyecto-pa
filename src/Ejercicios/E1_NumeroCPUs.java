package ProyectoFinal.src.Ejercicios;

import java.lang.Runtime.*;

public class E1_NumeroCPUs {
    public static void main(String[] args){
        int CPUs = Runtime.getRuntime().availableProcessors();
        System.out.println("\t Numero de CPU's: \t" + CPUs );
    }
}
