package RedGUI;

import Adventure.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * This class represents the content for the entire GUI interface and has all of the methods necessary to
 * gather the input necessary for the game and to display output back out to the player. Though this class doesnt implement
 * the GameIOUtility interface, all of the functionality for that interface as implemented by the GameWindow class
 * is actually present in this class. The GameWindow class actually serves as a passthrough between the IO class and
 * this class. It also acts as an event listener that looks for the enter key being pressed or the enter button
 * being clicked, signaling that there is a string of input ready.
 */
public class RedGUIPanel
    extends JPanel
    implements ActionListener
{
    @SuppressWarnings( "compatibility:3026359036205884011" )
    private static final long serialVersionUID = 1L;

    private JScrollPane gameWindowScroller;

    private JTextArea gameOutputWindow;

    private JTextField gameInputField;

    private JButton gameEnterButton;

    private String inputString;

    private boolean inputReady;

    /**
     * In this constructor we initialize all of the various components of the GUI window.
     */
    public RedGUIPanel()
    {
        try
        {
            // This makes the window non-opaque
            this.setOpaque( false );
            
            // These lines will configure the textarea that we will use for displaying output.
            this.gameOutputWindow = new JTextArea( 22, 80 );
            this.gameOutputWindow.setName( "Output Area" );
            this.gameOutputWindow.setMargin( new Insets( 8, 8, 8, 8 ) );
            this.gameOutputWindow.setWrapStyleWord( true );
            this.gameOutputWindow.setLineWrap( true );
            this.gameOutputWindow.setEditable( false );
            this.gameOutputWindow.setFont( new Font( "Monospaced", Font.PLAIN, 11 ) );
            this.gameOutputWindow.setText( "" );
            this.gameOutputWindow.setBackground( Color.RED );
            
            // Here we set up a scroll pane so that if our output is long we can scroll though it.
            this.gameWindowScroller =
                    new JScrollPane( gameOutputWindow, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
            
            // Here we will set up the field used to gather input.
            this.gameInputField = new JTextField();
            this.gameInputField.setName( "Input Field" );
            this.gameInputField.setColumns( 72 );
            this.gameInputField.setMargin( new Insets( 4, 4, 4, 4 ) );
            this.gameInputField.setFont( new Font( "Monospaced", Font.PLAIN, 11 ) );
            this.gameInputField.addActionListener( this );
            
            // Because we are simulating a console with our GUI we need to include an "Enter" button
            this.gameEnterButton = new JButton();
            this.gameEnterButton.setName( "Enter Button" );
            this.gameEnterButton.setText( "Enter" );
            this.gameEnterButton.addActionListener( this );
            
            // Now that all of our components are set up, we can add them to this panel.
            this.add( gameWindowScroller, null );
            this.add( gameInputField, null );
            this.add( gameEnterButton, null );
        }
        catch ( Exception e )
        {
            // If there is some type of error, we will just send a message to output and quit.
            IO.addLine( "There was an error loading the GUI. Now exiting the program..." );
            System.exit( 1 );
        }
    }

    /**
     * This method is used to clear the contents of the output window.
     */
    public void clear()
    {
        this.gameOutputWindow.setText( "" );
    }

    /**
     * This method is used to gather a string of input from the text field to be used by the IO class as input.
     * 
     * @return The string of player input to be processed.
     */
    public String getInput()
    {
        // Because we want the game to pause and wait for input, we will tell our thread to yield and wait until is ready
        while ( !inputReady )
        {
            Thread.yield();
        }

        // Now that we know we have a valid input string, we can unmark it as ready again.
        inputReady = false;
        // Now we store the value of the input string in a new string.
        String input = inputString;
        // We set the value of the input string to a blank string again.
        inputString = "";
        // And we return the value we stored in the new string above.
        return input;
    }

    /**
     * This method is used to gather an integer value of input that the engine can use to determine player choices.
     * Choices will always be greater than or equal to 0.
     *
     * @return The integer value that a player entered, or a -1 if it could not be determined.
     */
    public int getChoice()
    {
        String input = "";
        int choice = -1;

        input = this.getInput();
        try
        {
            choice = Integer.parseInt( input );
        }
        catch ( Exception e )
        {
            choice = -1;
            IO.addLine( "I don't understand that selection." );
        }
        return choice;
    }

    /**
     * This method displays the content of the output buffer to the screen then positions the caret at the end of the
     * output string so that if it is larger than the text area it will scroll to the last line.
     *
     * @param outputBuffer The output buffer passed in from the IO class.
     */
    public void showOutput( StringBuilder outputBuffer )
    {
        String output = gameOutputWindow.getText() + "\n" +
            outputBuffer.toString();
        gameOutputWindow.setText( output );
        gameOutputWindow.setCaretPosition( gameOutputWindow.getDocument().getLength() );
    }

    /**
     * This method displays the content of the output buffer to the screen along with the given prompt,
     * then positions the caret at the end of the output string so that if it is larger than the text area
     * it will scroll to the last line.
     *
     * @param outputBuffer The output buffer passed in from the IO class.
     * @param prompt The string prompt to display to the player asking them for more input.
     */
    public void showOutput( StringBuilder outputBuffer, String prompt )
    {
        String output = gameOutputWindow.getText() + "\n" +
            outputBuffer.toString();
        output += prompt + "\n";
        gameOutputWindow.setText( output );
        gameOutputWindow.setCaretPosition( gameOutputWindow.getDocument().getLength() );
    }

    /**
     * This is the event listener that will signal that the enter button or the enter key is being pressed.
     * 
     * @param e The ActionEvent object that represents the event.
     */
    public void actionPerformed( ActionEvent e )
    {
        // First we get a reference to the source of the event and store it.
        Object o = e.getSource();
        
        // Now we can check if the source is the enter button or the enter key being pressed within the input field
        if ( o.equals( gameEnterButton ) || o.equals( gameInputField ) )
        {
            // if it is we will store the contents of the input field as the input string.
            inputString = gameInputField.getText();
            // Next we can clear the input field so it is ready for more input.
            gameInputField.setText( "" );
            // Now we flag our input as ready, so the thread will resume.
            inputReady = true;
        }
    }
}
