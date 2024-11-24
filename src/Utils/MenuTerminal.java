package ProyectoFinal.src.Utils;

import java.util.Scanner;

public class MenuTerminal {

    //Muestra un menú al usuario para seleccionar el tipo de hilos.

    public static int mostrarMenuSeleccionHilos() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.printf("%n=========================%n");
                System.out.printf(" Seleccione el tipo de hilos (Su numero de opcion)%n");
                System.out.printf("=========================%n");
                System.out.printf("1. Hilos de Plataforma%n");
                System.out.printf("2. Hilos Virtuales%n");
                System.out.printf("-------------------------%n");
                System.out.printf("Ingrese su opción: ");

                int opcion = scanner.nextInt();

                // Validar entrada usando switch
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado: Hilos de Plataforma");
                        return 1;

                    case 2:
                        System.out.println("Has seleccionado: Hilos Virtuales");
                        return 2;

                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break; // Volver al menú
                }
            } catch (java.util.InputMismatchException e) {
                // Manejar entradas no numéricas
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }
}

