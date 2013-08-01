package Adventure.Command;

import Adventure.*;

import Adventure.Base.*;

/**
 * This command is one of the 5 required system commands, and is used to save the current state of the game for later.
 */
public class Save
    extends BaseCommand
{
    @SuppressWarnings( "compatibility:6029799020586678932" )
    private static final long serialVersionUID = 1L;

    private static String mostRecentSaveName;

    static
    {
        mostRecentSaveName = "";
    }

    public static void setMostRecentSaveName( String saveName )
    {
        mostRecentSaveName = saveName;
    }

    public static String getMostRecentSaveName()
    {
        return mostRecentSaveName;
    }

    /**
     * This constructor enables the Save command.
     */
    public Save()
    {
        super( "Save", "Save the state of the game to a file.", "0" );
    }

    /**
     * When run, this method will save the current game state to a file.
     *
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        // First we will store a copy of the original input array so we can reference it.
        String[] commands = operation.getinputArray();

        // The operation's direct object and the mostRecentSaveName cannot both be blank strings.
        if ( commands[ 2 ].equals( "" ) && mostRecentSaveName.equals( "" ) )
        {
            // If they are, we need to add an error message to the output and return.
            IO.addLine( "You need to supply a name for the game to be saved. Your game was not saved." );
            return;
        }

        // Now we need to see if there is a saved game name specified.
        if ( !commands[ 2 ].equals( "" ) )
        {
            // If there is, we will store it as the most recent save for future reference.
            setMostRecentSaveName( commands[ 2 ] );
            // Now we can load the game state for the given name.
            Engine.saveGameState( commands[ 2 ] );
            return;
        }

        // If no game name is specified, we just use the most recent save game name.
        Engine.saveGameState( mostRecentSaveName );
    }
}
