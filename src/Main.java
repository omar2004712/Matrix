public class Main {
    public static void main(String[] args) throws Exception {
        double[][] matrixValues = {
                { 2, 1, 2 },
                { 3, 2, 2 },
                { 1, 2, 3 }
        };

        double[][] matrix2Values = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };


        Matrix identity = new Matrix();
        Matrix matrix = new Matrix(matrixValues);
        Matrix matrix2 = new Matrix(matrix2Values);

        identity.multiplyScalar(2);
        identity.printMatrix();
    }
}