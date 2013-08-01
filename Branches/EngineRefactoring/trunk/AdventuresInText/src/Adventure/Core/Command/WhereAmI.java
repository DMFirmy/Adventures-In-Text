package Adventure.Core.Command;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This method will display the name of the players current location to the screen.
 */
public class WhereAmI
    extends BaseAction
{
    @SuppressWarnings( "compatibility:-2254009252963853858" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the WhereAmI command.
     */
    public WhereAmI()
    {
        super( "WhereAmI", "Get the name of your current location.", "i" );
    }

    /**
     * When run, this method will display the name of the players current location.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        GamePlayer player = Engine.getCurrentPlayer();
        IO.addLine( player.getLocation().getName() );
    }
}
