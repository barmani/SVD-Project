import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    public SVDImage( File file ) throws IOException {
        
        Image preImage = ImageIO.read( file );
                
        image = new BufferedImage( preImage.getWidth(null), preImage.getHeight(null), BufferedImage.TYPE_INT_RGB );
        
        Graphics2D g = image.createGraphics();
        g.drawImage(preImage, 0, 0, null);
        g.dispose();       

        imgWidth = image.getWidth();
        imgHeight = image.getHeight();
        imgPixels = new Matrix( imgWidth, imgHeight );
        pictures = new Matrix[imgHeight];
        
        populateMatrixRGBWithGray();
        changeMatrixToDoubles();
        constructSVDMatrix();
        convertMatricesBack();       
        
    }    
    
    /**
     * Compare a specific singular value picture with the 
     * original picture.
     * 
     * @param index the index of pictures the user wants to draw
     */
    public void compareApproximation( int index ) {
        
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
     * Return the array of stored images calculating by adding the next
     * singular value to the previous image.
     * 
     * @return the list of images
     */
    public ArrayList<BufferedImage> getImageList() {
        
        ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();

        for ( int i = 0; i < imgHeight; i++ ) {

            BufferedImage tempImg = new BufferedImage( imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB );
            
            // construct matrices into buffered images
            for ( int j = 0; j < imgWidth; j++ ) {
                for ( int k = 0; k < imgHeight; k++ ) {
                    tempImg.setRGB( j, k, (int) pictures[i].get( j, k ) );
                }
            }
            
            list.add( tempImg );
            
        }
        
        return list;

    }
    
    /**
     * Get the height of the image.
     * 
     * @return the height
     */
    public int getHeight() {
        
        return imgHeight;
        
    }
    
    /**
     * Get the width of the image.
     * 
     * @return the width
     */
    public int getWidth() {
        
        return imgWidth;
        
    }
    
    /*********************** private methods *********************/
    
    /**
     * Change matrix elements to doubles.
     * 
     * ** Acknowledgement: 
     *      Method from vipinkumar7 in their SVD -for -image project
     */
    private void changeMatrixToDoubles() {
        
        for (int row = 0; row < imgWidth; row++) {
            for (int col = 0; col < imgHeight; col++) {
                imgPixels.set( row, col, imgPixels.get( row, col) / 255.00 );
            }
        }
        
//        JFrame frame = new JFrame();
//        frame.getContentPane().setLayout(new FlowLayout());
//        frame.getContentPane().add(new JLabel(new ImageIcon( image )));
//        frame.pack();
//        frame.setVisible(true);
        
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
            
            // construct next image from last
            if ( i == 0 ) {
                pictures[i] = matrixToAdd;
            } else {
                pictures[i] = pictures[i - 1].plus( matrixToAdd );
            }
              
        }
        
//        JFrame frame = new JFrame();
//        frame.getContentPane().setLayout(new FlowLayout());
//        frame.getContentPane().add(new JLabel(new ImageIcon( image )));
//        frame.pack();
//        frame.setVisible(true);
        
    }
    
    /**
     * Convert matrix values back to integers.
     * 
     * ** Acknowledgement: 
     *      Method from vipinkumar7 in their SVD -for -image project
     */
    private void convertMatricesBack() {
        
        for ( int i = 0; i < pictures.length; i++ ) {
            Matrix tempMatrix = pictures[i];
            for (int row = 0; row < imgWidth; row++) {
                for (int col = 0; col < imgHeight; col++) {
                    tempMatrix.set( row,  col, (int) ( tempMatrix.get( row, col ) * 255.00 ) );
                }
            }
        }
        
//        JFrame frame = new JFrame();
//        frame.getContentPane().setLayout(new FlowLayout());
//        frame.getContentPane().add(new JLabel(new ImageIcon( image )));
//        frame.pack();
//        frame.setVisible(true);

    }
    
    /**
     * Fill the imgPixels matrix with RGB values explicitly
     * converted to grayscale.
     * 
     * ** Acknowledgement: 
     *      Method from vipinkumar7 in their SVD -for -image project
     */
    private void populateMatrixRGBWithGray() {
        
        for (int row = 0; row < imgWidth; row++) {
            for (int col = 0; col < imgHeight; col++) {

//                int pixel = image.getRGB( row, col );
//
//                int R = (pixel >> 16) & 0x000000FF;
//                int G = (pixel >> 8) & 0x000000FF;
//                int B = (pixel) & 0x000000FF;
//                
//                // luminosity method of conversion
//                double gray =  0.2989 * R + 0.5870 * G + 0.1140 * B;
//                // int newPixel = (gray<<16) | (gray<<8) | gray;
                
                // Remove the alpha component
                Color c = new Color(image.getRGB(row, col) & 0x00ffffff);
                // Normalize
                int newRed = (int) (0.2989f * c.getRed());
                int newGreen = (int) (0.5870f * c.getGreen());
                int newBlue = (int) (0.1140f * c.getBlue());
                int roOffset = newRed + newGreen + newBlue;
                
                imgPixels.set( row, col, roOffset );

            }
        }
        
        BufferedImage tempImg = new BufferedImage( imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB );
        
        // construct matrices into buffered images
        for ( int j = 0; j < imgWidth; j++ ) {
            for ( int k = 0; k < imgHeight; k++ ) {
                tempImg.setRGB( j, k, (int) imgPixels.get( j, k ) );
            }
        }
        
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon( tempImg )));
        frame.pack();
        frame.setVisible(true);
        
    }
    
    
  
}
