package Adventure.Core.Command;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This class is used to represent a direction of travel within the game world.
 */
public class Direction
    extends BaseCommand
    implements GameDirection
{
    @SuppressWarnings( "compatibility:-765586636729996755" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor is used to initialize and set up this direction.
     * 
     * @param name The string name of this direction.
     * @param helpText The text that will be shown on the help screen for this direction.
     * @param hotkey The string hotkey for this direction.
     */
    public Direction( String name, String helpText, String hotkey )
    {
        super( name, helpText, hotkey );
    }

    /**
     * This method, when run, will move the player to the location that is lead to by the exit in this direction in
     * their current location.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        GamePlayer player = Engine.getCurrentPlayer();
        for ( GameExit exit : player.getLocation().exitList() )
        {
            if ( exit.getExitDirection().equals( this ) && !exit.isHidden() )
            {
                player.setLocation( exit.getExitLocation() );
                player.getLocation().describe();
            }
        }
    }
}
