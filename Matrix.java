public class Matrix {

    private static void validateSameDimensions(int[][] matrix1, int[][] matrix2) {

        if (matrix1 == null || matrix2 == null) {
            throw new IllegalArgumentException("Matrices cannot be null.");
        }

        if (matrix1.length == 0 || matrix2.length == 0) {
            throw new IllegalArgumentException("Matrices cannot be empty.");
        }

        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }
    }

    public static int[][] addition(int[][] matrix1, int[][] matrix2) {

        validateSameDimensions(matrix1, matrix2);

        int rows = matrix1.length;
        int cols = matrix1[0].length;

        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public static int[][] subtraction(int[][] matrix1, int[][] matrix2) {

        validateSameDimensions(matrix1, matrix2);

        int rows = matrix1.length;
        int cols = matrix1[0].length;

        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    public static int[][] scalarMultiplication(int[][]matrix, int scalar) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return result;
    }

    public static int[][] matrixMultiplication(int[][] matrix1, int[][] matrix2) {

        int r1 = matrix1.length;
        int r2 = matrix2.length;

        int c1 = matrix1[0].length;
        int c2 = matrix2[0].length;

        if (r2 != c1) {
            throw new IllegalArgumentException("Matrices cannot be multiplied.");
        }

        int[][] result = new int[r1][c2];

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < r2; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
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

    public static double[][] reducedRowEchelonForm(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Copy matrix into a double matrix
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j];
            }
        }

        int pivotRow = 0;

        for (int pivotCol = 0; pivotCol < cols && pivotRow < rows; pivotCol++) {

            // Find row with largest pivot
            int maxRow = pivotRow;
            for (int i = pivotRow + 1; i < rows; i++) {
                if (Math.abs(result[i][pivotCol]) > Math.abs(result[maxRow][pivotCol])) {
                    maxRow = i;
                }
            }

            // If entire column = 0, move to next columns
            if (Math.abs(result[maxRow][pivotCol]) < 1e-9) {
                continue;
            }

            // Swap rows
            if (maxRow != pivotRow) {
                double[] temp = result[pivotRow];
                result[pivotRow] = result[maxRow];
                result[maxRow] = temp;
            }

            // Normalize pivot row
            double pivot = result[pivotRow][pivotCol];
            for (int j = 0; j < cols; j++) {
                result[pivotRow][j] /= pivot;
            }

            // Eliminate every other row
            for (int i = 0; i < rows; i++) {

                if (i == pivotRow) {
                    continue;
                }

                double factor = result[i][pivotCol];

                for (int j = 0; j < cols; j++) {
                    result[i][j] -= factor * result[pivotRow][j];

                    // Remove floating-point errors
                    if (Math.abs(result[i][j]) < 1e-9) {
                        result[i][j] = 0;
                    }
                }
            }

            pivotRow++;
        }

        return result;
    }

    /* 
    public static double[][] inverse(int[][] matrix) {

        int n = matrix.length;

        
    }
    */


}