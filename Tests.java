package ProyectoFinal;

import ProyectoFinal.src.Models.ArchivoCopiaTextoBuffered;

public class Tests {

    public void testArchivoCopiaTextoBufferedCopiaOrigen() {
        ArchivoCopiaTextoBuffered archivo = new ArchivoCopiaTextoBuffered();
        archivo.copiar("covid.csv");
    }

    public void testArchivoCopiaTextoBufferedCopiaOrigenDestino() {

    }

    public static void main(String[] args) {
        Tests tests = new Tests();
        tests.testArchivoCopiaTextoBufferedCopiaOrigen();
    }
}
