public final class Matrix {

    private static void validateDimensions(double[][] matrix) {

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

        for (double[] row : matrix) {
            if (row == null || row.length != cols) {
                throw  new IllegalArgumentException("Matrix must be rectangular.");
            }
        }

    }

    private static void validateSameDimensions(double[][] matrix1, double[][] matrix2) {

        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }
    }

    private static void validateMultiplicationDimensions(double[][] matrix1, double[][] matrix2) {

        if (matrix1[0].length != matrix2.length) {
            throw new IllegalArgumentException("The number of columns in the first matrix must equal the number of rows in the second matrix.");
        }
    }

    private static void validateSquareDimensions(double[][] matrix) {

        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
    }

    public static Calculations addition(double[][] matrix1, double[][] matrix2) {

        validateDimensions(matrix1);
        validateDimensions(matrix2);
        validateSameDimensions(matrix1, matrix2);

        int rows = matrix1.length;
        int cols = matrix1[0].length;

        double[][] result = new double[rows][cols];

        StringBuilder sb = new StringBuilder("OPERATION: Matrix Addition\n");
        sb.append("Adding elements entry-by-entry:\n\n");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];

                sb.append(String.format(
                    "Cell [%d, %d]: %.2f + %.2f = %.2f\n", 
                    (i + 1), 
                    (j + 1), 
                    matrix1[i][j], 
                    matrix2[i][j],
                    result[i][j]
                ));
            }
        }
        return new Calculations(result, sb.toString());
    }

    public static Calculations subtraction(double[][] matrix1, double[][] matrix2) {

        validateDimensions(matrix1);
        validateDimensions(matrix2);
        validateSameDimensions(matrix1, matrix2);

        int rows = matrix1.length;
        int cols = matrix1[0].length;

        double[][] result = new double[rows][cols];

        StringBuilder sb = new StringBuilder("OPERATION: Matrix Subtraction\n");
        sb.append("Subtracting elements entry-by-entry:\n\n");
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];

                sb.append(String.format(
                    "Cell [%d, %d]: %.2f - %.2f = %.2f\n", 
                    (i + 1), 
                    (j + 1), 
                    matrix1[i][j], 
                    matrix2[i][j],
                    result[i][j]
                ));
            }
        }
        return new Calculations(result, sb.toString());
    }

    public static Calculations scalarMultiplication(double[][]matrix, double scalar) {

        validateDimensions(matrix);

        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] result = new double[rows][cols];

        StringBuilder sb = new StringBuilder("OPERATION: Scalar Multiplication\n");
        sb.append(String.format("Multiplying every element by a scalar factor (%.2f):\n\n", scalar));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] * scalar;

                sb.append(String.format(
                    "Cell [%d, %d]: %.2f * %.2f = %.2f\n", 
                    (i + 1), 
                    (j + 1), 
                    matrix[i][j], 
                    scalar,
                    result[i][j]
                ));
            }
        }
        return new Calculations(result, sb.toString());
    }

    public static Calculations scalarDivision(double[][]matrix, double scalar) {

        validateDimensions(matrix);

        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] result = new double[rows][cols];

        StringBuilder sb = new StringBuilder("OPERATION: Scalar Division\n");
        sb.append(String.format("Dividing every element by a scalar factor (%.2f):\n\n", scalar));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] / scalar;

                sb.append(String.format(
                    "Cell [%d, %d]: %.2f / %.2f = %.2f\n", 
                    (i + 1), 
                    (j + 1), 
                    matrix[i][j], 
                    scalar,
                    result[i][j]
                ));
            }
        }
        return new Calculations(result, sb.toString());
    }

    public static Calculations matrixMultiplication(double[][] matrix1, double[][] matrix2) {

        validateDimensions(matrix1);
        validateDimensions(matrix2);
        validateMultiplicationDimensions(matrix1, matrix2);

        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;

        double[][] result = new double[rows1][cols2];

        StringBuilder sb = new StringBuilder("OPERATION: Matrix Multiplication (Dot Product)\n");
        sb.append(String.format("Multiplying rows of Matrix 1 (%dx%d) by columns of Matrix 2 (%dx%d):\n\n", rows1, cols1, cols1, cols2));

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                sb.append(String.format("Cell [%d, %d] Calculation:\n Row %d of matrix 1 x column %d of matrix 2\n Formula: ",
                    (i + 1), (j + 1), (i + 1), (j + 1)
                ));

                double sum = 0;
                StringBuilder mathExpression = new StringBuilder();

                for (int k = 0; k < cols1; k++) {
                    double term = matrix1[i][k] * matrix2[k][j];
                    sum += term;

                    mathExpression.append(String.format("(%.2f * %.2f)", matrix1[i][k], matrix2[k][j]));

                    if (k < cols1 - 1) {
                        mathExpression.append(" + ");
                    }
                }
                result[i][j] = sum;

                sb.append(mathExpression.toString());
                sb.append(String.format(" = %.2f\n\n", sum)) ;
            }
        }
        return new Calculations(result, sb.toString());
    }

    public static Calculations transpose(double[][] matrix) {

        validateDimensions(matrix);

        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] result = new double[cols][rows];

        StringBuilder sb = new StringBuilder("OPERATION: Matrix Transpose\n");
        sb.append(String.format("Swapping rows and columns (%dx%d -> %dx%d):\n\n", rows, cols, cols, rows));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];

                sb.append(String.format(
                    "Element at [%d, %d] (%.2f) moves to [%d, %d]\n", 
                    (i + 1), 
                    (j + 1), 
                    matrix[i][j], 
                    (j + 1),
                    (i + 1)
                ));
            }
            sb.append("\n");
        }
        return new Calculations(result, sb.toString());
    }

    public static Calculations determinant(double[][] matrix) {

        validateDimensions(matrix);
        validateSquareDimensions(matrix);

        StringBuilder sb = new StringBuilder("OPERATION: Determinant (Cofactor Expansion)\n");
        int n = matrix.length;
        sb.append(String.format("Calculating the determinant of a %dx%d matrix.\n\n", n, n));

        int detResult = calculateDeterminantRecursive(matrix, sb, "");
        
        double[][] resultMatrix = new double[][] { { (double) detResult } };

        return new Calculations(resultMatrix, sb.toString());
    }

    private static int calculateDeterminantRecursive(double[][] matrix, StringBuilder sb, String indent) {
        int n = matrix.length;

        // Base case (1x1 matrix)
        if (n == 1) {
            return (int) matrix[0][0];
        }

        // Base case (2x2 matrix)
        if (n == 2) {
            int determinant2x2 = (int) (matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0]);
            sb.append(indent).append(String.format("Found 2x2 minor determinant: (%.1f * %.1f) - (%.1f * %.1f) = %d\n",
                matrix[0][0], matrix[1][1], matrix[0][1], matrix[1][0], determinant2x2));
            return determinant2x2;
        }

        int determinant = 0;
        sb.append(indent).append(String.format("Expanding along row 1 for a %dx%d matrix:\n", n, n));

        for (int column = 0; column < n; column++) {
            int sign = (column % 2 == 0) ? 1 : -1;
            double element = matrix[0][column];
            double[][] minor = getMinor(matrix, 0, column);

            sb.append(indent).append(String.format(" -> Term %d: Sign(%d) * Element(%1.f) * det(Minor at [1, %d])\n",
               (column + 1), sign, element, (column + 1) 
            ));

            int minorDet = calculateDeterminantRecursive(minor, sb, indent + "    ");

            int termValue = (int) (sign * element * minorDet);
            determinant += termValue;

            sb.append(indent).append(String.format("   Term %d Value = %d * %.1f * %d = %d\n",
                (column + 1), sign, element, minorDet, termValue
            ));
        }

        sb.append(indent).append(String.format("Combined row 1 expansion result for this level = %d\n\n", determinant));
        return determinant;
    }

    private static double[][] getMinor(double[][] matrix, int row, int column) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];

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

    public static Calculations inverse(double[][] matrix) {

        validateDimensions(matrix);
        validateSquareDimensions(matrix);

        int n = matrix.length;

        double determinant = determinant(matrix).matrix[0][0];

        StringBuilder sb = new StringBuilder("OPERATION: Multiplicative Inverse Matrix (Gauss-Jordan Elimination)\n");
        sb.append(String.format("1. Verified Determinant: det(A) = %.4f\n", determinant));

        if (Math.abs(determinant) < 1e-9) {
            throw new IllegalArgumentException("Matrix is not invertible as the determinant is equal to 0."); 
        }

        sb.append("2. Setup Augmented Matrix [A | I] by appending the Identity Matrix to the right:\n\n");

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

            sb.append(String.format("--- Processing Column %d (Pivot Element = %.4f) ---\n", (pivot + 1), pivotVal));
            
            // Normalize pivot row
            sb.append(String.format("  * Normalize Row %d: R%d -> R%d / %.4f\n", (pivot + 1), (pivot + 1), (pivot + 1), pivotVal));
            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[pivot][j] /= pivotVal;
            }

            // Eliminate other rows
            for (int i = 0; i < n; i++) {
                if (i == pivot) continue;

                double factor = augmentedMatrix[i][pivot];
                if (Math.abs(factor) < 1e-9) continue; 

                sb.append(String.format("  * Eliminate Row %d entry: R%d -> R%d - (%.4f * R%d)\n",
                    (i + 1), (i + 1), (i + 1), factor, (pivot + 1)
                ));

                for (int j = 0; j < 2 * n; j++) {
                    augmentedMatrix[i][j] -= factor * augmentedMatrix[pivot][j];

                    // Remove floating point errors
                    if (Math.abs(augmentedMatrix[i][j]) < 1e-9) {
                        augmentedMatrix[i][j] = 0;
                    }
                }
            }
            sb.append("\n");
        }

        // Extract inverse matrix from the right side
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], n, result[i], 0, n);
        }

        sb.append("3. Extract isolated right-hand section [A^-1] as the final solution.\n");
        return new Calculations(result, sb.toString());        
    }
    
    public static Calculations reducedRowEchelonForm(double[][] matrix) {

        validateDimensions(matrix);

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Copy matrix into a double matrix
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, result[i], 0, cols);
        }

        StringBuilder sb = new StringBuilder("OPERATION: Reduced Row Echelon Form (RREF)\n");
        sb.append(String.format("Performing Gaussian Elimination on a %dx%d matrix:\n\n", rows, cols));

        int pivotRow = 0;

        for (int pivotCol = 0; pivotCol < cols && pivotRow < rows; pivotCol++) {

            // Find row with largest pivot
            int maxRow = pivotRow;
            for (int i = pivotRow + 1; i < rows; i++) {
                if (Math.abs(result[i][pivotCol]) > Math.abs(result[maxRow][pivotCol])) {
                    maxRow = i;
                }
            }

            // If entire column below the current row = 0, move to next columns
            if (Math.abs(result[maxRow][pivotCol]) < 1e-9) {
                continue;
            }

            sb.append(String.format("--- Processing Column %d (Pivot Row Index = %d) ---\n", (pivotCol + 1), (pivotRow + 1)));

            // Swap rows if a larger pivot element was found lower down
            if (maxRow != pivotRow) {
                sb.append(String.format("  * Swap Row %d with Row %d for largest valid partial pivot (value: %.4f).\n", 
                    (pivotRow + 1), (maxRow + 1), result[maxRow][pivotCol]
                ));
                double[] temp = result[pivotRow];
                result[pivotRow] = result[maxRow];
                result[maxRow] = temp;
            }

            // Normalize pivot row
            double pivot = result[pivotRow][pivotCol];
            sb.append(String.format("  * Normalize Row %d: R%d -> R%d / %.4f\n", 
                (pivotRow + 1), (pivotRow + 1), (pivotRow + 1), pivot
            ));
            for (int j = 0; j < cols; j++) {
                result[pivotRow][j] /= pivot;
            }

            // Eliminate every other row (above and below pivot)
            for (int i = 0; i < rows; i++) {

                if (i == pivotRow) {
                    continue;
                }

                double factor = result[i][pivotCol];
                if (Math.abs(factor) < 1e-9) {
                    continue;
                }

                sb.append(String.format("  * Row Sweep: R%d -> R%d - (%.4f * R%d)\n", 
                    (i + 1), (i + 1), factor, (pivotRow + 1)
                ));

                for (int j = 0; j < cols; j++) {
                    result[i][j] -= factor * result[pivotRow][j];

                    // Remove floating-point errors
                    if (Math.abs(result[i][j]) < 1e-9) {
                        result[i][j] = 0;
                    }
                }
            }
            sb.append("\n");
            pivotRow++;
        }

        sb.append("Completed elimination. The system has reached its reduced row echelon form matrix state.\n");
        return new Calculations(result, sb.toString());
    }

}