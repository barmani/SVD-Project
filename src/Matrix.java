
public class Matrix {

    private int m;
    private int n;
    private int[][] matrix;
    
    public Matrix( int n, int m ) {
        this.n = n;
        this.m = m;
        matrix = new int[m][n];
    }
    
    public Matrix transpose() {
        
        Matrix transpose = new Matrix( m, n );
        
        for ( int i = 0; i < m; i++ ) {
            for ( int j = 0; j < n; j++ ) {
                transpose.addEntry( i, j, matrix[j][i] );
            }
        }
        
        return transpose;       
        
    }
    
    public int getM() {
        return m;
    }
    
    public int getN() {
        return n;
    }
    
    public void addEntry( int m, int n, int entry ) {
        matrix[m][n] = entry;
    }
    
    public int getEntry( int m, int n ) {
        return matrix[m][n];
    }
    
    public void printMatrix() {
        
        for ( int i = 0; i < m; i++ ) {
            System.out.println();
            for ( int j = 0; j < n; j++ ) {
                System.out.print( matrix[i][j] + " ");
            }
        }
        
        System.out.println();
        
    }
    
    
    
}
