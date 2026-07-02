public final class Matrix {

    private static void validateDimensions(int[][] matrix) {

        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null.");
        }

        if (matrix.length == 0) {
            throw new IllegalArgumentException("Matrix cannot be empty.");
        }

        int cols = matrix[0].length;

        if (cols == 0) {
            throw new IllegalArgumentException("Matrix cannot have empty rows.");
        }

        for (int[] row : matrix) {
            if (row == null || row.length != cols) {
                throw  new IllegalArgumentException("Matrix must be rectangular.");
            }
        }

    }

    private static void validateSameDimensions(int[][] matrix1, int[][] matrix2) {

        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }
    }

    private static void validateMultiplicationDimensions(int[][] matrix1, int[][] matrix2) {

        if (matrix1[0].length != matrix2.length) {
            throw new IllegalArgumentException("The number of columns in the first matrix must equal the number of rows in the second matrix.");
        }
    }

    private static void validateSquareDimensions(int[][] matrix) {

        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
    }

    public static int[][] addition(int[][] matrix1, int[][] matrix2) {

        validateDimensions(matrix1);
        validateDimensions(matrix2);
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

        validateDimensions(matrix1);
        validateDimensions(matrix2);
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

        validateDimensions(matrix);

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

    public static int[][] scalarDivision(int[][]matrix, int scalar) {

        validateDimensions(matrix);

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] / scalar;
            }
        }
        return result;
    }

    public static int[][] matrixMultiplication(int[][] matrix1, int[][] matrix2) {

        validateDimensions(matrix1);
        validateDimensions(matrix2);
        validateMultiplicationDimensions(matrix1, matrix2);

        int[][] result = new int[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] transpose(int[][] matrix) {

        validateDimensions(matrix);

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static int determinant(int[][] matrix) {

        validateDimensions(matrix);
        validateSquareDimensions(matrix);

        int n = matrix.length;
        int determinant = 0;

        if (n == 1) {
            return matrix[0][0];
        }

        if (n == 2) {
            determinant = matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
            return determinant;
        }

        for (int column = 0; column < n; column++) {
            int sign = (column % 2 == 0) ? 1 : -1;

            determinant += sign * matrix[0][column] * determinant(getMinor(matrix, 0, column));
        }

        return determinant;
    }

    private static int[][] getMinor(int[][] matrix, int row, int column) {
        int[][] minor = new int[matrix.length - 1][matrix.length - 1];

        int r = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == row) continue;

            int c = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (j == column) continue;

                minor[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }
        return minor;
    }

    public static double[][] inverse(int[][] matrix) {

        validateDimensions(matrix);
        validateSquareDimensions(matrix);

        int n = matrix.length;

        // Check determinant
        if (determinant(matrix) == 0) {
            throw new IllegalArgumentException("Matrix is not invertible as the determinant is equal to 0.");
        }

        // Build augmented matrix [A | I]
        double[][] augmentedMatrix = new double[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
            }
            augmentedMatrix[i][i + n] = 1;
        }

        // Gaussian elimination
        for (int pivot = 0; pivot < n; pivot++) {

            double pivotVal = augmentedMatrix[pivot][pivot];

            if (Math.abs(pivotVal) < 1e-9) {
                throw new IllegalArgumentException("Matrix is a singular or poorly conditioned.");
            }

            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[pivot][j] /= pivotVal;
            }

            for (int i = 0; i < n; i++) {
                if (i == pivot) continue;

                double factor = augmentedMatrix[i][pivot];

                for (int j = 0; j < 2 * n; j++) {
                    augmentedMatrix[i][j] -= factor * augmentedMatrix[pivot][j];

                    if (Math.abs(augmentedMatrix[i][j]) < 1e-9) {
                        augmentedMatrix[i][j] = 0;
                    }
                }
            }
        }

        // Extract inverse matrix
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], n, result[i], 0, n);
        }

        return result;
        
    }
    
    public static double[][] reducedRowEchelonForm(int[][] matrix) {

        validateDimensions(matrix);

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


    


}