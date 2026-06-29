import java.util.Scanner;

public class Matrix {

    public static int[][] addition(int[][] matrixOne, int[][] matrixTwo, int r1, int c1) {

        int[][] result = new int[r1][c1];
        
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                result[i][j] = matrixOne[i][j] + matrixTwo[i][j];
            }
        }
        return result;
    }

    public static int[][] subtraction(int[][] matrixOne, int[][] matrixTwo, int r1, int c1) {

        int[][] result = new int[r1][c1];
        
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                result[i][j] = matrixOne[i][j] - matrixTwo[i][j];
            }
        }
        return result;
    }

    public static int[][] scalarMultiplication(int[][]matrixOne, int r1, int c1, int scalar) {

        int[][] result = new int[r1][c1];

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                result[i][j] = matrixOne[i][j] * scalar;
            }
        }
        return result;
    }

    public static int[][] matrixMultiplication(int[][] matrixOne, int[][] matrixTwo, int r1, int c1, int r2, int c2) {

        int[][] result = new int[r1][c2];

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < r2; k++) {
                    result[i][j] += matrixOne[i][k] * matrixTwo[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] transpose(int[][] matrixOne, int r1, int c1) {

        int[][] result = new int[c1][r1];

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                result[j][i] = matrixOne[i][j];
            }
        }
        return result;
    }

    public static int determinant(int[][] matrixOne) {

        int n = matrixOne.length;
        int determinant = 0;

        if (n == 1) {
            return matrixOne[0][0];
        }

        if (n == 2) {
            determinant = matrixOne[0][0]*matrixOne[1][1] - matrixOne[0][1]*matrixOne[1][0];
            return determinant;
        }

        for (int column = 0; column < n; column++) {
            int sign = (column % 2 == 0) ? 1 : -1;

            determinant += sign * matrixOne[0][column] * determinant(getMinor(matrixOne, 0, column));
        }

        return determinant;
    }

    private static int[][] getMinor(int[][] matrixOne, int row, int column) {
        int[][] minor = new int[matrixOne.length - 1][matrixOne.length - 1];

        int r = 0;
        for (int i = 0; i < matrixOne.length; i++) {
            if (i == row) continue;

            int c = 0;
            for (int j = 0; j < matrixOne.length; j++) {
                if (j == column) continue;

                minor[r][c] = matrixOne[i][j];
                c++;
            }
            r++;
        }
        return minor;
    }

    public static int[][] diagonalize(int[][] matrixOne, r1, c1) {
        int[][] temp = new int[r1][c1];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                temp[i][j] = matrixOne[i][j];
            }
        }

        
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of rows for the first matrix: ");
        int r1 = scanner.nextInt();

        System.out.println("Enter number of columns for the first matrix: ");
        int c1 = scanner.nextInt();

        System.out.println("Enter number of rows for the second matrix: ");
        int r2 = scanner.nextInt();

        System.out.println("Enter number of columns for the second matrix: ");
        int c2 = scanner.nextInt();

        int[][] matrixOne = new int[r1][c1];
        int[][] matrixTwo = new int[r2][c2];
        boolean equalSize = false;

        if (r1 == r2 && c1 == c2) {
            equalSize = true;
        }

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                System.out.println("Enter the first matrix's value at position: [" + i + "," + j + "]");
                int entry = scanner.nextInt();
                matrixOne[i][j] = entry;
            }
        }

        for (int i = 0; i < r2; i++) {
            for (int j = 0; j < c2; j++) {
                System.out.println("Enter the second matrix's value at position: [" + i + "," + j + "]");
                int entry = scanner.nextInt();
                matrixTwo[i][j] = entry;
            }
        }

        System.out.println("Menu: \n 1. Addition \n 2. Subtraction \n 3. Scalar Multiplication \n 4. Matrix Multiplication \n 5. Transpose \n 6. Determinant\n 7. Gaussian Elimination");
        int menuChoice = scanner.nextInt();

        if (menuChoice == 1 && equalSize) {
            int[][]additionResult = addition(matrixOne, matrixTwo, r1, c1);
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    System.out.println(additionResult[i][j]);
                }
            }
        }

        if (menuChoice == 2 && equalSize) {
            int[][]subtractionResult = subtraction(matrixOne, matrixTwo, r1, c1);
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    System.out.println(subtractionResult[i][j]);
                }
            }
        }

        if (menuChoice == 2 && equalSize) {
            int[][]subtractionResult = subtraction(matrixOne, matrixTwo, r1, c1);
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    System.out.println(subtractionResult[i][j]);
                }
            }
        }

        if (menuChoice == 3) {
            System.out.println("Enter scalar: ");
            int scalar = scanner.nextInt();
            int[][]scalarMultiplicationResult = scalarMultiplication(matrixOne, r1, c1, scalar);
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    System.out.println(scalarMultiplicationResult[i][j]);
                }
            }
        }

        if (menuChoice == 4) {
            if (c1 != r2) {
                System.out.println("Matrices cannot be multiplied.");
            }

            int[][]matrixMultiplicationResult = matrixMultiplication(matrixOne, matrixTwo, r1, c1, r2, c2);
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c2; j++) {
                    System.out.println(matrixMultiplicationResult[i][j]);
                }
            }
        }

        if (menuChoice == 5) {
            int[][]transposeResult = transpose(matrixOne, r1, c1);
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c2; j++) {
                    System.out.println(transposeResult[i][j]);
                }
            }
        }

        if (menuChoice == 6) {
            if (r1 == c1) {
                int determinant = determinant(matrixOne);
                System.out.println(determinant); 
            } else {
                System.out.println("The determinant can only be calculated for square matrices");
            }
        }

        if (menuChoice == 7) {
            int[][]diagonalized = diagonalize(matrixOne, r1, c1);
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c2; j++) {
                    System.out.println(diagonalized[i][j]);
                }
            }
        }


    }

}