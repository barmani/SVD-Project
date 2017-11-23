import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImageDisplayWindow extends JFrame {

    private DefaultListModel<BufferedImage> listModel;
    
    private ImageDisplayListener listener;
    
    private JList list;
    
    private JPanel app;
    private JPanel buttons;
    private JPanel pictureWindow;
    
    JScrollPane scroller;
    
    private JButton browse;
    private JButton quit;
    
    public ImageDisplayWindow() {
        
        setSize( 900, 900 );
        
        setPanels();
        setList();
        setLayouts();
        setButtons();
        setListener();
        buildContent();
        
        setContentPane( app );
        setVisible( true );
          
    }
    
    private void buildContent() {
        
        buttons.add( browse );
        buttons.add( quit );
        
        pictureWindow.add( scroller );
        
        app.add( pictureWindow );
        app.add( buttons );
        
    }
    
    private void setList() {
        
        listModel = new DefaultListModel<BufferedImage>();
        
        list = new JList( listModel );
        
        scroller = new JScrollPane( list );
        
    }
    
    public void addToList( BufferedImage img ) {
        
        listModel.addElement( img );
        
    }
    
    public void updateList() {
        // TODO implement if needed

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
        
        listener = new ImageDisplayListener(this);
        
        browse.addActionListener( listener );
        quit.addActionListener( listener );
        
    }
    
    private void setButtons() {
        
        browse = new JButton( "Browse Files" );
        quit = new JButton( "Quit" );
    }
}
