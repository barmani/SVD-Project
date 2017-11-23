import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class ImageDisplayListener implements ActionListener {

    private File selectedFile;
    
    private ImageDisplayWindow window;
    
    private JFileChooser chooser;
    
    public ImageDisplayListener( ImageDisplayWindow window ) {
        this.window = window;
        
        chooser = new JFileChooser();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JButton button = (JButton) e.getSource();
        
        if ( button.getText().equals( "Browse Files" ) ) {
            
            chooser.showOpenDialog( window );
            selectedFile = chooser.getSelectedFile();
            
            try {
                SVDImage image = new SVDImage( selectedFile );
                image.drawApproximation(300);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
                    
            
        } else if ( button.getText().equals( "Quit" ) ) {
            System.exit( 0 );
        }
        
        
        
        
    }
    
    public File getFile() {
        
        return selectedFile;
        
    }

}
