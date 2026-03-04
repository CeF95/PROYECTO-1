package algorithms;

public class Burbuja {

    // ----------------------------------------------------------------
    // VERSIÓN ITERATIVA
    // ----------------------------------------------------------------
    public static int[] iterativo(int[] arr) {
        int[] copia = arr.clone(); // para no modificar el original
        int n = copia.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (copia[j] > copia[j + 1]) {
                    int temp = copia[j];
                    copia[j] = copia[j + 1];
                    copia[j + 1] = temp;
                }
            }
        }
        return copia;
    }

    // ----------------------------------------------------------------
    // VERSIÓN RECURSIVA
    // ----------------------------------------------------------------
    public static int[] recursivo(int[] arr, int n) {
        int[] copia = arr.clone(); // para no modificar el original
        recursivoHelper(copia, n);
        return copia;
    }

    private static void recursivoHelper(int[] arr, int n) {
        // Caso base
        if (n == 1) return;

        // Una pasada de burbuja
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        // Llamada recursiva para el resto
        recursivoHelper(arr, n - 1);
    }
}
