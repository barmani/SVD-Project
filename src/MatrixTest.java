import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MatrixTest {

    private Matrix matrix1;
    
    @Before
    public void setUp() {
        
        matrix1 = new Matrix(3, 3);
        
        for ( int i = 0; i < 3; i++ ) {
            int entry = 1;
            for ( int j = 0; j < 3; j++ ) {
                matrix1.addEntry(i, j, entry);
                entry++;
            }
        }
        
    }
    
    @Test
    public void testAddAndSubtractMatrices() {
        
        Matrix testMatrix1 = new Matrix( 3, 3 );
        
        int entry = 2;
        
        for ( int i = 0; i < 3; i++ ) {           
            for ( int j = 0; j < 3; j++ ) {
                testMatrix1.addEntry( i, j, entry );            
            }
            entry += 2;
        }
        
        Matrix testMatrix2 = new Matrix( 3, 3 );
        
        for ( int i = 0; i < 3; i++ ) {           
            for ( int j = 0; j < 3; j++ ) {
                testMatrix2.addEntry( i, j, 0 );            
            }
        }
        
        assertTrue( testMatrix1.equals( matrix1.addMatrices( matrix1 ) ) );
        assertTrue( testMatrix2.equals( matrix1.subtractMatrices( matrix1 ) ) );
        
    }

}
