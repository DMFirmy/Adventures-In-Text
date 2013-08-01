/**
 * Adventures in Text
 * @version 2.0
 * @author Christopher Firman
 */

import Adventure.*;

import Adventure.Base.Utility.*;

import Adventure.UI.*;

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
        parseCommandLineArguments( args );
        
        // Now we can display the title screen to the player.
        Engine.showTitleScreen();
        
        // This is the main game loop, which will continue till the game is ended.
        while ( !Engine.gameOver() )
        {
            Engine.showOutput();
            Engine.getInput();
        }
    }

    /**
     * This method is used parse the command line arguments passed in to the main() method. It is used to determine if the
     * game should use the command line interface or to load the Swing based GUI window.
     * 
     * @param args The arguments passed into the main() method
     */
    private static void parseCommandLineArguments( String[] args )
    {
        // In later versions this should be able to load custom GUIs
        boolean useGUI = false;
        
        if ( args.length > 0 )
        {
            for ( String arg : args )
            {
                if ( arg.equalsIgnoreCase( "gui" ) )
                {
                    useGUI = true;
                }
            }
        }

        if ( useGUI )
        {
            IO.setIOUtility( new GameWindow() );
        }
        else
        {
            IO.setIOUtility( new SystemIOUtility() );
        }
    }
}
