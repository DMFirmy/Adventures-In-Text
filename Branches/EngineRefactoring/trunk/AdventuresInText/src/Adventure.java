/**
 * Adventures in Text
 * @version 2.0
 * @author Christopher Firman
 */

import Adventure.*;

/**
 * This class houses the main() method for the entire game and will load in the correct IOUtility based on the command line
 * arguments that are passed in to it.
 */
public class Adventure
{
    /**
     * This class is completely static, so we make the constructor private.
     */
    private Adventure()
    {
    }

    /**
     * This is the application main() method, which holds the game loop.
     *
     * @param args By passing the value of "gui" in to this method as an argument the game's Swing GUI will load.
     */
    public static final void main( String[] args )
    {
        // First we need to parse the command line arguments to figure out which IOUtility to load.
        Engine.parseCommandLineArguments( args );

        // This is the main game loop, which will continue till the game is ended.
        while ( !Engine.isGameOver() )
        {
            Engine.showOutput();
            Engine.getInput();
        }
    }
}
