@echo off
REM Navigate to the project directory (if needed, adjust the path)
cd /d %~dp0

REM Compile the Java files
echo compilando archivos
javac -d out -sourcepath src src\Models\Archivo.java src\Models\ArchivoCopiaTextoBuffered.java src\Fabricas\FabricaChunks.java src\Interfaces\IArchivo.java Tests.java

REM Check if compilation was successful
if %errorlevel% equ 0 (
    echo compilacion correcta, ejecutando archivos de prueba
    REM Run the Tests class
    java -cp out ProyectoFinal.Tests
) else (
    echo la compilacion fallo
)
pause
