import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class ImageDisplayWindow extends JFrame {

    private DefaultListModel listModel;
    
    private ImageDisplayListener listener;
    
    private JButton browse;
    private JButton quit;
        
    private JLabel titleLabel;
    
    private JList list;
    
    private JPanel app;
    private JPanel buttons;
    private JPanel pictureWindow;
    private JPanel slidebar;
    private JPanel title;
    
    JScrollPane scroller;

    private JSlider slider;
    
    
    public ImageDisplayWindow() {
        
        setPanels();
        setList();
        setLayouts();
        setButtons();
        setListener();
        setLabels();
        buildContent();
        
        
        setContentPane( app );
        pack();
        setVisible( true );
          
    }
    
    private void buildContent() {
        
        buttons.add( browse );
        buttons.add( quit );
        
        pictureWindow.add( scroller );
        
        title.add( titleLabel );
        
        app.add( title );
        app.add( pictureWindow );
        app.add( slidebar );
        app.add( buttons );
        
    }
    
    private void setList() {
        
        listModel = new DefaultListModel();
        
        list = new JList( listModel );
        
        scroller = new JScrollPane( list );
        
        //scroller.setPreferredSize( new Dimension( 1000, 800 ) );
        
    }
    
    /**
     * Set the slider and listener for the slider after the image has
     * been uploaded.
     * 
     * @param min first value on the slider
     * @param max last value on the slider
     */
    public void setSlider( int min, int max ) {
        
        ImageDisplayListener slideListener = new ImageDisplayListener( this );
        
        slider = new JSlider( min, max );
        
        slider.addChangeListener( slideListener );
        
        slidebar.add( slider );
        
        
    }
    
    public void addToList( BufferedImage img ) {
        
        
        ImageIcon icon = new ImageIcon( img );
        
        listModel.addElement( icon );
        
    }
    
    public void updateList() {
        // TODO implement if needed

    }
    
    private void setPanels() {
        
        app = new JPanel();
        buttons = new JPanel();
        pictureWindow = new JPanel();
        slidebar = new JPanel();
        title = new JPanel();
        
    }
    
    private void setLayouts() {
        
        app.setLayout( new BoxLayout( app, BoxLayout.Y_AXIS ) );
        buttons.setLayout( new FlowLayout() );
        pictureWindow.setLayout( new BorderLayout() );
        slidebar.setLayout( new FlowLayout() );
        title.setLayout( new FlowLayout() );

    }
    
    private void setListener() {
        
        listener = new ImageDisplayListener(this);
        
        browse.addActionListener( listener );
        quit.addActionListener( listener );
        
    }
    
    private void setLabels() {
        
        titleLabel = new JLabel( "Math 448 SVD Analysis" );
        
    }
    
    private void setButtons() {
        
        browse = new JButton( "Browse Files" );
        quit = new JButton( "Quit" );
    }
    
}
