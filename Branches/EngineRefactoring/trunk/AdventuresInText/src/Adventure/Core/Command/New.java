package Adventure.Core.Command;

import Adventure.*;

import Adventure.Base.*;

/**
 * This command is one of the 5 required system commands, and is used to allow a player to choose a level and to begin the
 * game after the level has been loaded into the Engine.
 */
public class New
    extends BaseCommand
{
    @SuppressWarnings( "compatibility:735773540170778568" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the New command.
     */
    public New()
    {
        super( "New", "Begin a new game.", "1" );
    }

    /**
     * When run, this method will load a list of levels that are currently available for the player to choose, then it
     * will load the initial state for the level that is chosen.
     *
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        // First we will store a copy of the original input array so we can reference it.
        String[] commands = operation.getinputArray();

        Engine.beginNewGame( commands[ 2 ] );
    }
}
