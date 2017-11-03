import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Jama.Matrix;

import Jama.SingularValueDecomposition;

public class SVDImage {

    private BufferedImage image;
    
    private Matrix imgPixels;
    
    private int imgWidth;
    private int imgHeight;
    
     
    public SVDImage( String imgName ) throws IOException {
        
        File file = new File(imgName);
        BufferedImage image = null;
        
        if ( file.exists() ) {
            System.out.println( image );
            image = ImageIO.read(SVDImage.class.getResource(imgName));
            System.out.println( image );
        }

        imgWidth = image.getWidth();
        imgHeight = image.getHeight();
        imgPixels = new Matrix( imgWidth, imgHeight );
        
        populateMatrixRGB();
        
    }
    
    public void populateMatrixRGB() {
        
//        for ( int i = 0; i < imgWidth; i++ ) {
//            for ( int j = 0; j < imgHeight; j++ ) {
//                imgPixels.set( i, j, image.getRGB( i, j ) );
//            }
//        }
//        
//        imgPixels.toString();
        
        for ( int i = 0; i < imgHeight; i++ ) {
          for ( int j = 0; j < imgWidth; j++ ) {
              image.getRGB( i, j );
          }
        }
            
    }
}
