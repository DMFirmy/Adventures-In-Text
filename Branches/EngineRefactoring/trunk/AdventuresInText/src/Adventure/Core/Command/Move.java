package Adventure.Core.Command;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This command will cause the player to move through the exit in their location that is in the direction specified.
 */
public class Move
    extends BaseAction
{
    @SuppressWarnings( "compatibility:1139701806000955862" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the Move command.
     */
    public Move()
    {
        super( "Move", "Moves the player to a new location.", "m" );
    }

    /**
     * When run, this method will move the player through the exit in the specified direction.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        String[] commands = operation.getinputArray();
        GamePlayer player = Engine.getCurrentPlayer();
        for ( GameExit exit : player.getLocation().exitList() )
        {
            if ( exit.getExitDirection().getHotkey().equalsIgnoreCase( commands[ 2 ] ) ||
                 exit.getExitDirection().getName().equalsIgnoreCase( commands[ 2 ] ) && !exit.isHidden() )
            {
                player.setLocation( exit.getExitLocation(), true );
            }
        }
    }
}
