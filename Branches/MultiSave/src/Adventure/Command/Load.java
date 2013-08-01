package Adventure.Command;

import Adventure.*;

import Adventure.Base.*;

/**
 * This command is one of the 5 required system commands, and is used to reload the state of a game that has been
 * previously saved.
 */
public class Load
    extends BaseCommand
{
    @SuppressWarnings( "compatibility:3889425732526543200" )
    private static final long serialVersionUID = 1L;
    
    /**
     * This constructor enables the Load command.
     */
    public Load()
    {
        super( "Load", "Load the state of a previously saved game.", "9" );
    }

    /**
     * When run, this method will load the state of the game from a previously saved file.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        // First we will store a copy of the original input array so we can reference it.
        String[] commands = operation.getinputArray();

        // The operation's direct object and the mostRecentSaveName cannot both be blank strings.
        if ( commands[ 2 ].equals( "" ) && Save.getMostRecentSaveName().equals( "" ) )
        {
            // If they are, we need to add an error message to the output and return.
            IO.addLine( "You need to supply a name for the game to be loaded. Your game was not loaded." );
            return;
        }

        // Now we need to see if there is a saved game name specified.
        if ( !commands[ 2 ].equals( "" ) )
        {
            // If there is, we will store it as the most recent save for future reference.
            Save.setMostRecentSaveName( commands[ 2 ] );
            // Now we can load the game state for the given name.
            Engine.loadGameState( commands[ 2 ] );
            return;
        }
        
        // If no game name is specified, we just use the most recent save game name.
        Engine.loadGameState( Save.getMostRecentSaveName() );
    }
}
