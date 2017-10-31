/**
 * Class to represent a matrix as a 2d array.
 * 
 * @author Brendan Armani, Andrew Levy, Andrew Tomassone
 * @version 1.0
 */
public class Matrix {

    private int m;
    private int n;
    private int[][] matrix;
    
    /**
     * Construct an mxn matrix
     * 
     * @param m number of columns
     * @param n number of rows
     */
    public Matrix( int m, int n ) {
        
        if ( m > 0 && n > 0 ) {
            this.m = m;
            this.n = n;
            matrix = new int[m][n];
        }
        
    }  
 
    /**
     * Add an entry at a specific spot in the matrix.
     * 
     * @param m the row
     * @param n the column
     * @param entry number to add
     * @return if the entry was added successfully
     */
    public boolean addEntry( int m, int n, int entry ) {
        
        boolean isAdded = false;
        
        try {
            matrix[m][n] = entry;
            isAdded = true;
        } catch ( ArrayIndexOutOfBoundsException e ) {

        }
        
        return isAdded;
        
    }

    /**
     * Add two matrices together. Return null if dimensions
     * do not match.
     * 
     * @param other matrix to add to current matrix
     * @return the matrix A + B
     */
    public Matrix addMatrices( Matrix other ) {
        
        Matrix sum = new Matrix( m, n );
        
        if ( sameDimensions( other ) ) {
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
    
    /**
     * Determine if a matrix equals another; that is, 
     * the dimensions are the same and every entry is 
     * the same. 
     * 
     * @param other the matrix to compare with 
     * @return whether they are equal
     */
    public boolean equals( Matrix other ) {
        
        boolean isEqual = true;
        
        if ( !sameDimensions( other ) ) {
            isEqual = false;
        } else {
            for ( int i = 0; i < m; i++ ) {
                for ( int j = 0; j < n; j++ ) {
                    if ( getEntry( i, j ) != other.getEntry( i, j ) ) {
                        isEqual = false;
                    }
                }
            }
        }

        return isEqual;
        
    }
    
    /**
     * Return the entry at the desired spot.
     * 
     * @param m the row
     * @param n the column
     * @return Integer.MIN_VALUE if an invalid spot, otherwise
     * the entry at the given dimensions
     */
    public int getEntry( int m, int n ) {
        
        int entry;
        
        try {
            entry = matrix[m][n];
        } catch ( ArrayIndexOutOfBoundsException e ) {
            entry = Integer.MIN_VALUE;
        }
        
        return entry;
        
    }
    
    /**
     * Get the number of rows of the current matrix.
     * 
     * @return number of rows
     */
    public int getM() {
        return m;
    }
    
    /**
     * Get the number of columns of the current matrix.
     * 
     * @return number of columns
     */
    public int getN() {
        return n;
    }
    
    
    /**
     * Print the contents of the matrix to the console.
     */
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
     * Subtract two matrices. Return null if dimensions
     * do not match.
     * 
     * @param other matrix to subtract from
     * @return the matrix A - B
     */
    public Matrix subtractMatrices( Matrix other ) {
        
        Matrix difference = new Matrix( m, n );
        
        if ( sameDimensions( other ) ) {           
            for ( int i = 0; i < m; i++ ) {
                for ( int j = 0; j < n; j++ ) {                   
                    int entry = getEntry( i, j ) - other.getEntry( i, j ); 
                    difference.addEntry(j, i, entry);
                }
            }           
        } else {     
            
            difference = null;
            
        }
        
        return difference;
        
    }
    
    /**
     * Return the transpose of the current matrix.
     * 
     * @return the transposed matrix
     */
    public Matrix transpose() {
        
        Matrix transpose = new Matrix( m, n );
        
        for ( int i = 0; i < m; i++ ) {
            for ( int j = 0; j < n; j++ ) {
                transpose.addEntry( i, j, matrix[j][i] );
            }
        }
        
        return transpose;       
        
    }
    
    /********************* private methods ******************/
    
    /**
     * Return whether the matrices have the same dimensions.
     * 
     * @param other matrix to compare with
     * @return whether dimensions are the same
     */
    private boolean sameDimensions( Matrix other ) {
        return m == other.getM() && n == other.getN();
    }
    
}
