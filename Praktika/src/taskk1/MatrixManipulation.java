package taskk1;

import java.util.Random;
import java.util.Scanner;

public class MatrixManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();

        // Создание и заполнение матрицы
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(2 * n + 1) - n; // Значения от -n до n
            }
        }

        // Вывод исходной матрицы
        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        // Поиск максимального элемента
        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > maxElement) {
                    maxElement = matrix[i][j];
                }
            }
        }

        System.out.println("Максимальный элемент: " + maxElement);

        // Определение строк и столбцов для удаления
        boolean[] rowsToRemove = new boolean[n];
        boolean[] colsToRemove = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == maxElement) {
                    rowsToRemove[i] = true;
                    colsToRemove[j] = true;
                }
            }
        }

        // Формирование новой матрицы
        int newRowCount = 0;
        for (int i = 0; i < n; i++) {
            if (!rowsToRemove[i]) {
                newRowCount++;
            }
        }

        int newColCount = 0;
        for (int j = 0; j < n; j++) {
            if (!colsToRemove[j]) {
                newColCount++;
            }
        }

        int[][] newMatrix = new int[newRowCount][newColCount];
        int newRow = 0;
        for (int i = 0; i < n; i++) {
            if (!rowsToRemove[i]) {
                int newCol = 0;
                for (int j = 0; j < n; j++) {
                    if (!colsToRemove[j]) {
                        newMatrix[newRow][newCol] = matrix[i][j];
                        newCol++;
                    }
                }
                newRow++;
            }
        }

        // Вывод новой матрицы
        System.out.println("Новая матрица:");
        printMatrix(newMatrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
