import java.awt.Image;

import Catalano.Imaging.*;

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
        
        File file = new File( imgName );
        
        System.out.println( file.exists() );
        
        Image preImage = ImageIO.read( file );
                
        image = new BufferedImage( preImage.getWidth(null), preImage.getHeight(null), BufferedImage.TYPE_INT_RGB );
        System.out.println( image );
        

        imgWidth = image.getWidth();
        imgHeight = image.getHeight();
        imgPixels = new Matrix( imgWidth, imgHeight );
        
        populateMatrixRGB();
        
    }
    
    public void populateMatrixRGB() {
        
        for ( int i = 0; i < imgWidth; i++ ) {
            for ( int j = 0; j < imgHeight; j++ ) {
                imgPixels.set( i, j, image.getRGB( i, j ) );
            }
        }
        
        System.out.println(imgPixels);
        
        
       // FastBitmap map = new FastBitmap( image );
        
       // double[][] arr = map.toMatrixGrayAsDouble();
        
            
    }
}
