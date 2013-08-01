package Adventure.Addon;

import Adventure.*;

import Adventure.Base.*;

/**
 * This is a demo of a custom command. It will add the status "flying" to the player.
 */
public class Fly
    extends BaseAction
{
    @SuppressWarnings( "compatibility:4301977695854674412" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor creates new Fly command object.
     */
    public Fly()
    {
        super( "Fly", "Fly above the ground", "f" );
    }

    /**
     * This command will add the status "flying" to the current player.
     * 
     * @param operation Not used in this command.
     */
    public void run( Operation operation )
    {
        Engine.getCurrentPlayer().setStatus( "flying", 1 );
        IO.addLine( "Your feet lift from the ground, and you discover that you can fly!" );
    }
}
