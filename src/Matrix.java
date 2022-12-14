public class Matrix {
    private double[][] matrix = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
    };;

    Matrix(double[][] matrix) throws Exception {
        areRowsOfEqualLength(matrix);

        this.matrix = matrix;
    }

    Matrix() {}

    private void areRowsOfEqualLength(double[][] matrix) throws Exception {
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length != matrix[i - 1].length)
                throw new Exception("Rows must be of equal length");
        }
    }

    private int getMaxEntryLength() {
        int maxEntryLen = 0;

        for(double[] row: matrix) {
            for(double entry: row) {
                final int entryLen = String.valueOf(entry).length();

                if (entryLen > maxEntryLen) {
                    maxEntryLen = entryLen;
                }
            }
        }

        return maxEntryLen;
    }

    public void printMatrix() {
        printMatrix(matrix);
    }

    public void printMatrix(double[][] matrix) {
        int maxEntryLength = getMaxEntryLength();

        for(double[] row: matrix) {
            for(double entry: row) {
                int entryLen = String.valueOf(entry).length();
                int multiply = maxEntryLength - entryLen;

                System.out.print(" ".repeat(multiply) + entry + ",  ");
            }
            System.out.print('\n');
        }
    }

    public void isSquare() throws Exception {
        for(double[] row: matrix) {
            if (row.length != matrix.length) {
                throw new Exception("Array must be a square");
            }
        }
    }

    public double[][] getSubMatrix(double[][] matrix, int row, int column) {
        double[][] subMatrix = new double[matrix.length - 1][matrix[0].length - 1];

        for (int i = 0; i < matrix.length; i++) {
            if (i == row) continue;
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == column) continue;
                subMatrix[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
            }
        }

        return subMatrix;
    }

    public double getDeterminant() throws Exception {
        return getDeterminant(matrix);
    }

    public double getDeterminant(double[][] matrix) throws Exception {
        isSquare();

        if (matrix.length == 1) {
            return matrix[0][0];
        }

        double determinant = 0;

        for(int j = 0; j < matrix.length; j++) {
            int i = 0;

            determinant += (matrix[i][j] * getDeterminant(getSubMatrix(matrix, i ,j)) * Math.pow(-1, i + j));
        }

        return determinant;
    }

    private double[][] getCofactor() throws Exception {
        double[][] cofactorMatrix = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                cofactorMatrix[i][j] = Math.pow(-1, i + j) * getDeterminant(getSubMatrix(matrix, i, j));
            }
        }

        return cofactorMatrix;
    }

    public Matrix getCofactorExpansionMatrix() throws Exception {
        isSquare();

        return new Matrix(getCofactor());
    }

    public double[][] getTranspose(double[][] matrix) {
        double[][] transposeMatrix = new double[matrix[0].length][matrix.length];

        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposeMatrix[j][i] = matrix[i][j];
            }
        }

        return transposeMatrix;
    }

    public double[][] getTranspose() throws Exception {
        return getTranspose(matrix);
    }

    public Matrix getTransposeMatrix() throws Exception {
        return new Matrix(getTranspose());
    }

    private double[][] getAdjoint(double[][] matrix) throws Exception {
        return getTranspose(getCofactor());
    }

    private double[][] getAdjoint() throws Exception {
        return getAdjoint(matrix);
    }

    public Matrix getAdjointMatrix() throws Exception {
        return new Matrix(getAdjoint());
    }

    private double[][] getInverse() throws Exception {
        return getInverse(matrix);
    }

    private double[][] getInverse(double[][] matrix) throws Exception {
        isSquare();
        double determinant = getDeterminant();
        double[][] adjointMatrix = getAdjoint(matrix);
        double[][] inverseMatrix = new double[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                inverseMatrix[i][j] = adjointMatrix[i][j] / determinant;
            }
        }

        return inverseMatrix;
    }

    public Matrix getInverseMatrix() throws Exception {
        return new Matrix(getInverse());
    }
}
