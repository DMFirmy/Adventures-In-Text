package Adventure.Addon;

import Adventure.*;

import Adventure.Base.*;

/**
 * This is a simple demo of a custom GameAction that will remove the status "flying" from the player.
 */
public class Land
    extends BaseCommand
{
    @SuppressWarnings( "compatibility:-2225848715889213705" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor creates new Land command object.
     */
    public Land()
    {
        super( "Land", "land back on the ground", "ln" );
    }

    /**
     * This command will remove the status "flying" from the current player.
     * 
     * @param operation Not used in this command.
     */
    public void run( Operation operation )
    {
        Engine.getCurrentPlayer().removeStatus( "flying");
        IO.addLine( "Your feet land flat on the ground." );
    }
}
