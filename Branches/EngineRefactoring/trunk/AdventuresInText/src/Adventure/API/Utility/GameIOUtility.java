package Adventure.API.Utility;

/**
 * This interface is used to represent the user interface to the game, or more specifically, the source of the strings
 * of input that the game manipulates for its processing and the destination for the strings of output data that
 * the game generates while being played. In practice, this will allow for the complete customization of the entire
 * user interface.
 */
public interface GameIOUtility
    extends GameEngineUtility
{
    /**
     * This method is used to halt the execution of the game until the player enters a string of input data, which is
     * then returned back to the Engine for processing and execution is resumed.
     * 
     * @return A string of input from the player of the game.
     */
    public abstract String getInput();

    /**
     * This method is used to halt the execution of the game until the player enters an integer number 0 or greater, which is
     * then returned back to the Engine for processing and execution is resumed.
     * 
     * @return An integer number 0 or greater representing a player choice from a list of options.
     */
    public abstract int getChoice();

    /**
     * This method is used to display the output data being maintained by the output buffer in the IO class.
     * 
     * @param outputBuffer This is a reference to the output buffer being maintained by the IO class.
     */
    public abstract void display( StringBuilder outputBuffer );

    /**
     * This method is used to display the output data being maintained by the output buffer in the IO class, along with
     * the provided text prompt for additional input.
     * 
     * @param outputBuffer This is a reference to the output buffer being maintained by the IO class.
     * @param prompt The text string to be displayed to the player as the prompt.
     */
    public abstract void display( StringBuilder outputBuffer, String prompt );

    /**
     * This method is used to wipe the output screen clear of text so that it can be repopulated with the output being
     * currently held in the output buffer.
     */
    public abstract void clearScreen();
}
