package BasicGUI;

import Adventure.API.Utility.*;

import java.awt.*;

import javax.swing.*;

/**
 * This class is the window that our simple Swing based GUI will run in.
 */
public class BasicGUIWindow
    extends JFrame
    implements GameIOUtility
{
    @SuppressWarnings( "compatibility:-3467967334140865147" )
    private static final long serialVersionUID = 1L;

    private BasicGUIPanel panel;

    private Dimension dimension;
    
    private String name;
    /**
     * In this constructor we initialize the GamePanel object that holds the interface itself. It also serves as the
     * GameIOUtility that the IO class will use to gather player input and display output back to them. This is actually
     * handled within the GamePanel, but the GameWindow object serves as a passthrough between the IO class and the 
     * GamePanel object contained within the window. This constructor also makes sure the window is properly sized and
     * centered on the screen.
     */
    public BasicGUIWindow()
    {
        super();
        this.name = "BasicGUI";
        Toolkit tk = Toolkit.getDefaultToolkit();
        dimension = tk.getScreenSize();

        this.panel = new BasicGUIPanel();
        this.add( panel );

        this.setTitle( "Adventures In Text" );
        this.setSize( 640, 450 );
        this.setResizable( false );
        this.centerGUI();
        this.setVisible( true );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }
    
    public String getName()
    {
        return this.name;
    }

    /**
     * This method will center the GameWindow GUI on the screen.
     */
    private void centerGUI()
    {
        setLocation( ( dimension.width - this.getWidth() ) / 2, ( dimension.height - this.getHeight() ) / 2 );
    }

    /**
     * This method is a passthrough method between the IO class and the GamePanel object where input is actually
     * gathered from the player.
     * 
     * @return The string of input gathered from the GamePanel object to be passed to the IO class.
     */
    public String getInput()
    {
        return this.panel.getInput();
    }

    /**
     * This method is a passthrough method between the IO class and the GamePanel object where input is actually
     * displayed. It is used to clear the screen.
     */
    public void clearScreen()
    {
        this.panel.clear();
    }

    /**
     * This method is a passthrough method between the IO class and the GamePanel object where input is actually
     * gathered from the player.
     * 
     * @return An integer value gathered as input from the player from the GamePanel object to be passed to the IO class.
     */
    public int getChoice()
    {
        return this.panel.getChoice();
    }

    /**
     * This method is a passthrough method between the IO class and the GamePanel object where input is actually
     * displayed. It is used to display the contents of the output buffer on the screen.
     * 
     * @param outputBuffer The output buffer being maintained by the IO class.
     */
    public void display( StringBuilder outputBuffer )
    {
        this.panel.showOutput( outputBuffer );
    }

    /**
     * This method is a passthrough method between the IO class and the GamePanel object where input is actually
     * displayed. It is used to display the contents of the output buffer on the screen along with the given prompt.
     * 
     * @param outputBuffer The output buffer being maintained by the IO class.
     * @param prompt the propmt to display to the player asking them for input.
     */
    public void display( StringBuilder outputBuffer, String prompt )
    {
        this.panel.showOutput( outputBuffer, prompt );
    }
}
