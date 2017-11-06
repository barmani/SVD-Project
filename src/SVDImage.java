import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;

import Catalano.Imaging.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Jama.Matrix;

import Jama.SingularValueDecomposition;

public class SVDImage {

    private BufferedImage image;
    
    private Matrix imgPixels;
    
    private int imgWidth;
    private int imgHeight;
    
     
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
        
        populateMatrixRGB();
        redrawPicture();
        
    }
    
    private void populateMatrixRGB() {
        
        for ( int i = 0; i < imgWidth; i++ ) {
            for ( int j = 0; j < imgHeight; j++ ) {
                imgPixels.set( i, j, image.getRGB( i, j ) );
            }
        }            
    }
    
    private void redrawPicture() {
        
        BufferedImage image2 = new BufferedImage( imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB );
        
        for ( int i = 0; i < imgWidth; i++ ) {
            for ( int j = 0; j < imgHeight; j++ ) {
                image2.setRGB( i, j, (int) imgPixels.get( i, j ) );
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
