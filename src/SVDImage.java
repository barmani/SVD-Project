import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Jama.Matrix;

import Jama.SingularValueDecomposition;

/**
 * Creates SVD approximations of a given image.
 * 
 * @author Brendan Armani, Andrew Levy, Andrew Tomassone
 * @version 1.0
 */
public class SVDImage {

    private BufferedImage image;
    
    private Matrix imgPixels;
    
    private Matrix[] pictures;
    
    private int imgWidth;
    private int imgHeight;
    
    /**
     * Constructor for an SVDImage. Creates a bufferedImage for the image the
     * user wants to approximate, then makes a matrix of the image's RGB values 
     * and an array of the SVD approximations of the image.
     * 
     * @param imgName the name of the image file
     * @throws IOException input/output exception
     */
    public SVDImage( String imgName ) throws IOException {
        
        File file = new File( imgName );
        
        Image preImage = ImageIO.read( file );
                
        image = new BufferedImage( preImage.getWidth(null), preImage.getHeight(null), BufferedImage.TYPE_INT_RGB );

        Graphics2D g = image.createGraphics();
        g.drawImage(preImage, 0, 0, null);
        g.dispose();       

        imgWidth = image.getWidth();
        imgHeight = image.getHeight();
        imgPixels = new Matrix( imgWidth, imgHeight );
        pictures = new Matrix[imgWidth];
        
        populateMatrixRGB();
        constructSVDMatrix();
 
    }    
    
    /**
     * Draw a picture of a given approximation from the array of
     * approximations next to the original picture.
     * 
     * @param index the index of pictures the user wants to draw
     */
    public void drawApproximation( int index ) {
        
        if ( index >= 0 && index < pictures.length ) {
            BufferedImage image2 = new BufferedImage( imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB );
            
            for ( int i = 0; i < imgWidth; i++ ) {
                for ( int j = 0; j < imgHeight; j++ ) {
                    image2.setRGB( i, j, (int) pictures[index].get( i, j ) );
                }
            }

            // draw the image
            JFrame frame = new JFrame();
            frame.getContentPane().setLayout(new FlowLayout());
            frame.getContentPane().add(new JLabel(new ImageIcon( image )));
            frame.getContentPane().add(new JLabel(new ImageIcon( image2 )));
            frame.pack();
            frame.setVisible(true);
        }
        
    }
    
    /**
     * Populate the pictures array with each approximation using 
     * the singular values.
     */
    private void constructSVDMatrix() {
        
        SingularValueDecomposition svd = new SingularValueDecomposition( imgPixels );
        Matrix u = svd.getU();
        Matrix s = svd.getS();
        Matrix v = svd.getV();
        
        Matrix newU;
        double newS;
        Matrix newV;
        
        for ( int i = 0; i < imgPixels.getColumnDimension(); i++ ) {
            newU = u.getMatrix( 0, imgPixels.getRowDimension() - 1, i, i);
            newV = v.getMatrix( 0, imgPixels.getColumnDimension() - 1, i, i);
            newS = s.get( i, i );
            Matrix matrixToAdd = newU.times(newV.transpose() );
            matrixToAdd = matrixToAdd.times( newS );
            
            if ( i == 0 ) {
                pictures[i] = matrixToAdd;
            } else {
                pictures[i] = pictures[i - 1].plus( matrixToAdd );
            }
              
        }
        
    }
    
    /**
     * Fill the imgPixels matrix with RGB values from the 
     * bufferedImage;
     */
    private void populateMatrixRGB() {
        
        for ( int i = 0; i < imgWidth; i++ ) {
            for ( int j = 0; j < imgHeight; j++ ) {
                imgPixels.set( i, j, image.getRGB( i, j ) );
            }
        }            
    }
  
}
