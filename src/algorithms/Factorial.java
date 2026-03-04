package algorithms;


/**
 * ============================================================
 * A3 - FACTORIAL
 * ============================================================
 * Calcula el factorial de n: n! = n × (n-1) × (n-2) × ... × 1
 *
 * Ejemplo:
 *   5! = 5 × 4 × 3 × 2 × 1 = 120
 *
 * Complejidad temporal:
 *   - Iterativo:  O(n)   — un ciclo lineal
 *   - Recursivo:  O(n)   — una llamada por nivel
 *
 * Complejidad espacial:
 *   - Iterativo:  O(1)   — solo una variable acumuladora
 *   - Recursivo:  O(n)   — profundidad máxima del call stack
 * ============================================================
 */
public class Factorial {

    // ----------------------------------------------------------------
    // VERSIÓN ITERATIVA
    // ----------------------------------------------------------------
    /**
     * Calcula el factorial de n usando un ciclo.
     *
     * @param n número entero (n ≥ 0)
     * @return n!
     */
    public static long iterativo(int n) {
        if (n < 0) throw new IllegalArgumentException("n no puede ser negativo");

        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // ----------------------------------------------------------------
    // VERSIÓN RECURSIVA
    // ----------------------------------------------------------------
    /**
     * Calcula el factorial de n usando recursión directa.
     *
     * Fórmula recursiva:
     *   fact(0) = 1              ← caso base
     *   fact(n) = n × fact(n-1)  ← llamada recursiva
     *
     * @param n número entero (n ≥ 0)
     * @return n!
     */
    public static long recursivo(int n) {
        if (n < 0) throw new IllegalArgumentException("n no puede ser negativo");

        // --- CASO BASE ---
        if (n == 0) return 1;

        // --- LLAMADA RECURSIVA ---
        return n * recursivo(n - 1);
    }
}

