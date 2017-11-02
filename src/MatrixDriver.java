import Jama.Matrix;


import Jama.SingularValueDecomposition;

public class MatrixDriver {

    public static void main(String[] args) {
       
        Matrix matrix = new Matrix(3, 3);
        
        matrix.set(0,0,57);
        matrix.set(0,1,44);
        matrix.set(0,2,55);
        matrix.set(1,0,33);
        matrix.set(1,1,3);
        matrix.set(1,2,4);
        matrix.set(2,0,8);
        matrix.set(2,1,55);
        matrix.set(2,2,5);
        
        
        SingularValueDecomposition svd = new SingularValueDecomposition( matrix );
        Matrix U = svd.getU();
        Matrix S = svd.getS();
        Matrix V = svd.getV();
        U.print(3, 5);
        S.print(3, 5);
        V.print(3, 5);
        
        SVDImage img = new SVDImage( "81.jpg" );
        
    }
    

}
