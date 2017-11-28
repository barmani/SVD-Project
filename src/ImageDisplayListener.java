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

public class ImageDisplayListener implements ActionListener, ChangeListener {

    ArrayList<BufferedImage> imgList;
    
    private File selectedFile;
    
    private ImageDisplayWindow window;
    
    private JFileChooser chooser;
    
    /**
     * Constructor for the listener. Create access to the window, set the 
     * file chooser.
     * 
     * @param window the window being used
     */
    public ImageDisplayListener( ImageDisplayWindow window ) {
        
        this.window = window;
        
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
            
            try {
                
                SVDImage image = new SVDImage( selectedFile );
                imgList = image.getImageList();
                
                window.setSlider( 1, imgList.size() );
                window.revalidate();
                
            } catch (IOException e1) {
                e1.printStackTrace();
            }
                    
            
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
        
    }
    
    /**
     * Display the image at the current slider value in the window.
     * 
     * @param index index of image
     */
    private void changeDisplayImage( int index ) {
        
    }

}
