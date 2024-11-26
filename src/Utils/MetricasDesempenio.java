package ProyectoFinal.src.Utils;
import java.util.HashMap;
import java.util.Map;

public class MetricasDesempenio {
    private static final Map<String, Long> tiemposEjecucion = new HashMap<>();

    public static void registrarTiempo(String tipoEjecucion, long tiempo) {
        tiemposEjecucion.put(tipoEjecucion, tiempo);
    }

    public static void mostrarResumen() {
        System.out.println("\nResumen de Tiempos de Ejecución:");
        tiemposEjecucion.forEach((tipo, tiempo) -> System.out.printf("%s: %d ms%n", tipo, tiempo));

        // Calcular y mostrar SpeedUp
        if (tiemposEjecucion.containsKey("Secuencial") && tiemposEjecucion.containsKey("Hilos de Plataforma")) {
            double speedUpPlataforma = (double) tiemposEjecucion.get("Secuencial") / tiemposEjecucion.get("Hilos de Plataforma");
            System.out.printf("SpeedUp (Secuencial vs Plataforma): %.2f%n", speedUpPlataforma);
        }

        if (tiemposEjecucion.containsKey("Secuencial") && tiemposEjecucion.containsKey("Hilos Virtuales")) {
            double speedUpVirtuales = (double) tiemposEjecucion.get("Secuencial") / tiemposEjecucion.get("Hilos Virtuales");
            System.out.printf("SpeedUp (Secuencial vs Virtuales): %.2f%n", speedUpVirtuales);
        }
    }
}
