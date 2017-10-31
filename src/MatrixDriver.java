
public class MatrixDriver {

    public static void main(String[] args) {
       
        Matrix matrix = new Matrix(3, 3);
        
        for ( int i = 0; i < 3; i++ ) {
            int entry = 1;
            for ( int j = 0; j < 3; j++ ) {
                matrix.addEntry(i, j, entry);
                entry++;
            }
        }
        
        matrix.printMatrix();
        
        Matrix transpose = matrix.transpose();
        
        transpose.printMatrix();

        Matrix sum = matrix.addMatrix( matrix );
        
        sum.printMatrix();
        
    }

}
