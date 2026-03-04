package benchmark;

import algorithms.Fibonacci;
import algorithms.Factorial;
import algorithms.Burbuja;
import algorithms.BusquedaLineal;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


/**
 * ============================================================
 * PUNTO DE ENTRADA — BENCHMARK FIBONACCI
 * ============================================================
 * Ejecuta ambas versiones de Fibonacci para distintos valores de n,
 * mide sus tiempos y exporta los resultados a un archivo CSV.
 *
 * CÓMO COMPILAR (desde la carpeta raíz del proyecto):
 *   javac -d out src/algorithms/Fibonacci.java src/benchmark/Medidor.java src/benchmark/Main.java
 *
 * CÓMO EJECUTAR:
 *   java -cp out benchmark.Main
 * ============================================================
 */
public class Main {

    // ----------------------------------------------------------------
    // TAMAÑOS DE PRUEBA
    // ----------------------------------------------------------------
    /**
     * Valores de n para los que se ejecutará el benchmark.
     *
     * ITERATIVO: puede manejar n grandes (hasta ~92 con long)
     * RECURSIVO: limitado a n ≤ 30 por la complejidad O(2^n)
     *   fib(30) → ~2.7 millones de llamadas
     *   fib(40) → ~2.7 mil millones de llamadas (tarda minutos)
     */
    private static final int[] TAMANOS = {30, 15, 25, 20, 5, 10};
    


    /** Ruta del archivo de resultados */
    private static final String CSV_PATH = "resultados/tiempos.csv";

    // ----------------------------------------------------------------
    // MAIN
    // ----------------------------------------------------------------
    public static void main(String[] args) {
        imprimirBanner();

        StringBuilder csv = new StringBuilder();
        csv.append("Algoritmo,Version,n,Resultado,Tiempo_ms\n");

        // ---- FIBONACCI ITERATIVO ----
        System.out.println("\n  FIBONACCI ITERATIVO  [O(n)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS) {
            final int fn = n;

            // Calcular resultado una vez (solo para mostrarlo)
            long resultado = Fibonacci.iterativo(fn);

            // Medir solo el algoritmo puro (sin I/O ni inicialización)
            double tiempoMs = Medidor.medir(() -> Fibonacci.iterativo(fn));

            Medidor.imprimirFila("Fibonacci", "Iterativo", n, tiempoMs);
            csv.append(String.format("Fibonacci,Iterativo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

        // ---- FIBONACCI RECURSIVO ----
        System.out.println("\n  FIBONACCI RECURSIVO  [O(2^n)]");
        Medidor.imprimirEncabezado();

        for (int n : TAMANOS) {
            final int fn = n;

            long resultado = Fibonacci.recursivo(fn);
            double tiempoMs = Medidor.medir(() -> Fibonacci.recursivo(fn));

            Medidor.imprimirFila("Fibonacci", "Recursivo", n, tiempoMs);
            csv.append(String.format("Fibonacci,Recursivo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

                // ---- FACTORIAL ITERATIVO ----
        System.out.println("\n  FACTORIAL ITERATIVO  [O(n)]");
        Medidor.imprimirEncabezado();
        for (int n : TAMANOS) {
            long resultado = Factorial.iterativo(n);
            double tiempoMs = Medidor.medir(() -> Factorial.iterativo(n));
            Medidor.imprimirFila("Factorial", "Iterativo", n, tiempoMs);
            csv.append(String.format("Factorial,Iterativo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

        // ---- FACTORIAL RECURSIVO ----

        int[] arregloDesordenado = {10000, 100, 500, 5, 1000, 5000};

        System.out.println("\n  FACTORIAL RECURSIVO  [O(n)]");
        Medidor.imprimirEncabezado();
        for (int n : TAMANOS) {
            long resultado = Factorial.recursivo(n);
            double tiempoMs = Medidor.medir(() -> Factorial.recursivo(n));
            Medidor.imprimirFila("Factorial", "Recursivo", n, tiempoMs);
            csv.append(String.format("Factorial,Recursivo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

        // ---- BÚSQUEDA LINEAL ITERATIVO ----
        System.out.println("\n  BÚSQUEDA LINEAL ITERATIVO");
        Medidor.imprimirEncabezado();
        for (int objetivo : arregloDesordenado) {
            int resultado = BusquedaLineal.iterativo(arregloDesordenado, objetivo);
            double tiempoMs = Medidor.medir(() -> BusquedaLineal.iterativo(arregloDesordenado, objetivo));
            Medidor.imprimirFila("BusquedaLineal", "Iterativo", objetivo, tiempoMs);
            csv.append(String.format("BusquedaLineal,Iterativo,%d,%d,%.6f%n", objetivo, resultado, tiempoMs));
        }

        // ---- BÚSQUEDA LINEAL RECURSIVA ----
        System.out.println("\n  BÚSQUEDA LINEAL RECURSIVA");
        Medidor.imprimirEncabezado();
        for (int objetivo : arregloDesordenado) {
            int resultado = BusquedaLineal.recursivo(arregloDesordenado, objetivo, 0);
            double tiempoMs = Medidor.medir(() -> BusquedaLineal.recursivo(arregloDesordenado, objetivo, 0));
            Medidor.imprimirFila("BusquedaLineal", "Recursivo", objetivo, tiempoMs);
            csv.append(String.format("BusquedaLineal,Recursivo,%d,%d,%.6f%n", objetivo, resultado, tiempoMs));
        }


        // ---- BURBUJA ITERATIVO ----

            // ---- BURBUJA ITERATIVO ----
        System.out.println("\n  BURBUJA ITERATIVO  [O(n^2)]");
        Medidor.imprimirEncabezado();
        for (int objetivo : arregloDesordenado) {
            double tiempoIter = Medidor.medir(() -> Burbuja.iterativo(arregloDesordenado));
            Medidor.imprimirFila("Burbuja", "Iterativo", objetivo, tiempoIter);
            csv.append(String.format("Burbuja,Iterativo,%d,%.6f%n",
                    objetivo,
                    tiempoIter));
        }

        // ---- BURBUJA RECURSIVO ----
        System.out.println("\n  BURBUJA RECURSIVO  [O(n^2)]");
        Medidor.imprimirEncabezado();
        for (int objetivo : arregloDesordenado) {
            double tiempoRec = Medidor.medir(() -> Burbuja.recursivo(arregloDesordenado, arregloDesordenado.length));
            Medidor.imprimirFila("Burbuja", "Recursivo", objetivo, tiempoRec);
            csv.append(String.format("Burbuja,Recursivo,%d,%.6f%n",
                    objetivo,
                    tiempoRec));
        }


        //PROMEDIO 
        System.out.println("\n  PROMEDIO DE TIEMPOS (5 intentos)");
        Medidor.imprimirEncabezado2();
                // Número de intentos completos
        final int INTENTOS = 5;

        // ===============================
        // BURBUJA ITERATIVO
        // ===============================
        double sumaBurbujaIter = 0;
        for (int i = 0; i < INTENTOS; i++) {
            sumaBurbujaIter += Medidor.medir(() -> Burbuja.iterativo(arregloDesordenado));
        }
        double promedioBurbujaIter = sumaBurbujaIter / INTENTOS;
        System.out.printf("Burbuja Iterativo (promedio %d intentos): %.6f ms%n", INTENTOS, promedioBurbujaIter);

        // BURBUJA RECURSIVO
        double sumaBurbujaRec = 0;
        for (int i = 0; i < INTENTOS; i++) {
            sumaBurbujaRec += Medidor.medir(() -> Burbuja.recursivo(arregloDesordenado, arregloDesordenado.length));
        }
        double promedioBurbujaRec = sumaBurbujaRec / INTENTOS;
        System.out.printf("Burbuja Recursivo (promedio %d intentos): %.6f ms%n", INTENTOS, promedioBurbujaRec);

        // ===============================
        // FACTORIAL ITERATIVO
        // ===============================
        double sumaFactIter = 0;
        for (int i = 0; i < INTENTOS; i++) {
            sumaFactIter += Medidor.medir(() -> Factorial.iterativo(20)); // ejemplo con n=20
        }
        double promedioFactIter = sumaFactIter / INTENTOS;
        System.out.printf("Factorial Iterativo (promedio %d intentos): %.6f ms%n", INTENTOS, promedioFactIter);

        // FACTORIAL RECURSIVO
        double sumaFactRec = 0;
        for (int i = 0; i < INTENTOS; i++) {
            sumaFactRec += Medidor.medir(() -> Factorial.recursivo(20));
        }
        double promedioFactRec = sumaFactRec / INTENTOS;
        System.out.printf("Factorial Recursivo (promedio %d intentos): %.6f ms%n", INTENTOS, promedioFactRec);

        // ===============================
        // FIBONACCI ITERATIVO
        // ===============================
        double sumaFibIter = 0;
        for (int i = 0; i < INTENTOS; i++) {
            sumaFibIter += Medidor.medir(() -> Fibonacci.iterativo(30)); // ejemplo con n=30
        }
        double promedioFibIter = sumaFibIter / INTENTOS;
        System.out.printf("Fibonacci Iterativo (promedio %d intentos): %.6f ms%n", INTENTOS, promedioFibIter);

        // FIBONACCI RECURSIVO
        double sumaFibRec = 0;
        for (int i = 0; i < INTENTOS; i++) {
            sumaFibRec += Medidor.medir(() -> Fibonacci.recursivo(20)); // n más pequeño por O(2^n)
        }
        double promedioFibRec = sumaFibRec / INTENTOS;
        System.out.printf("Fibonacci Recursivo (promedio %d intentos): %.6f ms%n", INTENTOS, promedioFibRec);

        // ===============================
        // BÚSQUEDA LINEAL ITERATIVO
        // ===============================
        double sumaBusqIter = 0;
        for (int i = 0; i < INTENTOS; i++) {
            sumaBusqIter += Medidor.medir(() -> BusquedaLineal.iterativo(arregloDesordenado, 1000)); // buscar 1000
        }
        double promedioBusqIter = sumaBusqIter / INTENTOS;
        System.out.printf("Busqueda Lineal Iterativo (promedio %d intentos): %.6f ms%n", INTENTOS, promedioBusqIter);

        // BÚSQUEDA LINEAL RECURSIVO
        double sumaBusqRec = 0;
        for (int i = 0; i < INTENTOS; i++) {
            sumaBusqRec += Medidor.medir(() -> BusquedaLineal.recursivo(arregloDesordenado, 1000, 0));
        }
        double promedioBusqRec = sumaBusqRec / INTENTOS;
        System.out.printf("Busqueda Lineal Recursivo (promedio %d intentos): %.6f ms%n", INTENTOS, promedioBusqRec);

        


        // ---- ANÁLISIS DE DIFERENCIA ----
        System.out.println("\n  COMPARACIÓN ITERATIVO vs RECURSIVO (FIBONACCI)");
        System.out.println("-".repeat(60));
        System.out.printf("%-8s | %-14s | %-14s | %s%n",
                "n", "Iterativo (ms)", "Recursivo (ms)", "Recursivo / Iterativo");
        System.out.println("-".repeat(60));

        for (int n : TAMANOS) {
            final int fn = n;
            double tIter = Medidor.medir(() -> Fibonacci.iterativo(fn));
            double tRec  = Medidor.medir(() -> Fibonacci.recursivo(fn));
            double factor = (tIter > 0) ? tRec / tIter : 0;

            System.out.printf("n=%-6d | %-14.6f | %-14.6f | %.1fx más lento%n",
                    n, tIter, tRec, factor);
        }

        // ---- EXPORTAR CSV ----
        exportarCSV(csv.toString());

        System.out.println("\n============================================================");
        System.out.println("  Resultados exportados → " + CSV_PATH);
        System.out.println("============================================================");
    }

    // ----------------------------------------------------------------
    // AUXILIARES
    // ----------------------------------------------------------------
    private static void imprimirBanner() {
        System.out.println("============================================================");
        System.out.println("  ESTRUCTURA DE DATOS — BENCHMARK FIBONACCI");
        System.out.println("  Universidad Da Vinci de Guatemala");
        System.out.println("  Ing. Brandon Chitay");
        System.out.println("============================================================");
    }

    private static void exportarCSV(String contenido) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_PATH))) {
            pw.print(contenido);
            System.out.println("\n  ✓ CSV generado exitosamente en: " + CSV_PATH);
        } catch (IOException e) {
            System.err.println("  ✗ Error al escribir CSV: " + e.getMessage());
        }
    }
}
