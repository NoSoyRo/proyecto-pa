package ProyectoFinal;

import ProyectoFinal.src.Models.ArchivoCopiaTextoBuffered;

public class Tests {

    public void testArchivoCopiaTextoBufferedCopiaOrigen() {
        ArchivoCopiaTextoBuffered archivo = new ArchivoCopiaTextoBuffered();
        archivo.copiar("covid.csv");
    }

    public void testArchivoObtenDimesniones() {
        ArchivoCopiaTextoBuffered archivo = new ArchivoCopiaTextoBuffered();
        int[] arr = archivo.getDimensiones("covid.csv");
        String resView = Integer.toString(arr[0]) + ',' + Integer.toString(arr[1]);
        System.out.println(resView);
        // works correctly
    }

    public static void main(String[] args) {
        Tests tests = new Tests();
        System.out.println("\n Unit test 1: testArchivoCopiaTextoBufferedCopiaOrigen \n");
        tests.testArchivoCopiaTextoBufferedCopiaOrigen();
        System.out.println("\n Unit test 2: testArchivoObtenDimesniones \n");
        tests.testArchivoObtenDimesniones();
    }
}
