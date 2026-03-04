package benchmark;

import algorithms.Fibonacci;
import algorithms.Factorial;
import algorithms.Burbuja;
import algorithms.BusquedaLineal;
import algorithms.Burbuja;


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
    private static final int[] TAMANOS = {24, 14, 9, 20, 5, 30};
    


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
        System.out.println("\n  FACTORIAL RECURSIVO  [O(n)]");
        Medidor.imprimirEncabezado();
        for (int n : TAMANOS) {
            long resultado = Factorial.recursivo(n);
            double tiempoMs = Medidor.medir(() -> Factorial.recursivo(n));
            Medidor.imprimirFila("Factorial", "Recursivo", n, tiempoMs);
            csv.append(String.format("Factorial,Recursivo,%d,%d,%.6f%n", n, resultado, tiempoMs));
        }

        // ---- BUSQUEDA LINEAL ITERATIVO
        System.out.println("\n  BUSQUEDA LINEAL ITERATIVO");
        Medidor.imprimirEncabezado();
        for (int objetivo : TAMANOS) {
            int resultado = BusquedaLineal.iterativo(TAMANOS, objetivo);
            double tiempoMs = Medidor.medir(() -> BusquedaLineal.iterativo(TAMANOS, objetivo));
            Medidor.imprimirFila("BusquedaLineal", "Iterativo", objetivo, tiempoMs);
            csv.append(String.format("BusquedaLineal,Iterativo,%d,%d,%.6f%n", objetivo, resultado, tiempoMs));
        }

        // ---- BÚSQUEDA LINEAL RECURSIVA ----
        
        System.out.println("\n  BÚSQUEDA LINEAL RECURSIVA");
        Medidor.imprimirEncabezado();
        for (int objetivo : TAMANOS) {
            int resultado = BusquedaLineal.recursivo(TAMANOS, objetivo,0);
            double tiempoMs = Medidor.medir(() -> BusquedaLineal.recursivo(TAMANOS, objetivo,0));
            Medidor.imprimirFila("BusquedaLineal", "Recursivo", objetivo, tiempoMs);
            csv.append(String.format("BusquedaLineal,Recursivo,%d,%d,%.6f%n", objetivo, resultado, tiempoMs));
        }

        // ---- BURBUJA ITERATIVO ----

        int[] arregloDesordenado = {10000, 100, 500, 5, 1000, 5000};

        // ---- BURBUJA ITERATIVO ----
        System.out.println("\n  BURBUJA ITERATIVO  [O(n^2)]");
        Medidor.imprimirEncabezado();
        int[] ordenadoIter = Burbuja.iterativo(arregloDesordenado);
        double tiempoIter = Medidor.medir(() -> Burbuja.iterativo(arregloDesordenado));
        Medidor.imprimirFila("Burbuja", "Iterativo", arregloDesordenado.length, tiempoIter);
        System.out.println("Arreglo ordenado (Iterativo): " + Arrays.toString(ordenadoIter));
        csv.append(String.format("Burbuja,Iterativo,%d,%s,%.6f%n",
                arregloDesordenado.length,
                Arrays.toString(ordenadoIter),
                tiempoIter));

        // ---- BURBUJA RECURSIVO ----
        System.out.println("\n  BURBUJA RECURSIVO  [O(n^2)]");
        Medidor.imprimirEncabezado();
        int[] ordenadoRec = Burbuja.recursivo(arregloDesordenado, arregloDesordenado.length);
        double tiempoRec = Medidor.medir(() -> Burbuja.recursivo(arregloDesordenado, arregloDesordenado.length));
        Medidor.imprimirFila("Burbuja", "Recursivo", arregloDesordenado.length, tiempoRec);
        System.out.println("Arreglo ordenado (Recursivo): " + Arrays.toString(ordenadoRec));
        csv.append(String.format("Burbuja,Recursivo,%d,%s,%.6f%n",
                arregloDesordenado.length,
                Arrays.toString(ordenadoRec),
                tiempoRec));


        // ---- ANÁLISIS DE DIFERENCIA ----
        System.out.println("\n  COMPARACIÓN ITERATIVO vs RECURSIVO");
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
