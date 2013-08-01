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
        Engine.loadGameState();
    }
}
