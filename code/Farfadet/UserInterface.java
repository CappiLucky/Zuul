import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

/**
 *  Project Zuul
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling (DB edited)
 * @author modified by Manon HERMANN    
 * @version v1
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private Parser     aParser;
    //creation des bouttons
    private JButton    aButtonHelp;
    private JButton    aButtonLook;
    private JButton    aButtonBack;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     * 
     * @param pText texte a afficher
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     * 
     * @param pText texte a afficher
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     * 
     * @param pImageName nom de l'image
     */
    public void showImage( final String pImageName )
    {
        URL vImageURL = this.getClass().getClassLoader().getResource( pImageName );
        if ( vImageURL == null )
            System.out.println( "image not found" );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     * 
     * @param pOnOff savoir si le jeu est en cours ou non
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff );
        if ( ! pOnOff )
            this.aEntryField.getCaret().setBlinkRate( 0 );
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Alerte au pied de l'arc en ciel" ); //titre de la fenetre
        this.aEntryField = new JTextField( 34 ); //police d'ecriture
        
        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();
        JPanel vPanelButtonCommande = new JPanel();
        
        
        //creation bouttons d'actions:
        this.aButtonHelp =  new JButton ("help");
        this.aButtonLook = new JButton ("look");
        this.aButtonBack = new JButton ("back");
        
        //disposition de la fenetre de jeu
        vPanel.setLayout( new BorderLayout() );
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
            //ajout d'autres boutons
        vPanelButtonCommande.add( this.aButtonHelp, BorderLayout.EAST); 
        vPanelButtonCommande.add( this.aButtonLook, BorderLayout.WEST); 
        vPanelButtonCommande.add( this.aButtonBack, BorderLayout.SOUTH); 
        
        
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );
            //ajout d'autres panels
        this.aMyFrame.getContentPane().add( vPanelButtonCommande, BorderLayout.EAST );
        
        
        // add some event listeners to some components
        this.aMyFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );

        this.aEntryField.addActionListener( this );
        this.aButtonHelp.addActionListener (this);
        this.aButtonLook.addActionListener (this);
        this.aButtonBack.addActionListener (this);

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
        Object vSource=pE.getSource();
        if (vSource==aButtonHelp){
            aEngine.interpretCommand("help");
        } else if (vSource==aButtonLook) {
            aEngine.interpretCommand("look");
        } else if (vSource==aButtonBack) {
            aEngine.interpretCommand("back");
        } 
        else this.processCommand();
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );
        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
