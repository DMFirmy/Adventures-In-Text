package Adventure.Command;

import Adventure.*;

import Adventure.Base.*;

/**
 * This command is one of the 5 required system commands, and is used to end the game.
 */
public class Quit
    extends BaseCommand
{
    @SuppressWarnings( "compatibility:-3727853171884214302" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the Quit command.
     */
    public Quit()
    {
        super( "Quit", "Exits the game.", "q" );
    }

    /**
     * When run, this method will cause the game to end.
     * 
     * @param operation The current Operation object that is being processed. 
     */
    public void run( Operation operation )
    {
        Engine.promprForSave();
        Engine.endGame( "Thank you for playing Adventures in Text." );
    }
}
