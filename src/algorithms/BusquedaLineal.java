package algorithms;

public class BusquedaLineal {

    // ----------------------------------------------------------------
    // VERSIÓN ITERATIVA
    // ----------------------------------------------------------------
    /**
     * Busca un valor en el arreglo recorriéndolo secuencialmente.
     *
     * @param arr arreglo de enteros
     * @param objetivo valor a buscar
     * @return índice del valor si existe, -1 si no se encuentra
     */
    public static int iterativo(int[] arr, int objetivo) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == objetivo) {
                return i; // índice encontrado
            }
        }
        return -1; // no encontrado
    }

    // ----------------------------------------------------------------
    // VERSIÓN RECURSIVA
    // ----------------------------------------------------------------
    /**
     * Busca un valor en el arreglo usando recursión.
     *
     * @param arr arreglo de enteros
     * @param objetivo valor a buscar
     * @param indice posición actual (inicia en 0)
     * @return índice del valor si existe, -1 si no se encuentra
     */
    public static int recursivo(int[] arr, int objetivo, int indice) {
        // --- CASO BASE ---
        if (indice >= arr.length) {
            return -1; // no encontrado
        }
        if (arr[indice] == objetivo) {
            return indice; // encontrado
        }

        // --- LLAMADA RECURSIVA ---
        return recursivo(arr, objetivo, indice + 1);
    }
}
