package ProyectoFinal.src.Utils;

import java.util.Scanner;

public class MenuTerminal {

    public static int mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.printf("%n=========================%n");
                System.out.printf("       MENÚ PRINCIPAL       %n");
                System.out.printf("=========================%n");
                System.out.printf("1. Ejecutar con Hilos de Plataforma%n");
                System.out.printf("2. Ejecutar con Hilos Virtuales%n");
                System.out.printf("3. Procesar de Forma Secuencial%n");
                System.out.printf("4. Salir%n");
                System.out.printf("-------------------------%n");
                System.out.printf("Ingrese su opción: ");

                int opcion = scanner.nextInt();

                if (opcion >= 1 && opcion <= 4) {
                    return opcion; // Devolver la opción válida
                } else {
                    System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }
}

