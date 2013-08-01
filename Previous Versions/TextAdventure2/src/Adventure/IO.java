package Adventure;

import Adventure.API.Utility.*;

/**
 * This class is used to handle gathering input and output for the game. It utilizes a GameIOUtility object which can be
 * changed out to allow for customization to the interface.
 */
public class IO
{
    private static StringBuilder outputBuffer;

    private static GameIOUtility io;
    
    /**
     * Here we initialize our static output buffer.
     */
    static
    {
        outputBuffer = new StringBuilder();
    }

    /**
     * This class is completely static, so we make the constructor private.
     */
    private IO()
    {
    }

    /**
     * This method will append a string of text to the end of the output buffer.
     * 
     * @param text The String of text to be appended.
     */
    public static void add( String text )
    {
        outputBuffer.append( text );
    }

    /**
     * This method will append a string of text to the end of the output buffer, along with a line break at the end.
     *
     * @param text The String of text to be appended.
     */
    public static void addLine( String text )
    {
        outputBuffer.append( text + "\n" );
    }

    /**
     * This method will clear the screen and display the current contents of the output buffer.
     * The output buffer is then cleared.
     */
    public static void display()
    {
        clearScreen();
        io.display( outputBuffer );
        clearBuffer();
    }

    /**
     * This method will clear the screen and display the current contents of the output buffer, along with the prompt text
     * that gets passed in. The output buffer is then cleared.
     * 
     * @param prompt This is the prompt text that is displayed to the player asking for more input.
     */
    public static void display( String prompt )
    {
        clearScreen();
        io.display( outputBuffer, prompt );
        clearBuffer();        
    }

    /**
     * This method is used to clear the screen of its current contents.
     */
    public static void clearScreen()
    {
        io.clearScreen();
    }

    /**
     * This method is used to gather a string of input from the player that the engine can parse and process.
     * 
     * @return The String of text that the player input.
     */
    public static String getInput()
    {
        return io.getInput();
    }

    /**
     * This method is used to gather an integer value of input that the engine can use to determine player choices. Choices
     * will always be greater than or equal to 0.
     * 
     * @return The integer value that a player entered, or a -1 if it could not be determined.
     */
    public static int getChoice()
    {
        return io.getChoice();
    }

    /**
     * This method is used to reassign the GameIOUtility object that the IO class uses for its processing.
     * 
     * @param newIOUtility The new GameIOUtility object that the IO class will use.
     */
    public static void setIOUtility( GameIOUtility newIOUtility )
    {
        io = newIOUtility;
    }

    /**
     *This method will get a reference to the current GameIOUtility object being used by the IO class.
     * 
     * @return a reference to the GameIOUtility object being used by the IO class.
     */
    static final GameIOUtility getIOUtility()
    {
        return io;
    }
    
    /**
     * This method is used to clear the contents of the output buffer.
     */
    public static void clearBuffer()
    {
        outputBuffer.delete( 0, outputBuffer.length() );
    }

}
