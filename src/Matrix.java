
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
    
    /**
     * Add two matrices together. Return null if dimensions
     * do not match.
     * 
     * @param other matrix to add to current matrix
     * @return the matrix A + B
     */
    public Matrix addMatrix( Matrix other ) {
        
        Matrix sum = new Matrix( m, n );
        
        if ( m == other.getM() && n == other.getN() ) {
            for ( int i = 0; i < m; i++ ) {
                for ( int j = 0; j < n; j++ ) {                   
                    int entry = getEntry( i, j ) + other.getEntry( i, j ); 
                    sum.addEntry(j, i, entry);
                }
            }
        } else {
            sum = null;
        }
        
        return sum;
        
    }
    
    
    
}
