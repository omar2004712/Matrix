public class Main {
    public static void main(String[] args) throws Exception {
        double[][] matrixValues = {
                { 2, 1, 2 },
                { 3, 2, 2 },
                { 1, 2, 3 }
        };

        Matrix matrix = new Matrix(matrixValues);

        matrix.getInverseMatrix().printMatrix();
    }
}