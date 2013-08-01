package Adventure.Demo.Event;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * When triggered, this event will create a new exit within the given GameLocation that leads to another given
 * GameLocation from the given GameGirection.
 */
public class AddExitToLocation
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:-8320599494865969082" )
    private static final long serialVersionUID = 1L;

    private GameLocation location;

    private GameLocation exitTo;

    private GameDirection direction;

    /**
     * This constructor will build and set up the event.
     *
     * @param name The GameComponent name to be assigned to this event.
     * @param theLocation The GameLocation to add the exit to.
     * @param exitToLocation The GameLocation the exit will lead to.
     * @param exitDirection the GameDirection that you must move in to get to the new GameLocation.
     */
    public AddExitToLocation( String name, GameLocation theLocation, GameLocation exitToLocation,
                              GameDirection exitDirection )
    {
        super( name );
        this.location = theLocation;
        this.exitTo = exitToLocation;
        this.direction = exitDirection;
    }

    /**
     * This event creates a new Gameexit in the given GameLocation.
     *
     * @param operation Not used in this event.
     */
    public void processEvent( Operation operation )
    {
        this.location.addExit( this.direction, this.exitTo );
    }
}
