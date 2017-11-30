import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is the listener for the display window. It responds to
 * events triggered from the buttons and the slider.
 */
public class ImageDisplayListener implements ActionListener, ChangeListener {

    private ArrayList<BufferedImage> imgList;
    
    private File selectedFile;
    
    private ImageDisplayWindow window;
    
    private JFileChooser chooser;
    
    private SVDImage image;
    
    /**
     * Constructor for the listener. Create access to the window, set the 
     * file chooser.
     * 
     * @param window the window being used
     */
    public ImageDisplayListener( ImageDisplayWindow window ) {
        
        this.window = window;
        
        imgList = new ArrayList<BufferedImage>();
        
        chooser = new JFileChooser();
        
    }
    
    /**
     * Respond to the browse and quit buttons to upload a picture
     * or quit the application.
     * 
     * @param e the event triggered
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JButton button = (JButton) e.getSource();
        
        if ( button.getText().equals( "Browse Files" ) ) {
            
            chooser.showOpenDialog( window );
            selectedFile = chooser.getSelectedFile();
            
            // produce list of images, create the slider, initialize it to the first image
            try {
                
                image = new SVDImage( selectedFile );
                
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            imgList = image.getImageList();       
            
            window.setSlider( 1, imgList.size(), 1 );
            window.setSize( image.getWidth() + 50, image.getHeight() + 140 );
            window.revalidate();
            
            changeDisplayImage( 1 );
   
        } else if ( button.getText().equals( "Save" ) ) {
            
            
        } else if ( button.getText().equals( "Quit" ) ) {
            
            System.exit( 0 );
            
        }
        
    }
    
    /**
     * Get the file select by the user.
     * 
     * @return the selected file
     */
    public File getFile() {
        
        return selectedFile;
        
    }

    /**
     * Respond to changes from the slider.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        
        JSlider slider = (JSlider) e.getSource();
        
        int value = slider.getValue();
        
        changeDisplayImage( value );
        
        window.changeLabelText( "" + value );
        
    }   
    
    /************************** private methods **********************/
    
    
    /**
     * Display the image at the current slider value in the window.
     * 
     * @param index index of image
     */
    private void changeDisplayImage( int index ) {
        
        if ( imgList != null && imgList.size() > 0 && index > 0 ) {

            window.changeDisplayImage( imgList.get( index - 1 ) );
            
        }
        
    }

}
