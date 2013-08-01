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

    /**
     * This constructor enables the Save command.
     */
    public Save()
    {
        super( "Save", "Save the state of the game.", "0" );
    }

    /**
     * When run, this method will save the current game state to a file.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        Engine.saveGameState();
    }
}
