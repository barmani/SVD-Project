import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageDisplayWindow extends JFrame {

    private ImageDisplayListener listener;
    
    private JPanel app;
    private JPanel buttons;
    private JPanel pictureWindow;
    
    private JButton browse;
    
    public ImageDisplayWindow() {
        
        setSize( 900, 900 );
        
        setPanels();
        setLayouts();
        setButtons();
        setListener();
        buildContent();
        
        setContentPane( app );
        setVisible( true );
          
    }
    
    private void buildContent() {
        
        buttons.add( browse );
        
        app.add( buttons );
    }
    
    private void setPanels() {
        
        app = new JPanel();
        buttons = new JPanel();
        pictureWindow = new JPanel();
        
    }
    
    private void setLayouts() {
        
        app.setLayout( new BoxLayout( app, BoxLayout.Y_AXIS ) );
        buttons.setLayout( new FlowLayout() );
        pictureWindow.setLayout( new FlowLayout() );
    }
    
    private void setListener() {
        
        listener = new ImageDisplayListener();
        browse.addActionListener( listener );
        
    }
    
    private void setButtons() {
        
        browse = new JButton( "Browse Files" );
    }
}
