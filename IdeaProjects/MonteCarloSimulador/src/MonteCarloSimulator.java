/**
 * Proyecto: Simulador de Probabilidad (Metodo Monte Carlo)
 * Curso: MCA 1 2026-1 FCiencias
 * Tema: Programación funcional en Java - Paso de funciones como parámetros
 *
 * Autor: José Angel De León Olivares
 * Fecha: 5 Nov 2025
 *
 * Fuentes:
 * - GeeksforGeeks: Monte Carlo Method for Estimation of Pi
 *   https://www.geeksforgeeks.org/monte-carlo-method-for-estimation-of-value-of-pi/
 *   - Bro Code (YouTube): Java Lambda Expressions Tutorial
 */

import java.util.Random;

@FunctionalInterface
interface Experimento {
    boolean ejecutar();
}

public class MonteCarloSimulator {

    public static double estimarProbabilidad(Experimento experimento, int repeticiones) {
        int exitos = 0;
        for (int i = 0; i < repeticiones; i++) {
            if (experimento.ejecutar()) exitos++;
        }
        return (double) exitos / repeticiones;
    }

    public static void main(String[] args) {
        Random r = new Random();

        // 1️⃣ Experimento: lanzar un dado y obtener un número par
        Experimento dadoPar = () -> (r.nextInt(6) + 1) % 2 == 0;

        // 2️⃣ Experimento: punto dentro de un círculo (estimación de π)
        Experimento puntoEnCirculo = () -> {
            double x = r.nextDouble();
            double y = r.nextDouble();
            return x * x + y * y <= 1;
        };

        // Resultados
        double probabilidadPar = estimarProbabilidad(dadoPar, 100000);
        double estimacionPi = 4 * estimarProbabilidad(puntoEnCirculo, 1_000_000);

        System.out.printf("Probabilidad de número par (dado): %.2f%%%n", probabilidadPar * 100);
        System.out.printf("Estimación de π (Monte Carlo): %.6f%n", estimacionPi);

        System.out.println("\nSimulación completada exitosamente ✅");
    }
}

